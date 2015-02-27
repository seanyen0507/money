package com.example.moneymoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	private final static int _DBVersion = 1; //<-- ª©¥»
	private final static String _DBName = "SampleList.db";	//<-- db name
	private final static String _TableName = "Sample"; //<-- table name
	public Database(Context context) {
		super(context, _DBName, null, _DBVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		final String SQL = "CREATE TABLE IF NOT EXISTS " + _TableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"_USER VARCHAR," +
	            "_NAME VARCHAR(50)," +
                "_PICTURE TEXT" +
	            ");";
		db.execSQL(SQL);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
