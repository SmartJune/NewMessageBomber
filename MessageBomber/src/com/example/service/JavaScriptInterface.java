package com.example.service;

import android.content.Context;
import android.widget.Toast;

public class JavaScriptInterface {

	Context context;
	public JavaScriptInterface(Context context){
		this.context = context;
	}
	public void showToast(String webMessage){
		Toast.makeText(context, webMessage, Toast.LENGTH_SHORT).show();
	}
}
