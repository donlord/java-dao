package com.cooksys.DAO;

public class Person {

	private long id;
	private String firstName;
	private String lastName;
	private int age;
	private int locid;

	private String location;
	private String interest;

	public Person() {
	}

	public Person(long id, String first, String last, int age) {
		this.id = id;
		this.firstName = first;
		this.lastName = last;
		this.age = age;
	}

	public Person(String f, String l, int a) {
		this.firstName = f;
		this.lastName = l;
		this.age = a;
	}

	public Person(long id, String f, String last, int a, String loc, String i) {
		this.id = id;
		this.firstName = f;
		this.lastName = last;
		this.age = a;
		this.location = loc;
		this.interest = i;
	}

	public Person(long id, String f, String last, int a, String loc, int locid, String i) {
		this.id = id;
		this.firstName = f;
		this.lastName = last;
		this.age = a;
		this.locid = locid;
		this.location = loc;
		this.interest = i;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public int getLocid() {
		return locid;
	}

	public void setLocid(int locid) {
		this.locid = locid;
	}

}
