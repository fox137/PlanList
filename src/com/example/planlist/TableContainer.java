package com.example.planlist;

import android.net.Uri;
import android.provider.BaseColumns;

public class TableContainer {
	public static final String AUTHORITY = "com.xfqym.base.db";
	public static final String URI_STR = "content://" + AUTHORITY;
	
	public static class PlanTable implements BaseColumns{
		public static final String TABLE_NAME = "plan";
		public static final Uri CONTENT_URI = Uri.parse(URI_STR + "/" + TABLE_NAME);
		
		public static final String CONTENT = "content";
		public static final String START_TIME = "start_time";
		public static final String COMPLETE_TIME = "complete_time";
		
		private static StringBuffer sql = new StringBuffer().append("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append(" (")
				.append(_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT ,")
				.append(CONTENT).append(" TEXT,")
				.append(START_TIME).append(" INTEGER,")
				.append(COMPLETE_TIME).append(" INTEGER")
				.append(");");
				
				public static final String CREAT_TABLE = sql.toString();
	}

}
