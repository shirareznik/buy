package com.buyeye;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BEDatabaseHelper extends SQLiteOpenHelper {

	public BEDatabaseHelper(Context context) {
		super(context, "be_db", null, 1);//change back from .db
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("create table todo ( _id integer primary key autoincrement,"
			//	+  " title text, due long );");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Nothing to do.
	}

}
