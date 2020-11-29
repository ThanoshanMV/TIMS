package com.uc.tims;

public class StaticMembers {

	static String no;
	static String park;
	static String parkNo;
	static String wheelNo;
	static String driverName;
	static String address;
	static String nic;
	static String phoneNumber;
	static String gs;
	static String payment;
	static String amountPaid;
	static String ucReceiptNo;
	static String imageURL;
	static String name = "Hey there!,";
	static boolean adminLoggedin = false;
	static String searchParkNoDelete;
	static String paymentDriverName;
	static String paymentNIC;
	static String paymentDeleteName;
	static double y13 = 0;
	static double y14 = 0;
	static double y15 = 0;
	static double y16 = 0;
	static double y17 = 0;
	static double y18 = 0;
	static double y19 = 0;
	static double y20 = 0;
	static double y21 = 0;
	static double y22 = 0;
	static double total = 0;

	static String sqlQueryForAdminLogIn = "SELECT * FROM `employee` WHERE `username`= ? AND `password`= ?";
	
	static String sqlQueryForUserRegistration = "INSERT INTO `employee`(`roleid`,`name`,`username`,`nic`,`job`,`password`) VALUES (?,?,?,?,?,?)";
	
	static String sqlQueryForUserLogIn = "SELECT * FROM `USER` WHERE `USERNAME`= ? AND `PASSWORD`= ?";
	
	static String sqlQueryForDriverRegistration = "INSERT INTO `driver`(`name`,`nic`,`phoneno`,`wheelno`,`address`,`parkno`,`park`,`images`,`imageurl`,`gs`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	static String sqlQueryForUpdatePaymentidInDriver = "UPDATE `driver` SET `paymentid` = ? WHERE id = ?";
	static String sqlQueryForSelectDriverByID = "SELECT * FROM `driver` WHERE `nic` = ?";

	
	static String sqlQueryForChooseDriverToEdit = "SELECT * FROM `DRIVER` WHERE `PARK`= ? AND `NIC NUMBER`= ?";
	
	static String sqlQueryForInsertPaymentRow = "INSERT INTO `payment`(`name`,`nic`,`park`,`totalPayment`,`year2013`,`year2014`,`year2015`,`year2016`,`year2017`,`year2018`,`year2019`,`year2020`,`year2021`,`year2022`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

}
