package com.uc.tims.validator.mysqlvalidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.uc.tims.MySQLConnection;

public class EmployeeValidator implements MySQLValidatable {
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	
	@Override
	public boolean isNameExists(String name) {
		boolean checkName = false;
		String query = "SELECT * FROM `employee` WHERE `name`= ?";
		try {
			
			connection = MySQLConnection.establishMySqlConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				checkName = true;
			} else {
				checkName = false;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error while establishing connection.");
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return checkName;
	
	}

	@Override
	public boolean isUserNameExists(String name) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean checkUserName = false;
		String query = "SELECT * FROM `employee` WHERE `username`= ?";
		try {
			
			connection = MySQLConnection.establishMySqlConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				checkUserName = true;
			} else {
				checkUserName = false;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error while establishing connection.");
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return checkUserName;
	
	}

	@Override
	public boolean isNICExists(String nic) {

		boolean checkNic = false;
		String query = "SELECT * FROM `employee` WHERE `nic`= ?";
		try {
			connection = MySQLConnection.establishMySqlConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nic);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				checkNic = true;
			} else {
				checkNic = false;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error while establishing connection.");
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return checkNic;
	
	
	}

}
