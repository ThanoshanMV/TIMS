package com.uc.tims.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.uc.tims.StaticMembers;
import com.uc.tims.entity.Driver;
import com.uc.tims.entity.Employee;
import com.uc.tims.entity.Payment;

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

			// either (1) the row count for SQL Data Manipulation Language (DML) statements
			// or (2) 0 for SQL statements that return nothing
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

	public int findDriverByNic(String nic) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		try {
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForSelectDriverByID);
			preparedStatement.setString(1, nic);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int driverId = resultSet.getInt("id");
				return driverId;
			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e1) {
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

	public int deletePaymentByNic(String nic) {
		connection = MySQLConnection.establishMySqlConnection();
		try {
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForDeletePaymentByNic);
			preparedStatement.setString(1, nic);

			// returns > 0 if successfully executed
			return preparedStatement.executeUpdate();

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
		return -1;
	}

	public ResultSet loginAdmin(Employee employee) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForAdminLogIn);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, employee.getUserName());
			preparedStatement.setString(2, employee.getPassword());

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// login failed
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public int registerDriver(Driver driver) {

		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForDriverRegistration);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, driver.getName());
			preparedStatement.setString(2, driver.getNic());
			preparedStatement.setString(3, driver.getPhoneNumber());
			preparedStatement.setString(4, driver.getWheelNumber());
			preparedStatement.setString(5, driver.getAddress());
			preparedStatement.setString(6, driver.getParkNumber());
			preparedStatement.setString(7, driver.getPark());
			preparedStatement.setBytes(8, driver.getImage());
			preparedStatement.setString(9, driver.getImageUrl());
			preparedStatement.setString(10, driver.getGsDecision());

			// returns > 0 if successfully registered
			return preparedStatement.executeUpdate();
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
		// registration failed
		return -1;
	}

	public ResultSet findPaymentByParkNic(Driver driver) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			String query = "SELECT * FROM `payment` WHERE `park`= ? AND `nic`= ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, driver.getPark());
			preparedStatement.setString(2, driver.getNic());

			resultSet = preparedStatement.executeQuery();

			System.out.println(resultSet);

			return resultSet;
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error while establishing connection!!");
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public ResultSet findDriverByParkNic(Driver driver) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForChooseDriverToEdit);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, driver.getPark());
			preparedStatement.setString(2, driver.getNic());

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public int updateDriver(Driver driver) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		try {
			// creating prepared statement to execute parameterized query
			String sqlQueryForUpdateDriverDetails = "UPDATE `driver` SET `park`= ?,`parkno`= ? ,`wheelno`= ? ,`name`= ? ,`address`=  ? ,`nic`= ? ,`phoneno`= ? ,`gs`= ? ,`images`= ? ,`imageurl` = ? WHERE `parkno`= ?";
			preparedStatement = connection.prepareStatement(sqlQueryForUpdateDriverDetails);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, driver.getPark());
			preparedStatement.setString(2, driver.getParkNumber());
			preparedStatement.setString(3, driver.getWheelNumber());
			preparedStatement.setString(4, driver.getName());
			preparedStatement.setString(5, driver.getAddress());
			preparedStatement.setString(6, driver.getNic());
			preparedStatement.setString(7, driver.getPhoneNumber());
			preparedStatement.setString(8, driver.getGsDecision());
			preparedStatement.setBytes(9, driver.readImageFile(driver.getImageUrl()));
			preparedStatement.setString(10, driver.getImageUrl());
			preparedStatement.setString(11, StaticMembers.parkNo);

			// returns > 0 if successfully registered
			return preparedStatement.executeUpdate();
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
		// registration failed
		return -1;
	}

	public int updatePayment(Payment payment, String nic) {
		// name, nic, park

		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			String sqlQueryForUpdateDriverDetails = "UPDATE `payment` SET `name`= ?,`nic`= ? ,`park`= ? WHERE `nic`= ?";

			preparedStatement = connection.prepareStatement(sqlQueryForUpdateDriverDetails);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, payment.getName());
			preparedStatement.setString(2, payment.getNic());
			preparedStatement.setString(3, payment.getPark());
			preparedStatement.setString(4, StaticMembers.nic);

			// returns > 0 if successfully registered
			return preparedStatement.executeUpdate();
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
		// registration failed
		return -1;
	}

	public int changePassword(Employee employee) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		try {
			// creating prepared statement to execute parameterized query
			String sqlQueryForUserPasswordChange = "UPDATE `employee` SET `password`= ? WHERE `username`= ?";
			preparedStatement = connection.prepareStatement(sqlQueryForUserPasswordChange);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, employee.getPassword());
			preparedStatement.setString(2, StaticMembers.name);

			// returns > 0 if successfully registered
			return preparedStatement.executeUpdate();
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
		// registration failed
		return -1;
	}

	public int savePayment(Payment payment) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		try {
			// creating prepared statement to execute parameterized query
			String sqlQueryForUpdatePaymentDetails = "UPDATE `payment` SET `year2013`= ?,`year2014`= ? ,`year2015`= ? ,`year2016`= ? ,`year2017`=  ? ,`year2018`= ? ,`year2019`= ? ,`year2020`= ? ,`year2021`= ? ,`year2022` = ?, `totalpayment` = ? WHERE `name`= ?";
			preparedStatement = connection.prepareStatement(sqlQueryForUpdatePaymentDetails);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setDouble(1, payment.getYear2013());
			preparedStatement.setDouble(2, payment.getYear2014());
			preparedStatement.setDouble(3, payment.getYear2015());
			preparedStatement.setDouble(4, payment.getYear2016());
			preparedStatement.setDouble(5, payment.getYear2017());
			preparedStatement.setDouble(6, payment.getYear2018());
			preparedStatement.setDouble(7, payment.getYear2019());
			preparedStatement.setDouble(8, payment.getYear2020());
			preparedStatement.setDouble(9, payment.getYear2021());
			preparedStatement.setDouble(10, payment.getYear2022());
			preparedStatement.setDouble(11, payment.getTotalPayment());
			preparedStatement.setString(12, payment.getName());

			// returns > 0 if successfully registered
			return preparedStatement.executeUpdate();
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
		// registration failed
		return -1;
	}

	public ResultSet findPaymentHistory(String selection, String searchValue) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// can not add to MySQLQuery class as if I put there "WHERE ? = ?" error occurs
			String findPaymentHistory = "SELECT `park`,`name`,`nic`,`year2013`,`year2014`,`year2015`,`year2016`,`year2017`,`year2018`,`year2019`,`year2020`,`year2021`,`year2022`,`totalpayment` FROM `payment` WHERE `"
					+ selection + "` = ?";

			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(findPaymentHistory);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, searchValue);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			System.out.println(resultSet);

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public ResultSet findDriverDetails(String selection, String searchValue) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// can not add to MySQLQuery class as if I put there "WHERE ? = ?" error occurs
			String findDriverDetails = "SELECT `park`,`parkno`,`wheelno`,`name`,`address`,`nic`,`phoneno`,`gs` FROM `driver` WHERE `"
					+ selection + "` = ?";

			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(findDriverDetails);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, searchValue);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public ResultSet findDriverByParkNo(String parkNumber) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection
					.prepareStatement(MySQLQuery.sqlQueryForSelectDriverDetailsForSearchOperartion);

			// setting parkno value using PreparedStatement's setter methods
			preparedStatement.setString(1, parkNumber);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public ResultSet findDriverBySelection(String selection, String searchValue) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// can not add to MySQLQuery class as if I put there "WHERE ? = ?" error occurs
			String findDriverBySelection = "SELECT `park`,`parkno`,`wheelno`,`name`,`address`,`nic`,`phoneno`,`gs` FROM `driver` WHERE `"
					+ selection + "` LIKE ?";

			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(findDriverBySelection);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, searchValue);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			System.out.println(resultSet);

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public int deleteDriverByNic(String nic) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();
		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForDeleteDriverByNic);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, nic);

			// returns > 0 if successfully executed
			return preparedStatement.executeUpdate();

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
		// registration failed
		return -1;
	}

	public ResultSet countParkBySelection(String selection, String searchValue) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {

			String sqlQueryForCountParkBySelection = "SELECT COUNT(`park`) FROM `driver` WHERE `" + selection
					+ "` LIKE ?";

			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(sqlQueryForCountParkBySelection);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, searchValue);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public ResultSet findDriverSummaryByParkCount() {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForGetSummaryByParkCount);

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public ResultSet loginUser(Employee employee) {
		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForUserLogIn);

			// setting vales using PreparedStatement's setter methods
			preparedStatement.setString(1, employee.getUserName());
			preparedStatement.setString(2, employee.getPassword());

			// execute the selected query and return an instance of ResultSet
			resultSet = preparedStatement.executeQuery();

			return resultSet;

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		// login failed
		// cannot close the connection, statement instance until we are done with
		// ResultSet. Once we are done with ResultSet, we can close both ResultSet,
		// connection and statement.
		// if we don't do like it, we'll get error: java.sql.SQLException: Operation not
		// allowed after ResultSet closed
		// source:
		// https://initcodes.blogspot.com/2018/05/javasqlsqlexception-operation-not.html
		return resultSet;
	}

	public int registerEmployee(Employee employee) {

		// establishing MySQL connection
		connection = MySQLConnection.establishMySqlConnection();

		try {
			// creating prepared statement to execute parameterized query
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForUserRegistration);

			// setting vales using PreparedStatement's setter methods
			// registering for user, his (user) role id is 2
			preparedStatement.setInt(1, 2);
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getUserName());
			preparedStatement.setString(4, employee.getNic());
			preparedStatement.setString(5, employee.getJob());
			preparedStatement.setString(6, employee.getPassword());

			// returns > 0 if successfully registered
			return preparedStatement.executeUpdate();

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
		// registration failed
		return -1;
	}

}
