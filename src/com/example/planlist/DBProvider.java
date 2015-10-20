package com.example.planlist;



import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

public class DBProvider extends ContentProvider {
	
	private static DBHelper mDbHelper;
	private static SQLiteDatabase mDatabase;
	public static final UriMatcher URI_MATCHER;
	private static final String TAG = null;
	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(TableContainer.AUTHORITY,
				TableContainer.PlanTable.TABLE_NAME, 1);
	}

	@Override
	public boolean onCreate() {
		mDbHelper = new DBHelper(getContext());
		return false;
	}
	
	public SQLiteDatabase getDatabase(){
		if (mDbHelper == null) {
			mDbHelper = new DBHelper(getContext());
		}
		if (mDatabase == null) {
			try {
				mDatabase = mDbHelper.getWritableDatabase();
			} catch (SQLiteException e) {
				// TODO: handle exception
			}
		}
		return mDatabase;
	}
	
	private String switchTable(Uri uri){
		if(uri == null){
			return null;
		}
		String tableName = null;
		switch (URI_MATCHER.match(uri)) {
			case 1:
				tableName = TableContainer.PlanTable.TABLE_NAME;
				break;
				
			default:
				break;
		}
		return tableName;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = getDatabase();
		Cursor cursor = null;
		String table = switchTable(uri);
		if (db != null && table != null && !table.isEmpty()) {
			try {
				cursor = db.query(table, projection, selection, selectionArgs, null, null, sortOrder);
			} catch (IllegalArgumentException e) {
				Log.e(TAG, "Query SQL error:" + e);
			} catch (SQLiteException e) {
				Log.e(TAG, "Query database error! " + e);
			} catch (SQLException e) {
				Log.e(TAG, "Query fail! " + e);
			}
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return 	"vnd.android.cursor.dir/com.xfqym.plan";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = getDatabase();
		String table = switchTable(uri);
		long r = -1;
		if (db != null && table != null && !table.isEmpty()) {
			try {
				r = db.insert(table, null, values);
			} catch (IllegalArgumentException e) {
				Log.e(TAG, "Query SQL error:" + e);
			} catch (SQLiteException e) {
				Log.e(TAG, "Query database error! " + e);
			} catch (SQLException e) {
				Log.e(TAG, "Query fail! " + e);
			}
		}
		return r > 0 ? uri : null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = getDatabase();
		String table = switchTable(uri);
		int r = -1;
		if (db != null && table != null && !table.isEmpty()) {
			try {
				r = db.delete(table, selection, selectionArgs);
			} catch (IllegalArgumentException e) {
				Log.e(TAG, "Query SQL error:" + e);
			} catch (SQLiteException e) {
				Log.e(TAG, "Query database error! " + e);
			} catch (SQLException e) {
				Log.e(TAG, "Query fail! " + e);
			}
		}
		return r;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = getDatabase();
		String table = switchTable(uri);
		int r = -1;
		if (db != null && table != null && !table.isEmpty()) {
			try {
				r = db.update(table, values, selection, selectionArgs);
			} catch (IllegalArgumentException e) {
				Log.e(TAG, "Query SQL error:" + e);
			} catch (SQLiteException e) {
				Log.e(TAG, "Query database error! " + e);
			} catch (SQLException e) {
				Log.e(TAG, "Query fail! " + e);
			}
		}
		return r;
	}

}
