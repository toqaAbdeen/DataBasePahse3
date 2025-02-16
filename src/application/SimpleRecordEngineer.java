package application;

import java.math.BigInteger;

public class SimpleRecordEngineer {

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

	public SimpleRecordEngineer(int engID, String fullName, char gender, String maritalStatus, String placeOfBirth,
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

	public void setRecords(String sg, int i) {

		switch (i) {

		case 1:
			engID = Integer.parseInt(sg);
			break;
		case 2:
			fullName = sg;
			break;
		case 3:
			gender = sg.charAt(0);
			break;
		case 4:
			maritalStatus = sg;
			break;
		case 5:
			placeOfBirth = sg;
			break;
		case 6:
			dateOfBirth = sg;
			break;
		case 7:
			IDNumber = new BigInteger(sg);
			break;
		case 8:
			placeOfIssuingID = sg;
			break;
		case 9:
			dateOfIssuingID = sg;
			break;
		case 10:
			houseBuildingNumber = Integer.parseInt(sg);
			break;
		case 11:
			houseHeighborhood = sg;
			break;
		case 12:
			houseStreet = sg;
			break;
		case 13:
			city_village = sg;
			break;
		case 14:
			phoneNum1 = new BigInteger(sg);
			break;
		case 15:
			mailbox = sg;
			break;
		case 16:
			email = sg;
			break;
		case 17:
			secondaryBranch = sg;
			break;
		case 18:
			secondaryAverage = Double.parseDouble(sg);
			break;
		case 19:
			secondarYear = Integer.parseInt(sg);
			break;
		case 20:
			area_agencyIssued = sg;
			break;
		case 21:
			universityName = sg;
			break;
		case 22:
			dateOfGraduation = sg;
			break;
		case 23:
			universityLocation = sg;
			break;
		case 24:
			universityDegree = sg;
			break;
		case 25:
			OverallAppraisals = sg;
			break;
		case 26:
			generalSpecialization = sg;
			break;
		case 27:
			specificSpecialization = sg;
			break;
		case 28:
			universityID = Integer.parseInt(sg);
			break;
		case 29:
			startYear = Integer.parseInt(sg);
			break;
		case 30:
			endYear = Integer.parseInt(sg);
			break;
		case 31:
			totalCreditsHours = Integer.parseInt(sg);
			break;
		case 32:
			totalEngineeringHours = Integer.parseInt(sg);
			break;
		case 33:
			nameOfGraduationProject = sg;
			break;
		case 34:
			officeID = Integer.parseInt(sg);
			break;
		case 35:
			transactionID = Integer.parseInt(sg);
			break;
		case 36:
			salary = Double.parseDouble(sg);
			break;
		case 37:
			dateOfReceiptOfSalary = sg;
			break;
		case 38:
			annualSubscriptionRepaymentDate = sg;
		}

	}

	public String getField(int i) {

		String res = null;
		switch (i) {

		case 1:
			res = String.valueOf(engID);
			break;
		case 2:
			res = fullName;
			break;
		case 3:
			res = String.valueOf(gender);
			break;
		case 4:
			res = maritalStatus;
			break;
		case 5:
			res = placeOfBirth;
			break;
		case 6:
			res = dateOfBirth;
			break;
		case 7:
			res = String.valueOf(IDNumber);
			break;

		case 8:
			res = placeOfIssuingID;
		case 9:
			res = dateOfIssuingID;
			break;
		case 10:
			res = String.valueOf(houseBuildingNumber);
			break;
		case 11:
			res = houseHeighborhood;
			break;
		case 12:
			res = houseStreet;
			break;
		case 13:
			res = city_village;
			break;
		case 14:
			res = String.valueOf(phoneNum1);
			break;
		case 15:
			res = mailbox;
			break;
		case 16:
			res = email;
			break;
		case 17:
			res = secondaryBranch;
			break;
		case 18:
			res = String.valueOf(secondaryAverage);
			break;
		case 19:
			res = String.valueOf(secondarYear);
			break;
		case 20:
			res = area_agencyIssued;
			break;
		case 21:
			res = universityName;
			break;
		case 22:
			res = dateOfGraduation;
			break;
		case 23:
			res = universityLocation;
			break;
		case 24:
			res = universityDegree;
			break;
		case 25:
			res = OverallAppraisals;
			break;
		case 26:
			res = generalSpecialization;
			break;
		case 27:
			res = specificSpecialization;
			break;
		case 28:
			res = String.valueOf(universityID);
			break;
		case 29:
			res = String.valueOf(startYear);
			break;
		case 30:
			res = String.valueOf(endYear);
			break;
		case 31:
			res = String.valueOf(totalCreditsHours);
			break;
		case 32:
			res = String.valueOf(totalEngineeringHours);
			break;
		case 33:
			res = nameOfGraduationProject;
			break;
		case 34:
			res = String.valueOf(officeID);
			break;
		case 35:
			res = String.valueOf(transactionID);
			break;
		case 36:
			res = String.valueOf(salary);
			break;
		case 37:
			res = dateOfReceiptOfSalary;
			break;
		case 38:
			res = annualSubscriptionRepaymentDate;
		}
		return res;
	}
}
