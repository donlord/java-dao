package com.cooksys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class InterestDAO {
	private String url;
	private String user;
	private String pw;

	public InterestDAO(String url, String user, String pw) {
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
	
	public Interest get(int id) {
		Interest i = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select * from interest where id = "+id+"");
			while (rs.next()) {
				i = new Interest(rs.getString("interest"));
			}
		} catch (SQLException e) {
			System.out.println("error when trying to retrieve interest: " + id);
			e.printStackTrace();
		}
		return i;
	}
	public Interest save(Interest i) {

		long id = i.getId();
		String title = i.getTitle();
		
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.pw)) {
			Statement s = connection.createStatement();
			s.executeUpdate("INSERT into interest(interest) VALUES ('"+title+"')");
			ResultSet rs = s.executeQuery("select * from interest where interest='"+title+"'");
			if (rs.next()) {
				return new Interest(rs.getInt("id"), rs.getString("interest"));
			}
		} catch (SQLException ex) {
			System.out.println("error when trying to input data to table interest");
			ex.printStackTrace();
		}
		return null;
	}
}