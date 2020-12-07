package com.uc.tims.mysql;

public class MySQLQuery {
	
	/**
	 * Queries for admin table
	 */
	public final static String sqlQueryForAdminLogIn = "SELECT * FROM `employee` WHERE `username`= ? AND `password`= ?";
	
	/**
	 * Queries for employee table
	 */
	public final static String sqlQueryForUserRegistration = "INSERT INTO `employee`(`roleid`,`name`,`username`,`nic`,`job`,`password`) VALUES (?,?,?,?,?,?)";
	
	public final static String sqlQueryForUserLogIn = "SELECT * FROM `employee` WHERE `username`= ? AND `password`= ?";
	
	
	/**
	 * Queries for driver table 
	 */
	public final static String sqlQueryForDriverRegistration = "INSERT INTO `driver`(`name`,`nic`,`phoneno`,`wheelno`,`address`,`parkno`,`park`,`images`,`imageurl`,`gs`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	public final static String sqlQueryForUpdatePaymentidInDriver = "UPDATE `driver` SET `paymentid` = ? WHERE id = ?";
	
	public final static String sqlQueryForSelectDriverByID = "SELECT `id` FROM `driver` WHERE `nic` = ?";

	public final static String sqlQueryForChooseDriverToEdit = "SELECT * FROM `driver` WHERE `park`= ? AND `nic`= ?";
	
	public final static String sqlQueryForSelectDriverDetailsForSearchOperartion = "SELECT * FROM `driver` WHERE `parkno`= ?";
	
	public final static String sqlQueryForSelectDriverDetailsBySearch = "SELECT `park`,`parkno`,`wheelno`,`name`,`address`,`nic`,`phoneno`,`gs` FROM `driver` WHERE ? LIKE ?";
	
	public final static String sqlQueryForDeleteDriverByNic = "DELETE FROM `driver` WHERE `nic` = ?";
	
	public final static String sqlQueryForCountBy = "SELECT COUNT(`address`) FROM `driver` WHERE ? = ?";
	
	public final static String sqlQueryForGetSummaryByParkCount = "Select park as Park, count(*) as Total from driver Group by park";

	/**
	 * Queries for payment table 
	 */
	public final static String sqlQueryForInsertPaymentRow = "INSERT INTO `payment`(`id`,`name`,`nic`,`park`,`totalPayment`,`year2013`,`year2014`,`year2015`,`year2016`,`year2017`,`year2018`,`year2019`,`year2020`,`year2021`,`year2022`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public final static String sqlQueryForDeletePaymentByNic = "DELETE FROM `payment` WHERE `nic` = ?";

}
