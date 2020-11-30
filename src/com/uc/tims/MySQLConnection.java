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
	
	public static void insertPaymentRow(int id, String name, String nic, String park) {
		PreparedStatement prepare = null; 
		Connection connection = MySQLConnection.establishMySqlConnection();
		try {
			prepare = connection.prepareStatement(StaticMembers.sqlQueryForInsertPaymentRow);
			double initialPayment = 0.00;
			prepare.setInt(1, id);
			prepare.setString(2, name);
			prepare.setString(3, nic);
			prepare.setString(4, park);
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
			prepare.setDouble(15, initialPayment);
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
	
	public static int getDriverIdByNic(String nic) {
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;
		Connection connection = MySQLConnection.establishMySqlConnection();
		try {
			preparedStatement = connection.prepareStatement(StaticMembers.sqlQueryForSelectDriverByID);
			preparedStatement.setString(1, nic);
			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int driverId = resultSet.getInt("id");
				return driverId;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			try {
				resultSet.close();
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return -1;
	}
}
