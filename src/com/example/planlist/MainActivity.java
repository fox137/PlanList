package com.example.planlist;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{
	
	private ListView mPlanList;
	private PlanListAdapter mPlanAdapter;
	private List<PlanInfo> mData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	private void initData() {
		mData = new PlanDBService(this).query();
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		mData = new PlanDBService(this).query();
	}

	private void initView() {
		mPlanList = (ListView) findViewById(R.id.ma_lv_plan);
		mPlanAdapter = new PlanListAdapter(this, mData);
		mPlanList.setAdapter(mPlanAdapter);
		
		findViewById(R.id.ma_btn_add).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ma_btn_add:
			startActivity(new Intent(PlanEditActivity.ACTION));
			break;

		default:
			break;
		}
		
	}


}
