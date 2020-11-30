package com.uc.tims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MySQLConnection {
	public static Connection establishMySqlConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection mySqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/tims", "root", "root");
			return mySqlConnection;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		MySQLConnection.establishMySqlConnection();
	}
	
}
