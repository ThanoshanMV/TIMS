package com.uc.tims;

import javax.swing.JOptionPane;

import java.sql.*;
import java.util.Calendar;

public class SqliteConnection {

	static Connection establishSqliteConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "\\UrbanCouncil.db");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}

	static void createAdminTable() {
		try {

			Statement statement1 = null;

			statement1 = establishSqliteConnection().createStatement();

			String createAdminTableQuery = "CREATE TABLE IF NOT EXISTS 'ADMIN' ('ID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'USERNAME' TEXT NOT NULL, 'PASSWORD' TEXT NOT NULL)";
			statement1.executeUpdate(createAdminTableQuery);
			statement1.close();

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = SqliteConnection.establishSqliteConnection()
						.prepareStatement("SELECT * FROM `ADMIN` WHERE `USERNAME`= ?");
				ps.setString(1, "Hatton-UC");
				rs = ps.executeQuery();

				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Admin Exists");
				} else {
					JOptionPane.showMessageDialog(null, "No Admin Available");
					ps = SqliteConnection.establishSqliteConnection()
							.prepareStatement("INSERT INTO `ADMIN`(`USERNAME`,`PASSWORD`) VALUES ('Hatton-UC','2019')");
					if (ps.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "Admin Entry Done");
					} else {
						JOptionPane.showMessageDialog(null, "Admin Enty Failed");
					}
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error while establishing connection.");
			} finally {
				ps.close();
				rs.close();
				establishSqliteConnection().close();
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	static void createUserTable() {
		try {

			Statement statement2 = null;

			statement2 = establishSqliteConnection().createStatement();
			String createUserTableQuery = "CREATE TABLE IF NOT EXISTS 'USER' ('ID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'NAME' TEXT NOT NULL, 'USERNAME' TEXT NOT NULL, 'PASSWORD' TEXT NOT NULL, 'NIC' TEXT NOT NULL, 'UC' TEXT NOT NULL)";
			statement2.executeUpdate(createUserTableQuery);
			statement2.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				establishSqliteConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static void createDriverTable() {
		try {

			Statement statement3 = null;

			statement3 = establishSqliteConnection().createStatement();
			String createUserTableQuery = "CREATE TABLE IF NOT EXISTS 'DRIVER' ('ID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'PARK' TEXT NOT NULL, 'PARK NO' TEXT NOT NULL, 'WHEEL NO' TEXT NOT NULL, 'DRIVER NAME' TEXT NOT NULL, 'ADDRESS' TEXT NOT NULL, 'NIC NUMBER' TEXT NOT NULL, 'PHONE NUMBER' TEXT NOT NULL, 'GS' TEXT NOT NULL, 'IMAGES' BLOB NOT NULL, 'IMAGEURL' TEXT NOT NULL)";
			statement3.executeUpdate(createUserTableQuery);
			statement3.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				establishSqliteConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static void createPaymentTable() {
		try {

			Statement statement4 = null;

			statement4 = establishSqliteConnection().createStatement();
			String createPaymentTableQuery = "CREATE TABLE IF NOT EXISTS 'PAYMENT' ('ID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'PARK' TEXT NOT NULL, 'NAME' TEXT NOT NULL, 'NIC NUMBER' TEXT NOT NULL, '2013' REAL NOT NULL, '2014' REAL NOT NULL, '2015' REAL NOT NULL, '2016' REAL NOT NULL, '2017' REAL NOT NULL, '2018' REAL NOT NULL, '2019' REAL NOT NULL, '2020' REAL NOT NULL, '2021' REAL NOT NULL, '2022' REAL NOT NULL, 'TOTAL' REAL NOT NULL)";
			statement4.executeUpdate(createPaymentTableQuery);
			statement4.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				establishSqliteConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static void insertPaymentRow(String parkValue, String nameValue, String nicValue) {
		PreparedStatement prepare = null;
		System.out.println(parkValue + " -> PARK");
		try {
			String SqlQueryInsertPaymentRowForDriver = "INSERT INTO `PAYMENT`(`PARK`,`NAME`,`NIC NUMBER`,`2013`,`2014`,`2015`,`2016`,`2017`,`2018`,`2019`,`2020`,`2021`,`2022`,`TOTAL`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			prepare = SqliteConnection.establishSqliteConnection().prepareStatement(SqlQueryInsertPaymentRowForDriver);
			double d = 0.0;
			prepare.setString(1, parkValue);
			prepare.setString(2, nameValue);
			prepare.setString(3, nicValue);
			prepare.setDouble(4, d);
			prepare.setDouble(5, d);
			prepare.setDouble(6, d);
			prepare.setDouble(7, d);
			prepare.setDouble(8, d);
			prepare.setDouble(9, d);
			prepare.setDouble(10, d);
			prepare.setDouble(11, d);
			prepare.setDouble(12, d);
			prepare.setDouble(13, d);
			prepare.setDouble(14, d);
			prepare.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			try {
				prepare.close();
				SqliteConnection.establishSqliteConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	static void createBackupTable() {
		try {

			Statement statement5 = null;

			statement5 = establishSqliteConnection().createStatement();
			String createUpdateTableQuery = "CREATE TABLE IF NOT EXISTS 'BACKUP' ('ID' INTEGER NOT NULL PRIMARY KEY, 'DBTIME' INTEGER NOT NULL)";
			statement5.executeUpdate(createUpdateTableQuery);
			statement5.close();

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = SqliteConnection.establishSqliteConnection()
						.prepareStatement("SELECT * FROM `BACKUP` WHERE `ID`= ?");
				ps.setInt(1, 1);
				rs = ps.executeQuery();

				if (rs.next()) {
					System.out.println("Backup Table exists");
				} else {
					// creating Calendar instance
					Calendar calendar = Calendar.getInstance();
					// Returns current time in millis
					long timeInMilliSeconds = calendar.getTimeInMillis();
					ps = SqliteConnection.establishSqliteConnection()
							.prepareStatement("INSERT INTO `BACKUP`(`ID`,`DBTIME`) VALUES (?,?)");
					ps.setInt(1, 1);
					ps.setLong(2, timeInMilliSeconds);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Backup is ready to go");
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error while establishing connection.");
			} finally {
				ps.close();
				rs.close();
				establishSqliteConnection().close();
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				establishSqliteConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Send backup emails to owner
	static void backupSystem(long dbTimeValue) {

		String sqlQueryForBackupSystem = "UPDATE `BACKUP` SET `DBTIME`= ?";
		PreparedStatement ps = null;
		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement(sqlQueryForBackupSystem);
			ps.setLong(1, dbTimeValue);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				establishSqliteConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
