package com.cooksys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class PersonDAO {
	private String url;
	private String user;
	private String pw;

	public PersonDAO(String url, String user, String pw) {
		this.url = url;
		this.user = user;
		this.pw = pw;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load the driver for postgresql");
			e.printStackTrace();
		}
	}

	public Person get(int id) {
		Person p = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select * from person where id = " + id + "");
			while (rs.next()) {
				p = new Person(rs.getInt("id"), rs.getString("first"), rs.getString("last"), rs.getInt("age"));
			}
		} catch (SQLException e) {
			System.out.println("error when trying to retrieve person: " + id);
			e.printStackTrace();
		}
		return p;
	}
	
	public List<Person> getMore(String name) {
		List<Person> people = new ArrayList<Person>();
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select person.id, person.first, person.last, person.age, location.city, interest.interest"
					+ " from person"
					+ " join location on locationid = location.id join personinterest on personinterest.personid = person.id"
					+ " join interest on personinterest.interestid = interest.id"
					+ " where person.first = '" +name+"'");
			while (rs.next()) {
				people.add(new Person(rs.getInt("id"), rs.getString("first"), rs.getString("last"), rs.getInt("age"),rs.getString("city"), rs.getString("interest")));
			}
		} catch (SQLException e) {
			System.out.println("error when trying to retrieve more info on person: " + name);
			e.printStackTrace();
		}
		return people;
	}

	public Person save(Person p) {

		long id = p.getId();
		String first = p.getFirstName();
		String last = p.getLastName();
		int age = p.getAge();
		
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			s.executeUpdate("INSERT into person(first,last,age,locationid) VALUES ('"+first +"',"+"'"+last+"',"+age+","+2+")");
			ResultSet rs = s.executeQuery("select * from person where first='" + first + "'");
			if (rs.next()) {
				return new Person(rs.getInt("id"), rs.getString("first"), rs.getString("last"), rs.getInt("age"));
			}
		} catch (SQLException ex) {
			System.out.println("error when trying to input data to table person");
			ex.printStackTrace();
		}
		return null;
	}
	
	//testing save methods
//	public Person saveMore(Person p, Location l, Interest interest) {
//		
//		long id = p.getId();
//		String first = p.getFirstName();
//		String last = p.getLastName();
//		int age = p.getAge();
//		String ploc = p.getLocation();
//		String pinterest = p.getInterest();
//		
//		String location = l.getCity();
//		long locid = l.getId();
//		String city = l.getCity();
//		String state = l.getState();
//		String country = l.getCountry();
//	
//		long interestid = interest.getId();
//		String title = interest.getTitle();
//		
//		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
//			Statement s = connection.createStatement();
//			s.executeUpdate("UPDATE into person(first,last,age,locationid) VALUES ('"+first +"',"+"'"+last+"',"+age+","+2+")");
//			s.executeUpdate("INSERT into location(locationid, city, state, country Values('"+locid +"',"+"'"+ploc+"',"+city+"'"
//					+"', '"+state+ "', '"+country+"')");
//			s.executeUpdate("INSERT into interest(interestid, interest) VALUES ('"+locid+"', "+ "'" + title+"')");
//			ResultSet rs = s.executeQuery("select * from person where first='" + first + "'");
//			if (rs.next()) {
//				return new Person(rs.getInt("id"), rs.getString("first"), rs.getString("last"), rs.getInt("age"));
//			}
//		} catch (SQLException ex) {
//			System.out.println("error when trying to input data to table person/interest/location");
//			ex.printStackTrace();
//		}
//		return null;
//	}
	
//	public Person save2(Person p) {
//
//		long id = p.getId();
//		String first = p.getFirstName();
//		String last = p.getLastName();
//		int age = p.getAge();
//		String ploc = p.getLocation();
//		String pinterest = p.getInterest();
//		int locid = p.getLocid();
//		
//		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
//			Statement s = connection.createStatement();
//			s.executeUpdate("UPDATE person JOIN location on location.id = person.locationid SET locationid = '"+ locid+ "' WHERE person.id = "+id +";");
//			ResultSet rs = s.executeQuery("select * from person where first='" + first + "'");
//			if (rs.next()) {
//				return new Person(rs.getInt("id"), rs.getString("first"), rs.getString("last"), rs.getInt("age"),rs.getString("city"), rs.getString("interest"));
//			}
//		} catch (SQLException ex) {
//			System.out.println("error when trying to update data to table person");
//			ex.printStackTrace();
//		}
//		return null;
//	}
	
}
