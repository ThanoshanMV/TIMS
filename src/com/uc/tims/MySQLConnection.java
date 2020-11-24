package com.uc.tims;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class MySQLConnection {
	public static Connection establishMySqlConnection() {
		Connection mySqlConnection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mySqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/tims", "root", "root");
			return mySqlConnection;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		MySQLConnection.establishMySqlConnection();
	}
}
