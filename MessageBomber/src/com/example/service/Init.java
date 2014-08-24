package com.example.service;

import com.example.database.BomberWebsite;

import android.content.Context;

public class Init {
	Context context;
	public Init(Context context){
		this.context = context;
	}
	public void init(){
		BomberWebsiteService bws = new BomberWebsiteService(context);
		System.out.println(bws.getCount());
		BomberWebsite bomberWebsite = new BomberWebsite("1", "2", 3); 
		bws.save(bomberWebsite);
		BomberWebsite bomberWebsite2 = bws.find(3);
		System.out.println(bomberWebsite2.getUrl());
		bws.delete(3);
		System.out.println(bws.getCount());
	}
}
