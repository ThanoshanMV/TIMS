package com.uc.tims.mysql;

public class MySQLQuery {
	
	/**
	 * Queries for admin table
	 */
	private static String sqlQueryForAdminLogIn = "SELECT * FROM `employee` WHERE `username`= ? AND `password`= ?";
	
	/**
	 * Queries for employee table
	 */
	private static String sqlQueryForUserRegistration = "INSERT INTO `employee`(`roleid`,`name`,`username`,`nic`,`job`,`password`) VALUES (?,?,?,?,?,?)";
	
	private static String sqlQueryForUserLogIn = "SELECT * FROM `employee` WHERE `username`= ? AND `password`= ?";
	
	
	/**
	 * Queries for driver table 
	 */
	private static String sqlQueryForDriverRegistration = "INSERT INTO `driver`(`name`,`nic`,`phoneno`,`wheelno`,`address`,`parkno`,`park`,`images`,`imageurl`,`gs`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private static String sqlQueryForUpdatePaymentidInDriver = "UPDATE `driver` SET `paymentid` = ? WHERE id = ?";
	
	private static String sqlQueryForSelectDriverByID = "SELECT `id` FROM `driver` WHERE `nic` = ?";

	private static String sqlQueryForChooseDriverToEdit = "SELECT * FROM `driver` WHERE `park`= ? AND `nic`= ?";
	
	private static String sqlQueryForSelectDriverDetailsForSearchOperartion = "SELECT * FROM `driver` WHERE `parkno`= ?";
	
	private static String sqlQueryForSelectDriverDetailsBySearch = "SELECT `park`,`parkno`,`wheelno`,`name`,`address`,`nic`,`phoneno`,`gs` FROM `driver` WHERE ? LIKE ?";
	
	private static String sqlQueryForDeleteDriverByNic = "DELETE FROM `driver` WHERE `nic` = ?";
	
	private static String sqlQueryForCountBy = "SELECT COUNT(`address`) FROM `driver` WHERE ? = ?";

	/**
	 * Queries for payment table 
	 */
	private static String sqlQueryForInsertPaymentRow = "INSERT INTO `payment`(`id`,`name`,`nic`,`park`,`totalPayment`,`year2013`,`year2014`,`year2015`,`year2016`,`year2017`,`year2018`,`year2019`,`year2020`,`year2021`,`year2022`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static String sqlQueryForDeletePaymentByNic = "DELETE FROM `payment` WHERE `nic` = ?";
	
	
	public static String getSqlQueryForAdminLogIn() {
		return sqlQueryForAdminLogIn;
	}

	public static void setSqlQueryForAdminLogIn(String sqlQueryForAdminLogIn) {
		MySQLQuery.sqlQueryForAdminLogIn = sqlQueryForAdminLogIn;
	}

	public static String getSqlQueryForUserRegistration() {
		return sqlQueryForUserRegistration;
	}

	public static void setSqlQueryForUserRegistration(String sqlQueryForUserRegistration) {
		MySQLQuery.sqlQueryForUserRegistration = sqlQueryForUserRegistration;
	}

	public static String getSqlQueryForUserLogIn() {
		return sqlQueryForUserLogIn;
	}

	public static void setSqlQueryForUserLogIn(String sqlQueryForUserLogIn) {
		MySQLQuery.sqlQueryForUserLogIn = sqlQueryForUserLogIn;
	}

	public static String getSqlQueryForDriverRegistration() {
		return sqlQueryForDriverRegistration;
	}

	public static void setSqlQueryForDriverRegistration(String sqlQueryForDriverRegistration) {
		MySQLQuery.sqlQueryForDriverRegistration = sqlQueryForDriverRegistration;
	}

	public static String getSqlQueryForUpdatePaymentidInDriver() {
		return sqlQueryForUpdatePaymentidInDriver;
	}

	public static void setSqlQueryForUpdatePaymentidInDriver(String sqlQueryForUpdatePaymentidInDriver) {
		MySQLQuery.sqlQueryForUpdatePaymentidInDriver = sqlQueryForUpdatePaymentidInDriver;
	}

	public static String getSqlQueryForSelectDriverByID() {
		return sqlQueryForSelectDriverByID;
	}

	public static void setSqlQueryForSelectDriverByID(String sqlQueryForSelectDriverByID) {
		MySQLQuery.sqlQueryForSelectDriverByID = sqlQueryForSelectDriverByID;
	}

	public static String getSqlQueryForChooseDriverToEdit() {
		return sqlQueryForChooseDriverToEdit;
	}

	public static void setSqlQueryForChooseDriverToEdit(String sqlQueryForChooseDriverToEdit) {
		MySQLQuery.sqlQueryForChooseDriverToEdit = sqlQueryForChooseDriverToEdit;
	}

	public static String getSqlQueryForInsertPaymentRow() {
		return sqlQueryForInsertPaymentRow;
	}

	public static void setSqlQueryForInsertPaymentRow(String sqlQueryForInsertPaymentRow) {
		MySQLQuery.sqlQueryForInsertPaymentRow = sqlQueryForInsertPaymentRow;
	}

	public static String getSqlQueryForSelectDriverDetailsForSearchOperartion() {
		return sqlQueryForSelectDriverDetailsForSearchOperartion;
	}

	public static void setSqlQueryForSelectDriverDetailsForSearchOperartion(
			String sqlQueryForSelectDriverDetailsForSearchOperartion) {
		MySQLQuery.sqlQueryForSelectDriverDetailsForSearchOperartion = sqlQueryForSelectDriverDetailsForSearchOperartion;
	}

	public static String getSqlQueryForSelectDriverDetailsBySearch() {
		return sqlQueryForSelectDriverDetailsBySearch;
	}

	public static void setSqlQueryForSelectDriverDetailsBySearch(String sqlQueryForSelectDriverDetailsBySearch) {
		MySQLQuery.sqlQueryForSelectDriverDetailsBySearch = sqlQueryForSelectDriverDetailsBySearch;
	}

	public static String getSqlQueryForDeleteDriverByNic() {
		return sqlQueryForDeleteDriverByNic;
	}

	public static void setSqlQueryForDeleteDriverByParkno(String sqlQueryForDeleteDriverByNic) {
		MySQLQuery.sqlQueryForDeleteDriverByNic = sqlQueryForDeleteDriverByNic;
	}

	public static String getSqlQueryForCountBy() {
		return sqlQueryForCountBy;
	}

	public static void setSqlQueryForCountBy(String sqlQueryForCountBy) {
		MySQLQuery.sqlQueryForCountBy = sqlQueryForCountBy;
	}

	public static String getSqlQueryForDeletePaymentByNic() {
		return sqlQueryForDeletePaymentByNic;
	}

	public static void setSqlQueryForDeletePaymentByNic(String sqlQueryForDeletePaymentByNic) {
		MySQLQuery.sqlQueryForDeletePaymentByNic = sqlQueryForDeletePaymentByNic;
	}

	
}
