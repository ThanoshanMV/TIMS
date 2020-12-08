package com.uc.tims.entity;

public class Payment extends Person {
	private String park;
	private double totalPayment;
	private double year2013;
	private double year2014;
	private double year2015;
	private double year2016;
	private double year2017;
	private double year2018;
	private double year2019;
	private double year2020;
	private double year2021;
	private double year2022;

	public String calculateTotalPayment(String s1, String s2, String s3, String s4, String s5, String s6, String s7,
			String s8, String s9, String s10) {
		double d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, total;
		try {
			d1 = convertToDouble(s1);
			d2 = convertToDouble(s2);
			d3 = convertToDouble(s3);
			d4 = convertToDouble(s4);
			d5 = convertToDouble(s5);
			d6 = convertToDouble(s6);
			d7 = convertToDouble(s7);
			d8 = convertToDouble(s8);
			d9 = convertToDouble(s9);
			d10 = convertToDouble(s10);
			total = d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10;
			return Double.toString(total);
		} catch (NumberFormatException e1) {
			return Double.toString(0.0);
		}
	}

	public Double convertToDouble(String value) {
		try {
			return Double.valueOf(value);
		} catch (NumberFormatException e1) {
			return 0.0;
		}
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public double getYear2013() {
		return year2013;
	}

	public void setYear2013(double year2013) {
		this.year2013 = year2013;
	}

	public double getYear2014() {
		return year2014;
	}

	public void setYear2014(double year2014) {
		this.year2014 = year2014;
	}

	public double getYear2015() {
		return year2015;
	}

	public void setYear2015(double year2015) {
		this.year2015 = year2015;
	}

	public double getYear2016() {
		return year2016;
	}

	public void setYear2016(double year2016) {
		this.year2016 = year2016;
	}

	public double getYear2017() {
		return year2017;
	}

	public void setYear2017(double year2017) {
		this.year2017 = year2017;
	}

	public double getYear2018() {
		return year2018;
	}

	public void setYear2018(double year2018) {
		this.year2018 = year2018;
	}

	public double getYear2019() {
		return year2019;
	}

	public void setYear2019(double year2019) {
		this.year2019 = year2019;
	}

	public double getYear2020() {
		return year2020;
	}

	public void setYear2020(double year2020) {
		this.year2020 = year2020;
	}

	public double getYear2021() {
		return year2021;
	}

	public void setYear2021(double year2021) {
		this.year2021 = year2021;
	}

	public double getYear2022() {
		return year2022;
	}

	public void setYear2022(double year2022) {
		this.year2022 = year2022;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	@Override
	public String toString() {
		return "Payment [totalPayment=" + totalPayment + ", year2013=" + year2013 + ", year2014=" + year2014
				+ ", year2015=" + year2015 + ", year2016=" + year2016 + ", year2017=" + year2017 + ", year2018="
				+ year2018 + ", year2019=" + year2019 + ", year2020=" + year2020 + ", year2021=" + year2021
				+ ", year2022=" + year2022 + "]";
	}

}
