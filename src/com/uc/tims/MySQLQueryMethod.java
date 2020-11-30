package com.uc.tims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MySQLQueryMethod {
	public static void insertPaymentRow(int id, String name, String nic, String park) {
		PreparedStatement prepare = null; 
		Connection connection = MySQLConnection.establishMySqlConnection();
		try {
			prepare = connection.prepareStatement(MySQLQuery.getSqlQueryForInsertPaymentRow());
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
			preparedStatement = connection.prepareStatement(MySQLQuery.getSqlQueryForSelectDriverByID());
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
	
	public static boolean deletePaymentByNic(String nic) {
		PreparedStatement preparedStatement = null; 
		boolean resultSet = true;
		Connection connection = MySQLConnection.establishMySqlConnection();
		try {
			preparedStatement = connection.prepareStatement(MySQLQuery.getSqlQueryForDeletePaymentByNic());
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
