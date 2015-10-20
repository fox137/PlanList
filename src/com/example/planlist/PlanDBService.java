package com.example.planlist;

import java.util.ArrayList;
import java.util.List;

import com.example.planlist.TableContainer.PlanTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class PlanDBService {
	
	private static final String TAG = "PlanDBService";
	private Context mContext;
	
	public PlanDBService(Context context) {
		mContext = context;
	}
	
	public List<PlanInfo> query(){
		List<PlanInfo> list = new ArrayList<PlanInfo>();;
		Cursor cursor = null;
		try {
			cursor = mContext.getContentResolver().query(TableContainer.PlanTable.CONTENT_URI,
					null, null, null, null);
			if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
				int contentIndex = cursor.getColumnIndex(PlanTable.CONTENT);
				int stIndex = cursor.getColumnIndex(PlanTable.START_TIME);
				int ctIndex = cursor.getColumnIndex(PlanTable.COMPLETE_TIME);
				do {
					PlanInfo info = new PlanInfo("");
					info.setContent(cursor.getString(contentIndex));
					info.setStartTime(cursor.getLong(stIndex));
					info.setCompleteTime(cursor.getLong(ctIndex));
					list.add(info);
				} while (cursor.moveToNext());
			}
			
		} catch (Exception e) {
			Log.e(TAG, e+"");
		}finally{
			if (cursor != null) {
				try {
					cursor.close();
				} catch (RuntimeException e) {
					Log.e(TAG, "Cursor close fail!");
				}
				cursor = null;
			}
		}
		return list;
	}
	
	public boolean insert(PlanInfo info){
		Uri uri = mContext.getContentResolver().insert(PlanTable.CONTENT_URI, convert(info));
		return uri == null ;
	}
	
	public boolean delete(PlanInfo info){
		String where = PlanTable.START_TIME + "=" + info.getStartTime();
		int r = mContext.getContentResolver().delete(PlanTable.CONTENT_URI, where , null);
		return r > 0;
	}
	
	public boolean update(PlanInfo info){
		String where = PlanTable.START_TIME + "=" + info.getStartTime();
		int r = mContext.getContentResolver().update(PlanTable.CONTENT_URI, convert(info), where, null);
		return r > 0;
	}
	
	public ContentValues convert(PlanInfo info){
		ContentValues values = new ContentValues();
		values.put(PlanTable.CONTENT, info.getContent());
		values.put(PlanTable.START_TIME, info.getStartTime());
		values.put(PlanTable.COMPLETE_TIME, info.getCompleteTime());
		return values;
		
	}
}
