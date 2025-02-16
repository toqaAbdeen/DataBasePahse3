package application;

import java.math.BigInteger;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Engineer {

	Alert exceptionAlert = new Alert(AlertType.ERROR);

	private int engID;
	private String fullName;
	private char gender;
	private String maritalStatus;
	private String placeOfBirth;
	private String dateOfBirth;
	private BigInteger IDNumber;
	private String placeOfIssuingID;
	private String dateOfIssuingID;
	private int houseBuildingNumber;
	private String houseHeighborhood;
	private String houseStreet;
	private String city_village;
	private BigInteger phoneNum1;
	private String mailbox;
	private String email;
	private String secondaryBranch;
	private double secondaryAverage;
	private int secondarYear;
	private String area_agencyIssued;
	private String universityName;
	private String dateOfGraduation;
	private String universityLocation;
	private String universityDegree;
	private String OverallAppraisals;
	private String generalSpecialization;
	private String specificSpecialization;
	private int universityID;
	private int startYear;
	private int endYear;
	private int totalCreditsHours;
	private int totalEngineeringHours;
	private String nameOfGraduationProject;
	private int officeID;
	private int transactionID;
	private double salary;
	private String dateOfReceiptOfSalary;
	private String annualSubscriptionRepaymentDate;

	public Engineer(int engID, String fullName, char gender, String maritalStatus, String placeOfBirth,
			String dateOfBirth, BigInteger iDNumber, String placeOfIssuingID, String dateOfIssuingID,
			int houseBuildingNumber, String houseHeighborhood, String houseStreet, String city_village,
			BigInteger phoneNum1, String mailbox, String email, String secondaryBranch, double secondaryAverage,
			int secondarYear, String area_agencyIssued, String universityName, String dateOfGraduation,
			String universityLocation, String universityDegree, String overallAppraisals, String generalSpecialization,
			String specificSpecialization, int universityID, int startYear, int endYear, int totalCreditsHours,
			int totalEngineeringHours, String nameOfGraduationProject, int officeID, int transactionID, double salary,
			String dateOfReceiptOfSalary, String annualSubscriptionRepaymentDate) {

		super();
		this.engID = engID;
		this.fullName = fullName;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.placeOfBirth = placeOfBirth;
		this.dateOfBirth = dateOfBirth;
		this.IDNumber = iDNumber;
		this.placeOfIssuingID = placeOfIssuingID;
		this.dateOfIssuingID = dateOfIssuingID;
		this.houseBuildingNumber = houseBuildingNumber;
		this.houseHeighborhood = houseHeighborhood;
		this.houseStreet = houseStreet;
		this.city_village = city_village;
		this.phoneNum1 = phoneNum1;
		this.mailbox = mailbox;
		this.email = email;
		this.secondaryBranch = secondaryBranch;
		this.secondaryAverage = secondaryAverage;
		this.secondarYear = secondarYear;
		this.area_agencyIssued = area_agencyIssued;
		this.universityName = universityName;
		this.dateOfGraduation = dateOfGraduation;
		this.universityLocation = universityLocation;
		this.universityDegree = universityDegree;
		this.OverallAppraisals = overallAppraisals;
		this.generalSpecialization = generalSpecialization;
		this.specificSpecialization = specificSpecialization;
		this.universityID = universityID;
		this.startYear = startYear;
		this.endYear = endYear;
		this.totalCreditsHours = totalCreditsHours;
		this.totalEngineeringHours = totalEngineeringHours;
		this.nameOfGraduationProject = nameOfGraduationProject;
		this.officeID = officeID;
		this.transactionID = transactionID;
		this.salary = salary;
		this.dateOfReceiptOfSalary = dateOfReceiptOfSalary;
		this.annualSubscriptionRepaymentDate = annualSubscriptionRepaymentDate;
	}

	public int getEngID() {
		return engID;
	}

	public void setEngID(int engID) {
		this.engID = engID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if(gender == 'M')
		this.gender = gender;
		else {
			exceptionAlert.setTitle("The engineer's ID already exists in the system ");
			exceptionAlert.setContentText(" is already exists in the system");
			exceptionAlert.show();
		}
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public BigInteger getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(BigInteger iDNumber) {
		IDNumber = iDNumber;
	}

	public String getPlaceOfIssuingID() {
		return placeOfIssuingID;
	}

	public void setPlaceOfIssuingID(String placeOfIssuingID) {
		this.placeOfIssuingID = placeOfIssuingID;
	}

	public String getDateOfIssuingID() {
		return dateOfIssuingID;
	}

	public void setDateOfIssuingID(String dateOfIssuingID) {
		this.dateOfIssuingID = dateOfIssuingID;
	}

	public int getHouseBuildingNumber() {
		return houseBuildingNumber;
	}

	public void setHouseBuildingNumber(int houseBuildingNumber) {
		this.houseBuildingNumber = houseBuildingNumber;
	}

	public String getHouseHeighborhood() {
		return houseHeighborhood;
	}

	public void setHouseHeighborhood(String houseHeighborhood) {
		this.houseHeighborhood = houseHeighborhood;
	}

	public String getHouseStreet() {
		return houseStreet;
	}

	public void setHouseStreet(String houseStreet) {
		this.houseStreet = houseStreet;
	}

	public String getCity_village() {
		return city_village;
	}

	public void setCity_village(String city_village) {
		this.city_village = city_village;
	}

	public BigInteger getPhoneNum1() {
		return phoneNum1;
	}

	public void setPhoneNum1(BigInteger phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecondaryBranch() {
		return secondaryBranch;
	}

	public void setSecondaryBranch(String secondaryBranch) {
		this.secondaryBranch = secondaryBranch;
	}

	public double getSecondaryAverage() {
		return secondaryAverage;
	}

	public void setSecondaryAverage(double secondaryAverage) {
		this.secondaryAverage = secondaryAverage;
	}

	public int getSecondarYear() {
		return secondarYear;
	}

	public void setSecondarYear(int secondarYear) {
		this.secondarYear = secondarYear;
	}

	public String getArea_agencyIssued() {
		return area_agencyIssued;
	}

	public void setArea_agencyIssued(String area_agencyIssued) {
		this.area_agencyIssued = area_agencyIssued;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getDateOfGraduation() {
		return dateOfGraduation;
	}

	public void setDateOfGraduation(String dateOfGraduation) {
		this.dateOfGraduation = dateOfGraduation;
	}

	public String getUniversityLocation() {
		return universityLocation;
	}

	public void setUniversityLocation(String universityLocation) {
		this.universityLocation = universityLocation;
	}

	public String getUniversityDegree() {
		return universityDegree;
	}

	public void setUniversityDegree(String universityDegree) {
		this.universityDegree = universityDegree;
	}

	public String getOverallAppraisals() {
		return OverallAppraisals;
	}

	public void setOverallAppraisals(String overallAppraisals) {
		OverallAppraisals = overallAppraisals;
	}

	public String getGeneralSpecialization() {
		return generalSpecialization;
	}

	public void setGeneralSpecialization(String generalSpecialization) {
		this.generalSpecialization = generalSpecialization;
	}

	public String getSpecificSpecialization() {
		return specificSpecialization;
	}

	public void setSpecificSpecialization(String specificSpecialization) {
		this.specificSpecialization = specificSpecialization;
	}

	public int getUniversityID() {
		return universityID;
	}

	public void setUniversityID(int universityID) {
		this.universityID = universityID;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getTotalCreditsHours() {
		return totalCreditsHours;
	}

	public void setTotalCreditsHours(int totalCreditsHours) {
		this.totalCreditsHours = totalCreditsHours;
	}

	public int getTotalEngineeringHours() {
		return totalEngineeringHours;
	}

	public void setTotalEngineeringHours(int totalEngineeringHours) {
		this.totalEngineeringHours = totalEngineeringHours;
	}

	public String getNameOfGraduationProject() {
		return nameOfGraduationProject;
	}

	public void setNameOfGraduationProject(String nameOfGraduationProject) {
		this.nameOfGraduationProject = nameOfGraduationProject;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDateOfReceiptOfSalary() {
		return dateOfReceiptOfSalary;
	}

	public void setDateOfReceiptOfSalary(String dateOfReceiptOfSalary) {
		this.dateOfReceiptOfSalary = dateOfReceiptOfSalary;
	}

	public String getAnnualSubscriptionRepaymentDate() {
		return annualSubscriptionRepaymentDate;
	}

	public void setAnnualSubscriptionRepaymentDate(String annualSubscriptionRepaymentDate) {
		this.annualSubscriptionRepaymentDate = annualSubscriptionRepaymentDate;
	}

}
