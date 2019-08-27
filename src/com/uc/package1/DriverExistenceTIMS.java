package com.uc.package1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DriverExistenceTIMS {
	public static boolean isParkNoExist(String parkno) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkParkNo = false;
		String query = "SELECT * FROM `DRIVER` WHERE `PARK NO`= ?";
		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
			ps.setString(1, parkno);

			rs = ps.executeQuery();

			if (rs.next()) {
				checkParkNo = true;
			} else {
				checkParkNo = false;
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
		return checkParkNo;
	}

	public static boolean isWheelNO(String wheelNO) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkWheelNo = false;
		String query = "SELECT * FROM `DRIVER` WHERE `WHEEL NO`= ?";
		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
			ps.setString(1, wheelNO);

			rs = ps.executeQuery();

			if (rs.next()) {
				checkWheelNo = true;
			} else {
				checkWheelNo = false;
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
		return checkWheelNo;
	}

	public static boolean isName(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkName = false;
		String query = "SELECT * FROM `DRIVER` WHERE `DRIVER NAME`= ?";
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

	public static boolean isNIC(String nic) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean checkNic = false;
		String query = "SELECT * FROM `DRIVER` WHERE `NIC NUMBER`= ?";
		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
			ps.setString(1, nic);

			rs = ps.executeQuery();

			if (rs.next()) {
				checkNic = true;
			} else {
				checkNic = false;
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
		return checkNic;
	}
}
