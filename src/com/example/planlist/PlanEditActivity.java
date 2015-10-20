package com.example.planlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PlanEditActivity extends Activity implements OnClickListener {

	public static final String ACTION = PlanEditActivity.class.getName();
	public static final String EXTRA_INFO = "info";

	private PlanInfo mPlanInfo;

	private EditText mContentView;
	private Button mSaveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_planedit);
		initData();
		initView();
	}

	private void initView() {
		mContentView = (EditText) findViewById(R.id.ape_et_content);
		if (mPlanInfo != null && TextUtils.isEmpty(mPlanInfo.getContent())) {
			mContentView.setText(mPlanInfo.getContent());
		}

		mSaveButton = (Button) findViewById(R.id.ape_btn_save);
		mSaveButton.setOnClickListener(this);
	}

	private void initData() {
		mPlanInfo = getIntent().getParcelableExtra(EXTRA_INFO);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ape_btn_save:
			save();
			break;

		default:
			break;
		}

	}

	private void save() {
		PlanDBService service = new PlanDBService(this);
		if (mPlanInfo == null) {
			mPlanInfo = new PlanInfo("");
			mPlanInfo.setContent(mContentView.getEditableText().toString());
			service.insert(mPlanInfo);
		} else {
			mPlanInfo.setContent(mContentView.getEditableText().toString());
			service.update(mPlanInfo);
		}
		finish();
	}

}
