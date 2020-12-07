package com.uc.tims.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

// make method dont void
public class MySQLQueryMethod {
	private PreparedStatement preparedStatement;
	private Connection connection;
	private ResultSet resultSet;
	
	public void insertPaymentRow(int id, String name, String nic, String park) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		
		// Initial payments of all years will be by default set to 0.0
		double initialPayment = 0.00;
		
		try {
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForInsertPaymentRow);
			
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, nic);
			preparedStatement.setString(4, park);
			preparedStatement.setDouble(5, initialPayment);
			preparedStatement.setDouble(6, initialPayment);
			preparedStatement.setDouble(7, initialPayment);
			preparedStatement.setDouble(8, initialPayment);
			preparedStatement.setDouble(9, initialPayment);
			preparedStatement.setDouble(10, initialPayment);
			preparedStatement.setDouble(11, initialPayment);
			preparedStatement.setDouble(12, initialPayment);
			preparedStatement.setDouble(13, initialPayment);
			preparedStatement.setDouble(14, initialPayment);
			preparedStatement.setDouble(15, initialPayment);
			
			// either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
			preparedStatement.executeUpdate();
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
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
	}
	
	public int getDriverIdByNic(String nic) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		
		try {
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForSelectDriverByID);
			
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
		// can't able to get the associated driver id using nic
		return -1;
	}
	
	public boolean deletePaymentByNic(String nic) {
		boolean resultSet = true;
		connection = MySQLConnection.establishMySqlConnection();
		try {
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForDeletePaymentByNic);
			preparedStatement.setString(1, nic);
			
			// true if the first result is a ResultSet object; false if the first result is an update count or there is no result
			resultSet = preparedStatement.execute();
			return resultSet;
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
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
		return true;
	}
}
