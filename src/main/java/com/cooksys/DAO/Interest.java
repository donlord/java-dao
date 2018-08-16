package com.cooksys.DAO;

public class Interest {
	private long id;
	private String title;
	
	public Interest() {}
	
	public Interest (long id, String t) {
		this.id = id;
		this.title = t;
	}
	
	public Interest(String t) {
		this.title = t;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
