package com.uc.tims.entity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Driver extends Person {
	private int paymentId;
	private String wheelNumber;
	private String address;
	private String park;
	private String parkNumber;
	private String phoneNumber;
	private String gsDecision;
	private String imageUrl;
	private byte[] image;
	private String[] availableParks; 
	
	public Driver() {
		availableParks = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH"};
	}
	
	public byte[] readImageFile(String file) {
		ByteArrayOutputStream bos = null;
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}
	
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getWheelNumber() {
		return wheelNumber;
	}
	public void setWheelNumber(String wheelNumber) {
		this.wheelNumber = wheelNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getParkNumber() {
		return parkNumber;
	}
	public void setParkNumber(String parkNumber) {
		this.parkNumber = parkNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGsDecision() {
		return gsDecision;
	}
	public void setGsDecision(String gsDecision) {
		this.gsDecision = gsDecision;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String[] getAvailableparks() {
		return availableParks;
	}
	public void setAvailableParks(String[] availableParks) {
		this.availableParks = availableParks;
	}

	@Override
	public String toString() {
		return "Driver [paymentId=" + paymentId + ", wheelNumber=" + wheelNumber + ", address=" + address + ", park="
				+ park + ", parkNumber=" + parkNumber + ", phoneNumber=" + phoneNumber + ", gsDecision=" + gsDecision
				+ ", imageUrl=" + imageUrl + ", availableParks="
				+ Arrays.toString(availableParks) + ", NIC" + getNic() + "]";
	}
	
	
}
