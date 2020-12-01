package com.uc.tims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/*public class UserExistenceTIMS {

	public static boolean isUserNameExist(String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean checkUserName = false;
		String query = "SELECT * FROM `employee` WHERE `username`= ?";
		try {
			
			connection = MySQLConnection.establishMySqlConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);

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

	public static boolean isNameExist(String name) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
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
}*/
