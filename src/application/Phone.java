package application;

import java.math.BigInteger;

public class Phone {

	private BigInteger phoneNum1;
	private BigInteger phoneNum2;
	private BigInteger landLineTelephone;

	public Phone(BigInteger phoneNum1, BigInteger phoneNum2, BigInteger landLineTelephone) {
		super();
		this.phoneNum1 = phoneNum1;
		this.phoneNum2 = phoneNum2;
		this.landLineTelephone = landLineTelephone;
	}

	public BigInteger getPhoneNum1() {
		return phoneNum1;
	}

	public void setPhoneNum1(BigInteger phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}

	public BigInteger getPhoneNum2() {
		return phoneNum2;
	}

	public void setPhoneNum2(BigInteger phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}

	public BigInteger getLandLineTelephone() {
		return landLineTelephone;
	}

	public void setLandLineTelephone(BigInteger landLineTelephone) {
		this.landLineTelephone = landLineTelephone;
	}

}
