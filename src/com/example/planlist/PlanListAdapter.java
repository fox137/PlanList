package com.example.planlist;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class PlanListAdapter extends BaseAdapter {
	
	private List<PlanInfo> mList;
	private LayoutInflater mInflater;
	
	public PlanListAdapter(Context context, List<PlanInfo> list) {
		mList = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_plan, null);
			vh.checkBox = (CheckBox) convertView.findViewById(R.id.ip_cb_complete);
			vh.contentView = (TextView) convertView.findViewById(R.id.ip_tv_plan);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		vh.setData(mList.get(position));
		return convertView;
	}

	private class ViewHolder{
		public CheckBox checkBox;
		public TextView contentView;
		
		public void setData(final PlanInfo info){
			checkBox.setChecked(info.isComplete());
			contentView.setText(info.getContent());
			
//			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					info.setComplete(isChecked);
//					Collections.sort(mList);
//					PlanListAdapter.this.notifyDataSetChanged();
//					Log.d("xufeng", "check " + isChecked);
//				}
//			});
			
			checkBox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					boolean isChecked = ((CheckBox)v).isChecked();
						info.setComplete(isChecked);
						Collections.sort(mList);
						PlanListAdapter.this.notifyDataSetChanged();
						Log.d("xufeng", "check " + isChecked);
				}
			});
		}
	}
}
