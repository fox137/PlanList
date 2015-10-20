package com.example.planlist;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NAME = "com.xfqym.planlist.db";
	private final static int VERSION = 1;

	public DBHelper(Context context) {
		super(context, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		createTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTables(db);
		createTables(db);

	}

	private void createTables(SQLiteDatabase db) {
		try {
			db.execSQL(TableContainer.PlanTable.CREAT_TABLE);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	private void dropTables(SQLiteDatabase db) {
		try {
			db.execSQL("DROP TABLE IF EXISTS " + TableContainer.PlanTable.CREAT_TABLE + ";");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
