package com.example.service;

import java.util.*;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.webkit.WebView;

import com.example.database.BomberWebsite;
import com.example.database.DataBaseHelper;

public class BomberWebsiteService {
	
	private DataBaseHelper dataBaseHelper;
	public BomberWebsiteService(Context context){
		this.dataBaseHelper = new DataBaseHelper(context);
	}
	public void save(BomberWebsite bomberWebsite){
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		db.execSQL("insert into website(url,js,id) values(?,?,?)",
		new Object[]{bomberWebsite.getUrl(), bomberWebsite.getJs(),bomberWebsite.getId()});
		db.close();
	}
	public void delete(Integer id){
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		db.execSQL("delete from website where id=?",
		new Object[]{id});
		db.close();
		}
	public void update(BomberWebsite bomberWebsite){
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		db.execSQL("update website set url=?,js=? where id=?",
		new Object[]{bomberWebsite.getUrl(),bomberWebsite.getJs(),bomberWebsite.getId()});
		db.close();
	}
	public BomberWebsite find(Integer id){
		SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from website where id=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			int newid = cursor.getInt(cursor.getColumnIndex("id"));
			String url = cursor.getString(cursor.getColumnIndex("url"));
			String js = cursor.getString(cursor.getColumnIndex("js"));
			return new BomberWebsite(url,js,newid);
		}

		cursor.close();
		return null;
	}
	public void deleteTable(){
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		String sql ="DROP TABLE website";           
	    db.execSQL(sql);
	}
	public long getCount(){
		SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from website",null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}

}
