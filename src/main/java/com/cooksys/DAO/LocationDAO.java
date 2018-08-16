package com.cooksys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LocationDAO {
	private String url;
	private String user;
	private String pw;

	public LocationDAO(String url, String user, String pw) {
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
	
	public Location get(int id) {
		Location l = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select * from location where id = "+id+"");
			while (rs.next()) {
				l = new Location(rs.getString("city"), rs.getString("state"), rs.getString("country"));
			}
		} catch (SQLException e) {
			System.out.println("error when trying to retrieve location: " + id);
			e.printStackTrace();
		}
		return l;
	}
	
	public Location save(Location l) {

		long id = l.getId();
		String city = l.getCity();
		String state = l.getState();
		String country = l.getCountry();
		
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			s.executeUpdate("INSERT into location(city,state,country) VALUES ('"+city+"',"+"'"+state+"',"+"'"+country+"')");
			ResultSet rs = s.executeQuery("select * from location where city='" + city + "'and state='"+state+"'");
			if (rs.next()) {
				return new Location(rs.getInt("id"), rs.getString("city"), rs.getString("state"), rs.getString("country"));
			}
		} catch (SQLException ex) {
			System.out.println("error when trying to input data to table location");
			ex.printStackTrace();
		}
		return null;
	}
}
