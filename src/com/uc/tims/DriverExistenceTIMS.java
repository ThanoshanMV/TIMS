package com.uc.tims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DriverExistenceTIMS {
	public static boolean isParkNoExist(String parkno) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean checkParkNo = false;
		String query = "SELECT * FROM `driver` WHERE `parkno`= ?";
		try {
			connection = MySQLConnection.establishMySqlConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, parkno);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				checkParkNo = true;
			} else {
				checkParkNo = false;
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
		return checkParkNo;
	}

	public static boolean isWheelNO(String wheelNO) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean checkWheelNo = false;
		String query = "SELECT * FROM `driver` WHERE `wheelno`= ?";
		try {
			connection = MySQLConnection.establishMySqlConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, wheelNO);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				checkWheelNo = true;
			} else {
				checkWheelNo = false;
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
		return checkWheelNo;
	}

	public static boolean isName(String name) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean checkName = false;
		String query = "SELECT * FROM `driver` WHERE `name`= ?";
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

	public static boolean isNIC(String nic) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean checkNic = false;
		String query = "SELECT * FROM `driver` WHERE `nic`= ?";
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
