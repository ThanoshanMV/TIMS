package com.uc.tims.validator.mysqlvalidator;

public interface MySQLValidatable {

	public boolean isParkNumberExists(String parkNumber);
	
	public boolean isWheelNumberExists(String wheelNumber);
	
	public boolean isNameExists(String name);
	
	public boolean isUserNameExists(String name);
	
	public boolean isNICExists(String nic);
	
}
