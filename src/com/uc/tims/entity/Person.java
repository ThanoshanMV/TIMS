/**
 * 
 */
package com.uc.tims.entity;

/**
 * @author thanos18
 * 
 */
public abstract class Person {
	private int id;
	private String name;
	private String nic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	
	
}
