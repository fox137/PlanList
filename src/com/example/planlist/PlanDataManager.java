package com.example.planlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class PlanDataManager {

	private static volatile PlanDataManager instance = null;
	
	private PlanDBService mDbService;
	private List<PlanInfo> mList;
	
	private PlanDataManager(Context context){
		mDbService = new PlanDBService(context);
		mList = mDbService.query();
	}
	


	public static PlanDataManager getManager(Context context){
		if (instance == null) {
			synchronized (PlanDataManager.class) {
				if (instance == null) {
					instance = new PlanDataManager(context);
				}
			}
		}
		return instance;
	}
	
	
	public boolean insert(PlanInfo info){
		mList.add(info);
		return mDbService.insert(info);
	}
	
	public void updateAll(){
		
	}
