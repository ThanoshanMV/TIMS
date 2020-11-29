package com.uc.tims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public static void insertPaymentRow(String name, String nic, String park) {
		PreparedStatement prepare = null; 
		Connection connection = MySQLConnection.establishMySqlConnection();
		try {
			prepare = connection.prepareStatement(StaticMembers.sqlQueryForInsertPaymentRow);
			double initialPayment = 0.00;
			prepare.setString(1, name);
			prepare.setString(2, nic);
			prepare.setString(3, park);
			prepare.setDouble(4, initialPayment);
			prepare.setDouble(5, initialPayment);
			prepare.setDouble(6, initialPayment);
			prepare.setDouble(7, initialPayment);
			prepare.setDouble(8, initialPayment);
			prepare.setDouble(9, initialPayment);
			prepare.setDouble(10, initialPayment);
			prepare.setDouble(11, initialPayment);
			prepare.setDouble(12, initialPayment);
			prepare.setDouble(13, initialPayment);
			prepare.setDouble(14, initialPayment);
			prepare.executeUpdate();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			try {
				prepare.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
