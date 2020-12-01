package com.uc.tims.validator.mysqlvalidator;

public interface MySQLValidatable {
	
	public boolean isNameExists(String name);
	
	public boolean isUserNameExists(String name);
	
	public boolean isNICExists(String nic);
	
}
