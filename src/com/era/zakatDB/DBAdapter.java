package com.era.zakatDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ID="id";
	public static final String KEY_NISOB="jmlNisob";
	public static final String TAG="DBAdapter";

	public static final String DATABASE_NAME="NisobDB";
	public static final String DATABASE_TABLE="nisob";
	public static final int DATABASE_VERSION=2;
	
	private static final String DATABASE_CREATE =
			"CREATE TABLE IF NOT EXISTS nisob (id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+"jmlNisob DOUBLE);";
	
	private final Context context;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapter(Context ctx)
	{
		this.context =ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				db.execSQL(DATABASE_CREATE);
			}catch (SQLException e){
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG,"Upgrading database from version "+oldVersion+" to "+newVersion
					+", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS nisob");
			onCreate(db);			
		}
	}
	
	public DBAdapter open() throws SQLException
	{
		db=DBHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		DBHelper.close();
	}
	
	public long insertRecord(int id, String jmlNisob)
	{
		ContentValues values = new ContentValues();
		values.put(KEY_ID, id);
		values.put(KEY_NISOB, jmlNisob);
		return db.insert(DATABASE_TABLE, null, values);
	}
	
	public boolean deleteRecord(long rowId) 
	{
		return db.delete(DATABASE_TABLE, KEY_ID+"="+rowId, null)>0;
	}
	
	public Cursor getAllRecords() 
	{
		return db.query(DATABASE_TABLE, new String[]{KEY_ID,KEY_NISOB}, null,null,null,null,null);
	}
	
	public Cursor getRecord(long rowId) throws SQLException 
	{
		Cursor mCursor=
				db.query(true, DATABASE_TABLE, new String[]{KEY_ID,
				KEY_NISOB},KEY_ID+"="+rowId,null,null,null,null,null);
		if (mCursor !=null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public boolean updateRecord(long rowID,Double jmlNisob) 
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NISOB, jmlNisob);
		return db.update(DATABASE_TABLE, args, KEY_ID+"="+rowID, null)>0;
	}
}
