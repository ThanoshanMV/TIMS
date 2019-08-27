package com.uc.package1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UserExistenceTIMS {

	public static boolean isUserNameExist(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkUserName = false;
		String query = "SELECT * FROM `USER` WHERE `USERNAME`= ?";
		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				checkUserName = true;
			} else {
				checkUserName = false;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error while establishing connection.");
		} finally {
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				SqliteConnection.establishSqliteConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return checkUserName;
	}

	public static boolean isNameExist(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkName = false;
		String query = "SELECT * FROM `USER` WHERE `NAME`= ?";
		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				checkName = true;
			} else {
				checkName = false;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error while establishing connection.");
		} finally {
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				SqliteConnection.establishSqliteConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return checkName;
	}
}
