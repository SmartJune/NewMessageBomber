package com.example.database;

public class BomberWebsite {
	String url;
	String js;
	int id;
	public BomberWebsite(String url, String js, int id) {
		super();
		this.url = url;
		this.js = js;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
}
