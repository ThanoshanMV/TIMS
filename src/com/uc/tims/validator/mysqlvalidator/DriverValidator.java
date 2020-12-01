package com.uc.tims.validator.mysqlvalidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.uc.tims.MySQLConnection;

public class DriverValidator implements MySQLValidatable {
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;

	@Override
	public boolean isParkNumberExists(String parkNumber) {
		boolean checkParkNo = false;
		String query = "SELECT * FROM `driver` WHERE `parkno`= ?";
		try {
			connection = MySQLConnection.establishMySqlConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, parkNumber);

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

	@Override
	public boolean isWheelNumberExists(String wheelNumber) {
		boolean checkWheelNo = false;
		String query = "SELECT * FROM `driver` WHERE `wheelno`= ?";
		try {
			connection = MySQLConnection.establishMySqlConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, wheelNumber);

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

	@Override
	public boolean isNameExists(String name) {
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

	@Override
	public boolean isUserNameExists(String name) {
		boolean checkName = false;
		String query = "SELECT * FROM `driver` WHERE `username`= ?";
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
	public boolean isNICExists(String nic) {
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
