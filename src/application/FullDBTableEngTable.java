package application;

import static javafx.stage.Modality.NONE;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import javafx.util.converter.BigIntegerStringConverter;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * A JavaFX ConnectDB Application
 */
public class FullDBTableEngTable extends Application{
	/**
	 * @param args the command line arguments
	 * 
	 * 
	 */
	public  ArrayList<Engineer> data;

	public ObservableList<Engineer> dataList;

	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "toqa123";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Engineers_Association";
	private Connection con;

	public static void main(String[] args) {

		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		data = new ArrayList<>();

		try {

			getData();

			dataList = FXCollections.observableArrayList(data);

			tableView(stage);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")

	public void tableView(Stage stage) {

		TableView<Engineer> myDataTable = new TableView<Engineer>();

//		Scene scene = new Scene(new Group());
		stage.setTitle("Engineer Table");
		stage.setWidth(550);
		stage.setHeight(500);

		Label label = new Label("Engineer Table");
		label.setFont(new Font("Arial", 20));
		label.setTextFill(Color.BLUE);

		myDataTable.setEditable(true);
		myDataTable.setMaxHeight(250);
		myDataTable.setMaxWidth(600);

		// name of column for display
		TableColumn<Engineer, Integer> engIDCol = new TableColumn<Engineer, Integer>("Engineer ID");
		engIDCol.setMinWidth(50);

		// to get the data from specific column
		engIDCol.setCellValueFactory(new PropertyValueFactory<Engineer, Integer>("engID"));

		// fullName
		TableColumn<Engineer, String> nameCol = new TableColumn<Engineer, String>("Full Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Engineer, String>("fullName"));

		nameCol.setCellFactory(TextFieldTableCell.<Engineer>forTableColumn());

		nameCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFullName(t.getNewValue()); // display
																														// only
			updateFullName(t.getRowValue().getEngID(), t.getNewValue());
		});

		// gender
		TableColumn<Engineer, Character> genderCol = new TableColumn<Engineer, Character>("Gender");
		genderCol.setMinWidth(50);
		genderCol.setCellValueFactory(new PropertyValueFactory<Engineer, Character>("gender"));

		genderCol
				.setCellFactory(TextFieldTableCell.<Engineer, Character>forTableColumn(new CharacterStringConverter()));

		genderCol.setOnEditCommit((CellEditEvent<Engineer, Character> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue());
			updateGender(t.getRowValue().getEngID(), t.getNewValue());
		});

		// maritalStatus

		TableColumn<Engineer, String> maritalStatusCol = new TableColumn<Engineer, String>("Marital Status");
		maritalStatusCol.setMinWidth(100);
		maritalStatusCol.setCellValueFactory(new PropertyValueFactory<Engineer, String>("maritalStatus"));

		maritalStatusCol.setCellFactory(TextFieldTableCell.<Engineer>forTableColumn());

		maritalStatusCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFullName(t.getNewValue()); // display
																														// only
			updateMaritalStatus(t.getRowValue().getEngID(), t.getNewValue());
		});

		// placeOfBirth

		TableColumn<Engineer, String> placeOfBirthCol = new TableColumn<Engineer, String>("Place Of Birth");
		placeOfBirthCol.setMinWidth(100);
		placeOfBirthCol.setCellValueFactory(new PropertyValueFactory<Engineer, String>("placeOfBirth"));

		placeOfBirthCol.setCellFactory(TextFieldTableCell.<Engineer>forTableColumn());

		placeOfBirthCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFullName(t.getNewValue()); // display
																														// only
			updatePlaceOfBirth(t.getRowValue().getEngID(), t.getNewValue());
		});

		// dateOfBirth

		TableColumn<Engineer, String> dateOfBirthCol = new TableColumn<Engineer, String>("Date Of Birth");
		dateOfBirthCol.setMinWidth(100);
		dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<Engineer, String>("dateOfBirth"));

		dateOfBirthCol.setCellFactory(TextFieldTableCell.<Engineer>forTableColumn());

		dateOfBirthCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFullName(t.getNewValue()); // display
																														// only
			updateDateOfBirth(t.getRowValue().getEngID(), t.getNewValue());
		});

		// IDNumber

		TableColumn<Engineer, BigInteger> idNumberCol = new TableColumn<>("ID Number");
		idNumberCol.setMinWidth(100);
		idNumberCol.setCellValueFactory(new PropertyValueFactory<>("IDNumber"));

		idNumberCol.setCellFactory(
				TextFieldTableCell.<Engineer, BigInteger>forTableColumn(new BigIntegerStringConverter()));

		idNumberCol.setOnEditCommit((CellEditEvent<Engineer, BigInteger> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIDNumber(t.getNewValue());
			updateIDNumber(t.getRowValue().getEngID(), t.getNewValue());
		});

		// placeOfIssuingID

		TableColumn<Engineer, String> placeOfIssuingIDCol = new TableColumn<Engineer, String>("Place Of Issuing ID");
		placeOfIssuingIDCol.setMinWidth(100);
		placeOfIssuingIDCol.setCellValueFactory(new PropertyValueFactory<Engineer, String>("placeOfIssuingID"));

		placeOfIssuingIDCol.setCellFactory(TextFieldTableCell.<Engineer>forTableColumn());

		placeOfIssuingIDCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFullName(t.getNewValue()); // display
																														// only
			updatePlaceOfIssuingID(t.getRowValue().getEngID(), t.getNewValue());
		});

		// dateOfIssuingID

		TableColumn<Engineer, String> dateOfIssuingIDCol = new TableColumn<Engineer, String>("Place Of Issuing ID");
		dateOfIssuingIDCol.setMinWidth(100);
		dateOfIssuingIDCol.setCellValueFactory(new PropertyValueFactory<Engineer, String>("placeOfIssuingID"));

		dateOfIssuingIDCol.setCellFactory(TextFieldTableCell.<Engineer>forTableColumn());

		dateOfIssuingIDCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFullName(t.getNewValue()); // display
																														// only
			updateDateOfIssuingID(t.getRowValue().getEngID(), t.getNewValue());
		});

		// houseBuildingNumber

		TableColumn<Engineer, Integer> houseBuildingNumberCol = new TableColumn<>("House Building Number");
		houseBuildingNumberCol.setMinWidth(100);
		houseBuildingNumberCol.setCellValueFactory(new PropertyValueFactory<>("houseBuildingNumber"));
		houseBuildingNumberCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		houseBuildingNumberCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setHouseBuildingNumber(t.getNewValue());
			updateHouseBuildingNumber(t.getRowValue().getEngID(), t.getNewValue());
		});

		// houseHeighborhood
		TableColumn<Engineer, String> houseHeighborhoodCol = new TableColumn<>("House Neighborhood");
		houseHeighborhoodCol.setMinWidth(100);
		houseHeighborhoodCol.setCellValueFactory(new PropertyValueFactory<>("houseHeighborhood"));

		houseHeighborhoodCol.setCellFactory(TextFieldTableCell.forTableColumn());

		houseHeighborhoodCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setHouseHeighborhood(t.getNewValue());
			updateHouseNeighborhood(t.getRowValue().getEngID(), t.getNewValue());
		});

		// houseStreet
		TableColumn<Engineer, String> houseStreetCol = new TableColumn<>("House Street");
		houseStreetCol.setMinWidth(100);
		houseStreetCol.setCellValueFactory(new PropertyValueFactory<>("houseStreet"));
		houseStreetCol.setCellFactory(TextFieldTableCell.forTableColumn());
		houseStreetCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setHouseStreet(t.getNewValue());
			updateHouseStreet(t.getRowValue().getEngID(), t.getNewValue());
		});

		// city_village
		TableColumn<Engineer, String> cityVillageCol = new TableColumn<>("City/Village");
		cityVillageCol.setMinWidth(100);
		cityVillageCol.setCellValueFactory(new PropertyValueFactory<>("city_village"));
		cityVillageCol.setCellFactory(TextFieldTableCell.forTableColumn());
		cityVillageCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setCity_village(t.getNewValue());
			updateCityVillage(t.getRowValue().getEngID(), t.getNewValue());
		});

		// phoneNum1
		TableColumn<Engineer, BigInteger> phoneNum1Col = new TableColumn<>("Phone Number");
		phoneNum1Col.setMinWidth(100);
		phoneNum1Col.setCellValueFactory(new PropertyValueFactory<>("phoneNum1"));
		phoneNum1Col.setCellFactory(TextFieldTableCell.forTableColumn(new BigIntegerStringConverter()));
		phoneNum1Col.setOnEditCommit((CellEditEvent<Engineer, BigInteger> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhoneNum1(t.getNewValue());
			updatePhoneNum1(t.getRowValue().getEngID(), t.getNewValue());
		});

		// mailbox
		TableColumn<Engineer, String> mailboxCol = new TableColumn<>("Mailbox");
		mailboxCol.setMinWidth(100);
		mailboxCol.setCellValueFactory(new PropertyValueFactory<>("mailbox"));
		mailboxCol.setCellFactory(TextFieldTableCell.forTableColumn());
		mailboxCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMailbox(t.getNewValue());
			updateMailbox(t.getRowValue().getEngID(), t.getNewValue());
		});

		// email
		TableColumn<Engineer, String> emailCol = new TableColumn<>("Email");
		emailCol.setMinWidth(100);
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			updateEmail(t.getRowValue().getEngID(), t.getNewValue());
		});

		// secondaryBranch
		TableColumn<Engineer, String> secondaryBranchCol = new TableColumn<>("Secondary Branch");
		secondaryBranchCol.setMinWidth(100);
		secondaryBranchCol.setCellValueFactory(new PropertyValueFactory<>("secondaryBranch"));
		secondaryBranchCol.setCellFactory(TextFieldTableCell.forTableColumn());
		secondaryBranchCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setSecondaryBranch(t.getNewValue());
			updateSecondaryBranch(t.getRowValue().getEngID(), t.getNewValue());
		});

		// secondaryAverage
		TableColumn<Engineer, Double> secondaryAverageCol = new TableColumn<>("Secondary Average");
		secondaryAverageCol.setMinWidth(100);
		secondaryAverageCol.setCellValueFactory(new PropertyValueFactory<>("secondaryAverage"));
		secondaryAverageCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		secondaryAverageCol.setOnEditCommit((CellEditEvent<Engineer, Double> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setSecondaryAverage(t.getNewValue());
			updateSecondaryAverage(t.getRowValue().getEngID(), t.getNewValue());
		});

		// secondarYear
		TableColumn<Engineer, Integer> secondarYearCol = new TableColumn<>("Secondary Year");
		secondarYearCol.setMinWidth(100);
		secondarYearCol.setCellValueFactory(new PropertyValueFactory<>("secondarYear"));
		secondarYearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		secondarYearCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setSecondarYear(t.getNewValue());
			updateSecondarYear(t.getRowValue().getEngID(), t.getNewValue());
		});

		// area_agencyIssued
		TableColumn<Engineer, String> areaAgencyIssuedCol = new TableColumn<>("Area/Agency Issued");
		areaAgencyIssuedCol.setMinWidth(100);
		areaAgencyIssuedCol.setCellValueFactory(new PropertyValueFactory<>("area_agencyIssued"));
		areaAgencyIssuedCol.setCellFactory(TextFieldTableCell.forTableColumn());
		areaAgencyIssuedCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setArea_agencyIssued(t.getNewValue());
			updateAreaAgencyIssued(t.getRowValue().getEngID(), t.getNewValue());
		});

		// universityName
		TableColumn<Engineer, String> universityNameCol = new TableColumn<>("University Name");
		universityNameCol.setMinWidth(100);
		universityNameCol.setCellValueFactory(new PropertyValueFactory<>("universityName"));
		universityNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		universityNameCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setUniversityName(t.getNewValue());
			updateUniversityName(t.getRowValue().getEngID(), t.getNewValue());
		});

		// dateOfGraduation
		TableColumn<Engineer, String> dateOfGraduationCol = new TableColumn<>("Date Of Graduation");
		dateOfGraduationCol.setMinWidth(100);
		dateOfGraduationCol.setCellValueFactory(new PropertyValueFactory<>("dateOfGraduation"));
		dateOfGraduationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		dateOfGraduationCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setDateOfGraduation(t.getNewValue());
			updateDateOfGraduation(t.getRowValue().getEngID(), t.getNewValue());
		});

		// universityLocation
		TableColumn<Engineer, String> universityLocationCol = new TableColumn<>("University Location");
		universityLocationCol.setMinWidth(100);
		universityLocationCol.setCellValueFactory(new PropertyValueFactory<>("universityLocation"));
		universityLocationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		universityLocationCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setUniversityLocation(t.getNewValue());
			updateUniversityLocation(t.getRowValue().getEngID(), t.getNewValue());
		});

		// universityDegree
		TableColumn<Engineer, String> universityDegreeCol = new TableColumn<>("University Degree");
		universityDegreeCol.setMinWidth(100);
		universityDegreeCol.setCellValueFactory(new PropertyValueFactory<>("universityDegree"));
		universityDegreeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		universityDegreeCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setUniversityDegree(t.getNewValue());
			updateUniversityDegree(t.getRowValue().getEngID(), t.getNewValue());
		});

		// OverallAppraisals
		TableColumn<Engineer, String> overallAppraisalsCol = new TableColumn<>("Overall Appraisals");
		overallAppraisalsCol.setMinWidth(100);
		overallAppraisalsCol.setCellValueFactory(new PropertyValueFactory<>("OverallAppraisals"));
		overallAppraisalsCol.setCellFactory(TextFieldTableCell.forTableColumn());
		overallAppraisalsCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setOverallAppraisals(t.getNewValue());
			updateOverallAppraisals(t.getRowValue().getEngID(), t.getNewValue());
		});

		// generalSpecialization
		TableColumn<Engineer, String> generalSpecializationCol = new TableColumn<>("General Specialization");
		generalSpecializationCol.setMinWidth(100);
		generalSpecializationCol.setCellValueFactory(new PropertyValueFactory<>("generalSpecialization"));
		generalSpecializationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		generalSpecializationCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setGeneralSpecialization(t.getNewValue());
			updateGeneralSpecialization(t.getRowValue().getEngID(), t.getNewValue());
		});

		// specificSpecialization
		TableColumn<Engineer, String> specificSpecializationCol = new TableColumn<>("Specific Specialization");
		specificSpecializationCol.setMinWidth(100);
		specificSpecializationCol.setCellValueFactory(new PropertyValueFactory<>("specificSpecialization"));
		specificSpecializationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		specificSpecializationCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setSpecificSpecialization(t.getNewValue());
			updateSpecificSpecialization(t.getRowValue().getEngID(), t.getNewValue());
		});

		// universityID
		TableColumn<Engineer, Integer> universityIDCol = new TableColumn<>("University ID");
		universityIDCol.setMinWidth(100);
		universityIDCol.setCellValueFactory(new PropertyValueFactory<>("universityID"));
		universityIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		universityIDCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setUniversityID(t.getNewValue());
			updateUniversityID(t.getRowValue().getEngID(), t.getNewValue());
		});

		// startYear
		TableColumn<Engineer, Integer> startYearCol = new TableColumn<>("Start Year");
		startYearCol.setMinWidth(100);
		startYearCol.setCellValueFactory(new PropertyValueFactory<>("startYear"));
		startYearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		startYearCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStartYear(t.getNewValue());
			updateStartYear(t.getRowValue().getEngID(), t.getNewValue());
		});

		// endYear
		TableColumn<Engineer, Integer> endYearCol = new TableColumn<>("End Year");
		endYearCol.setMinWidth(100);
		endYearCol.setCellValueFactory(new PropertyValueFactory<>("endYear"));
		endYearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		endYearCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEndYear(t.getNewValue());
			updateEndYear(t.getRowValue().getEngID(), t.getNewValue());
		});

		// totalCreditsHours
		TableColumn<Engineer, Integer> totalCreditsHoursCol = new TableColumn<>("Total Credits Hours");
		totalCreditsHoursCol.setMinWidth(100);
		totalCreditsHoursCol.setCellValueFactory(new PropertyValueFactory<>("totalCreditsHours"));
		totalCreditsHoursCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		totalCreditsHoursCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setTotalCreditsHours(t.getNewValue());
			updateTotalCreditsHours(t.getRowValue().getEngID(), t.getNewValue());
		});

		// totalEngineeringHours
		TableColumn<Engineer, Integer> totalEngineeringHoursCol = new TableColumn<>("Total Engineering Hours");
		totalEngineeringHoursCol.setMinWidth(100);
		totalEngineeringHoursCol.setCellValueFactory(new PropertyValueFactory<>("totalEngineeringHours"));
		totalEngineeringHoursCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		totalEngineeringHoursCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setTotalEngineeringHours(t.getNewValue());
			updateTotalEngineeringHours(t.getRowValue().getEngID(), t.getNewValue());
		});

		// nameOfGraduationProject
		TableColumn<Engineer, String> nameOfGraduationProjectCol = new TableColumn<>("Name of Graduation Project");
		nameOfGraduationProjectCol.setMinWidth(100);
		nameOfGraduationProjectCol.setCellValueFactory(new PropertyValueFactory<>("nameOfGraduationProject"));
		nameOfGraduationProjectCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameOfGraduationProjectCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setNameOfGraduationProject(t.getNewValue());
			updateNameOfGraduationProject(t.getRowValue().getEngID(), t.getNewValue());
		});

		// officeID
		TableColumn<Engineer, Integer> officeIDCol = new TableColumn<>("Office ID");
		officeIDCol.setMinWidth(100);
		officeIDCol.setCellValueFactory(new PropertyValueFactory<>("officeID"));
		officeIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		officeIDCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOfficeID(t.getNewValue());
			updateOfficeID(t.getRowValue().getEngID(), t.getNewValue());
		});

		// transactionID
		TableColumn<Engineer, Integer> transactionIDCol = new TableColumn<>("Transaction ID");
		transactionIDCol.setMinWidth(100);
		transactionIDCol.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		transactionIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		transactionIDCol.setOnEditCommit((CellEditEvent<Engineer, Integer> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setTransactionID(t.getNewValue());
			updateTransactionID(t.getRowValue().getEngID(), t.getNewValue());
		});

		// salary
		TableColumn<Engineer, Double> salaryCol = new TableColumn<>("Salary");
		salaryCol.setMinWidth(100);
		salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
		salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		salaryCol.setOnEditCommit((CellEditEvent<Engineer, Double> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue());
			updateSalary(t.getRowValue().getEngID(), t.getNewValue());
		});

		// dateOfReceiptOfSalary
		TableColumn<Engineer, String> dateOfReceiptOfSalaryCol = new TableColumn<>("Date Of Receipt Of Salary");
		dateOfReceiptOfSalaryCol.setMinWidth(100);
		dateOfReceiptOfSalaryCol.setCellValueFactory(new PropertyValueFactory<>("dateOfReceiptOfSalary"));
		dateOfReceiptOfSalaryCol.setCellFactory(TextFieldTableCell.forTableColumn());
		dateOfReceiptOfSalaryCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setDateOfReceiptOfSalary(t.getNewValue());
			updateDateOfReceiptOfSalary(t.getRowValue().getEngID(), t.getNewValue());
		});

		// annualSubscriptionRepaymentDate
		TableColumn<Engineer, String> annualSubscriptionRepaymentDateCol = new TableColumn<>(
				"Annual Subscription Repayment Date");
		annualSubscriptionRepaymentDateCol.setMinWidth(100);
		annualSubscriptionRepaymentDateCol
				.setCellValueFactory(new PropertyValueFactory<>("annualSubscriptionRepaymentDate"));
		annualSubscriptionRepaymentDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		annualSubscriptionRepaymentDateCol.setOnEditCommit((CellEditEvent<Engineer, String> t) -> {
			((Engineer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setAnnualSubscriptionRepaymentDate(t.getNewValue());
			updateAnnualSubscriptionRepaymentDate(t.getRowValue().getEngID(), t.getNewValue());
		});

		myDataTable.setItems(dataList);

		myDataTable.getColumns().addAll(engIDCol, nameCol, genderCol, placeOfBirthCol, dateOfBirthCol, idNumberCol,
				placeOfIssuingIDCol, dateOfIssuingIDCol, houseBuildingNumberCol, houseHeighborhoodCol, houseStreetCol,
				cityVillageCol, phoneNum1Col, mailboxCol, emailCol, secondaryAverageCol, secondarYearCol,
				areaAgencyIssuedCol, universityNameCol, dateOfGraduationCol, universityLocationCol, universityDegreeCol,
				overallAppraisalsCol, generalSpecializationCol, specificSpecializationCol, universityIDCol,
				startYearCol, endYearCol, totalCreditsHoursCol, totalEngineeringHoursCol, nameOfGraduationProjectCol,
				officeIDCol, transactionIDCol, salaryCol, dateOfReceiptOfSalaryCol, annualSubscriptionRepaymentDateCol);

		// TextField for engID
		final TextField addEngID = new TextField();
		addEngID.setPromptText("Engineer ID");
//		addEngID.setMaxWidth(engIDCol.getPrefWidth());

		// TextField for fullName
		final TextField addFullName = new TextField();
		addFullName.setPromptText("Full Name");
//		addFullName.setMaxWidth(nameCol.getPrefWidth());

		// TextField for gender
		final TextField addGender = new TextField();
		addGender.setPromptText("Gender");
//		addGender.setMaxWidth(genderCol.getPrefWidth());

		// TextField for maritalStatus
		final TextField addMaritalStatus = new TextField();
		addMaritalStatus.setPromptText("Marital Status");
//		addMaritalStatus.setMaxWidth(maritalStatusCol.getPrefWidth());

		// TextField for placeOfBirth
		final TextField addPlaceOfBirth = new TextField();
		addPlaceOfBirth.setPromptText("Place of Birth");
//		addPlaceOfBirth.setMaxWidth(placeOfBirthCol.getPrefWidth());

		// TextField for dateOfBirth
		final TextField addDateOfBirth = new TextField();
		addDateOfBirth.setPromptText("Date of Birth");
//		addDateOfBirth.setMaxWidth(dateOfBirthCol.getPrefWidth());

		// TextField for IDNumber
		final TextField addIDNumber = new TextField();
		addIDNumber.setPromptText("ID Number");
//		addIDNumber.setMaxWidth(idNumberCol.getPrefWidth());

		// TextField for placeOfIssuingID
		final TextField addPlaceOfIssuingID = new TextField();
		addPlaceOfIssuingID.setPromptText("Place of Issuing ID");
//		addPlaceOfIssuingID.setMaxWidth(placeOfIssuingIDCol.getPrefWidth());

		// TextField for dateOfIssuingID
		final TextField addDateOfIssuingID = new TextField();
		addDateOfIssuingID.setPromptText("Date of Issuing ID");
//		addDateOfIssuingID.setMaxWidth(dateOfIssuingIDCol.getPrefWidth());

		// TextField for houseBuildingNumber
		final TextField addHouseBuildingNumber = new TextField();
		addHouseBuildingNumber.setPromptText("House Building Number");
//		addHouseBuildingNumber.setMaxWidth(houseBuildingNumberCol.getPrefWidth());

		// TextField for houseHeighborhood
		final TextField addHouseHeighborhood = new TextField();
		addHouseHeighborhood.setPromptText("House Neighborhood");
//		addHouseHeighborhood.setMaxWidth(houseHeighborhoodCol.getPrefWidth());

		// TextField for houseStreet
		final TextField addHouseStreet = new TextField();
		addHouseStreet.setPromptText("House Street");
//		addHouseStreet.setMaxWidth(houseStreetCol.getPrefWidth());

		// TextField for city_village
		final TextField addCityVillage = new TextField();
		addCityVillage.setPromptText("City/Village");
//		addCityVillage.setMaxWidth(cityVillageCol.getPrefWidth());

		// TextField for phoneNum1
		final TextField addPhoneNum1 = new TextField();
		addPhoneNum1.setPromptText("Phone Number");
		// addPhoneNum1.setMaxWidth(phoneNum1Col.getPrefWidth());

		// TextField for mailbox
		final TextField addMailbox = new TextField();
		addMailbox.setPromptText("Mailbox");
		// addMailbox.setMaxWidth(mailboxCol.getPrefWidth());

		// TextField for email
		final TextField addEmail = new TextField();
		addEmail.setPromptText("Email");
		// addEmail.setMaxWidth(emailCol.getPrefWidth());

		// TextField for secondaryBranch
		final TextField addSecondaryBranch = new TextField();
		addSecondaryBranch.setPromptText("Secondary Branch");
		// addSecondaryBranch.setMaxWidth(secondaryBranchCol.getPrefWidth());

		// TextField for secondaryAverage
		final TextField addSecondaryAverage = new TextField();
		addSecondaryAverage.setPromptText("Secondary Average");
		// addSecondaryAverage.setMaxWidth(secondaryAverageCol.getPrefWidth());

		// TextField for secondarYear
		final TextField addSecondarYear = new TextField();
		addSecondarYear.setPromptText("Secondary Year");
		// addSecondarYear.setMaxWidth(secondarYearCol.getPrefWidth());

		// TextField for area_agencyIssued
		final TextField addAreaAgencyIssued = new TextField();
		addAreaAgencyIssued.setPromptText("Area/Agency Issued");
		// addAreaAgencyIssued.setMaxWidth(areaAgencyIssuedCol.getPrefWidth());

		// TextField for universityName
		final TextField addUniversityName = new TextField();
		addUniversityName.setPromptText("University Name");
		// addUniversityName.setMaxWidth(universityNameCol.getPrefWidth());

		// TextField for dateOfGraduation
		final TextField addDateOfGraduation = new TextField();
		addDateOfGraduation.setPromptText("Date of Graduation");
		// addDateOfGraduation.setMaxWidth(dateOfGraduationCol.getPrefWidth());

		// TextField for universityLocation
		final TextField addUniversityLocation = new TextField();
		addUniversityLocation.setPromptText("Un iversity Location");
		// addUniversityLocation.setMaxWidth(universityLocationCol.getPrefWidth());

		// TextField for universityDegree
		final TextField addUniversityDegree = new TextField();
		addUniversityDegree.setPromptText("University Degree");
		// addUniversityDegree.setMaxWidth(universityDegreeCol.getPrefWidth());

		// TextField for OverallAppraisals
		final TextField addOverallAppraisals = new TextField();
		addOverallAppraisals.setPromptText("Overall Appraisals");
		// addOverallAppraisals.setMaxWidth(overallAppraisalsCol.getPrefWidth());

		// TextField for generalSpecialization
		final TextField addGeneralSpecialization = new TextField();
		addGeneralSpecialization.setPromptText("General Specialization");
		// addGeneralSpecialization.setMaxWidth(generalSpecializationCol.getPrefWidth());

		// TextField for specificSpecialization
		final TextField addSpecificSpecialization = new TextField();
		addSpecificSpecialization.setPromptText("Specific Specialization");
		// addSpecificSpecialization.setMaxWidth(specificSpecializationCol.getPrefWidth());

		// TextField for universityID
		final TextField addUniversityID = new TextField();
		addUniversityID.setPromptText("University ID");
		// addUniversityID.setMaxWidth(universityIDCol.getPrefWidth());

		// TextField for startYear
		final TextField addStartYear = new TextField();
		addStartYear.setPromptText("Start Year");
		// addStartYear.setMaxWidth(startYearCol.getPrefWidth());

		// TextField for endYear
		final TextField addEndYear = new TextField();
		addEndYear.setPromptText("End Year");
		// addEndYear.setMaxWidth(endYearCol.getPrefWidth());

		// TextField for totalCreditsHours
		final TextField addTotalCreditsHours = new TextField();
		addTotalCreditsHours.setPromptText("Total Credits Hours");
		// addTotalCreditsHours.setMaxWidth(totalCreditsHoursCol.getPrefWidth());

		// TextField for totalEngineeringHours
		final TextField addTotalEngineeringHours = new TextField();
		addTotalEngineeringHours.setPromptText("Total Engineering Hours");
		// addTotalEngineeringHours.setMaxWidth(totalEngineeringHoursCol.getPrefWidth());

		// TextField for nameOfGraduationProject
		final TextField addNameOfGraduationProject = new TextField();
		addNameOfGraduationProject.setPromptText("Name of Graduation Project");
		// addNameOfGraduationProject.setMaxWidth(nameOfGraduationProjectCol.getPrefWidth());

		// TextField for officeID
		final TextField addOfficeID = new TextField();
		addOfficeID.setPromptText("Office ID");
		// addOfficeID.setMaxWidth(officeIDCol.getPrefWidth());

		// TextField for transactionID
		final TextField addTransactionID = new TextField();
		addTransactionID.setPromptText("Transaction ID");
		// addTransactionID.setMaxWidth(transactionIDCol.getPrefWidth());

		// TextField for salary
		final TextField addSalary = new TextField();
		addSalary.setPromptText("Salary");
		// addSalary.setMaxWidth(salaryCol.getPrefWidth());

		// TextField for dateOfReceiptOfSalary
		final TextField addDateOfReceiptOfSalary = new TextField();
		addDateOfReceiptOfSalary.setPromptText("Date of Receipt of Salary");
//		addDateOfReceiptOfSalary.setMaxWidth(dateOfReceiptOfSalaryCol.getPrefWidth());

		// TextField for annualSubscriptionRepaymentDate
		final TextField addAnnualSubscriptionRepaymentDate = new TextField();
		addAnnualSubscriptionRepaymentDate.setPromptText("Annual Subscription Repayment Date");
//		addAnnualSubscriptionRepaymentDate.setMaxWidth(annualSubscriptionRepaymentDateCol.getPrefWidth());

		// Button for engID
		final Button addEngIDButton = new Button("Add");
		addEngIDButton.setOnAction((ActionEvent e) -> { /***************/
			Engineer rc = new Engineer(Integer.valueOf(addEngID.getText()), addFullName.getText(),
					addGender.getText().charAt(0), addMaritalStatus.getText(), addPlaceOfBirth.getText(),
					addDateOfBirth.getText(), new BigInteger(addIDNumber.getText()), addPlaceOfIssuingID.getText(),
					addDateOfIssuingID.getText(), Integer.valueOf(addHouseBuildingNumber.getText()),
					addHouseHeighborhood.getText(), addHouseStreet.getText(), addCityVillage.getText(),
					new BigInteger(addPhoneNum1.getText()), addMailbox.getText(), addEmail.getText(),
					addSecondaryBranch.getText(), Double.valueOf(addSecondaryAverage.getText()),
					Integer.valueOf(addSecondarYear.getText()), addAreaAgencyIssued.getText(),
					addUniversityName.getText(), addDateOfGraduation.getText(), addUniversityLocation.getText(),
					addUniversityDegree.getText(), addOverallAppraisals.getText(), addGeneralSpecialization.getText(),
					addSpecificSpecialization.getText(), Integer.valueOf(addUniversityID.getText()),
					Integer.valueOf(addStartYear.getText()), Integer.valueOf(addEndYear.getText()),
					Integer.valueOf(addTotalCreditsHours.getText()),
					Integer.valueOf(addTotalEngineeringHours.getText()), addNameOfGraduationProject.getText(),
					Integer.valueOf(addOfficeID.getText()), Integer.valueOf(addTransactionID.getText()),
					Double.valueOf(addSalary.getText()), addDateOfReceiptOfSalary.getText(),
					addAnnualSubscriptionRepaymentDate.getText());
			dataList.add(rc);
			insertData(rc);
			// Clear all text fields
			addEngID.clear();
			addFullName.clear();
			addGender.clear();
			addMaritalStatus.clear();
			addPlaceOfBirth.clear();
			addDateOfBirth.clear();
			addIDNumber.clear();
			addPlaceOfIssuingID.clear();
			addDateOfIssuingID.clear();
			addHouseBuildingNumber.clear();
			addHouseHeighborhood.clear();
			addHouseStreet.clear();
			addCityVillage.clear();
			addPhoneNum1.clear();
			addMailbox.clear();
			addEmail.clear();
			addSecondaryBranch.clear();
			addSecondaryAverage.clear();
			addSecondarYear.clear();
			addAreaAgencyIssued.clear();
			addUniversityName.clear();
			addDateOfGraduation.clear();
			addUniversityLocation.clear();
			addUniversityDegree.clear();
			addOverallAppraisals.clear();
			addGeneralSpecialization.clear();
			addSpecificSpecialization.clear();
			addUniversityID.clear();
			addStartYear.clear();
			addEndYear.clear();
			addTotalCreditsHours.clear();
			addTotalEngineeringHours.clear();
			addNameOfGraduationProject.clear();
			addOfficeID.clear();
			addTransactionID.clear();
			addSalary.clear();
			addDateOfReceiptOfSalary.clear();
			addAnnualSubscriptionRepaymentDate.clear();
		});

		final HBox hb1 = new HBox();

		final Button deleteButton = new Button("Delete");
		deleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Engineer> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
			ArrayList<Engineer> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				myDataTable.getItems().remove(row);
				deleteRow(row);
				myDataTable.refresh();
			});
		});

		hb1.getChildren().addAll(addEngID, addFullName, addGender, addMaritalStatus);
		hb1.setSpacing(3);

		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(addPlaceOfBirth, addDateOfBirth, addIDNumber, addPlaceOfIssuingID);
		hb2.setSpacing(3);

		final HBox hb3 = new HBox();
		hb3.getChildren().addAll(addDateOfIssuingID, addHouseBuildingNumber, addHouseHeighborhood, addHouseStreet);
		hb3.setSpacing(3);

		final HBox hb4 = new HBox();
		hb4.getChildren().addAll(addCityVillage, addPhoneNum1, addMailbox, addEmail);
		hb4.setSpacing(3);

		final HBox hb5 = new HBox();
		hb5.getChildren().addAll(addSecondaryBranch, addSecondaryAverage, addSecondarYear, addAreaAgencyIssued);
		hb5.setSpacing(3);

		final HBox hb6 = new HBox();
		hb6.getChildren().addAll(addUniversityName, addDateOfGraduation, addUniversityLocation, addUniversityDegree);
		hb6.setSpacing(3);

		final HBox hb7 = new HBox();
		hb7.getChildren().addAll(addOverallAppraisals, addGeneralSpecialization, addSpecificSpecialization,
				addUniversityID);
		hb7.setSpacing(3);

		final HBox hb8 = new HBox();
		hb8.getChildren().addAll(addStartYear, addEndYear, addTotalCreditsHours, addTotalEngineeringHours);
		hb8.setSpacing(3);

		final HBox hb9 = new HBox();
		hb9.getChildren().addAll(addNameOfGraduationProject, addOfficeID, addTransactionID, addSalary);
		hb9.setSpacing(3);

		final HBox hb10 = new HBox();
		hb10.getChildren().addAll(addDateOfReceiptOfSalary, addAnnualSubscriptionRepaymentDate);
		hb10.setSpacing(3);

		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9, hb10);
		final Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction((ActionEvent e) -> {
			myDataTable.refresh();
		});
		final Button clearButton = new Button("Clear All");
		clearButton.setOnAction((ActionEvent e) -> {
			showDialog(stage, NONE, myDataTable);

		});

		final HBox hb11 = new HBox();
		hb11.getChildren().addAll(addEngIDButton, deleteButton, clearButton, refreshButton);
		hb11.setAlignment(Pos.CENTER_RIGHT);
		hb11.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.setAlignment(Pos.CENTER);

		myDataTable.setMinWidth(800); // Adjust minimum width as needed
		myDataTable.setPrefWidth(Double.MAX_VALUE); // Allow table to expand horizontally
		;

		ScrollPane scrollPane = new ScrollPane();

		vbox.getChildren().addAll(label, myDataTable, vBox, hb11);
		scrollPane.setContent(vbox);
		scrollPane.setPrefSize(300, 200); // Width, Height

		// vbox.getChildren().addAll(label, myDataTable);
		Scene scene = new Scene(scrollPane); // Add the ScrollPane to the Scene

//		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
	}

	
//	private void insertData(Engineer rc) {
//		try {
//			String query = "insert into engineer(engID, fullName, gender , maritalStatus, placeOfBirth , dateOfBirth ,\r\n"
//					+ "IDNumber , placeOfIssuingID, dateOfIssuingID , houseBuildingNumber,houseHeighborhood , houseStreet , city_village, \r\n"
//					+ "phoneNum1 ,mailbox,email , secondaryBranch, secondaryAverage , secondarYear , area_agencyIssued ,\r\n"
//					+ "universityName,dateOfGraduation , universityLocation, universityDegree , OverallAppraisals , generalSpecialization ,\r\n"
//					+ "specificSpecialization, universityID , startYear , endYear ,totalCreditsHours , totalEngineeringHours , \r\n"
//					+ "nameOfGraduationProject , officeID ,transactionID,salary ,dateOfReceiptOfSalary,annualSubscriptionRepaymentDate )  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//			connectDB();
//			PreparedStatement statement = con.prepareStatement(query);
//			statement.setInt(1, rc.getEngID());
//			statement.setString(2, rc.getFullName());
//			statement.setString(3, String.valueOf(rc.getGender()));
//			statement.setString(4, rc.getMaritalStatus());
//			statement.setString(5, rc.getPlaceOfBirth());
//			statement.setString(6, rc.getDateOfBirth());
//			statement.setLong(7, rc.getIDNumber().longValue());
//			statement.setString(8, rc.getPlaceOfIssuingID());
//			statement.setString(9, rc.getDateOfIssuingID());
//			statement.setInt(10, rc.getHouseBuildingNumber());
//			statement.setString(11, rc.getHouseHeighborhood());
//			statement.setString(12, rc.getHouseStreet());
//			statement.setString(13, rc.getCity_village());
//			statement.setLong(14, rc.getPhoneNum1().longValue());
//			statement.setString(15, rc.getMailbox());
//			statement.setString(16, rc.getEmail());
//			statement.setString(17, rc.getSecondaryBranch());
//			statement.setDouble(18, rc.getSecondaryAverage());
//			statement.setInt(19, rc.getSecondarYear());
//			statement.setString(20, rc.getArea_agencyIssued());
//			statement.setString(21, rc.getUniversityName());
//			statement.setString(22, rc.getDateOfGraduation());
//			statement.setString(23, rc.getUniversityLocation());
//			statement.setString(24, rc.getUniversityDegree());
//			statement.setString(25, rc.getOverallAppraisals());
//			statement.setString(26, rc.getGeneralSpecialization());
//			statement.setString(27, rc.getSpecificSpecialization());
//			statement.setInt(28, rc.getUniversityID());
//			statement.setInt(29, rc.getStartYear());
//			statement.setInt(30, rc.getEndYear());
//			statement.setInt(31, rc.getTotalCreditsHours());
//			statement.setInt(32, rc.getTotalEngineeringHours());
//			statement.setString(33, rc.getNameOfGraduationProject());
//			statement.setInt(34, rc.getOfficeID());
//			statement.setInt(35, rc.getTransactionID());
//			statement.setDouble(36, rc.getSalary());
//			statement.setString(37, rc.getDateOfReceiptOfSalary());
//			statement.setString(38, rc.getAnnualSubscriptionRepaymentDate());
//
//			statement.executeUpdate();
//			con.close();
//			System.out.println("Connection closed. Record inserted successfully.");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
	public void getData() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String SQL;

		connectDB();
		System.out.println("Connection established");

		SQL = "select engID, fullName, gender, maritalStatus, placeOfBirth, dateOfBirth, IDNumber, placeOfIssuingID, dateOfIssuingID, houseBuildingNumber, houseHeighborhood, houseStreet, city_village, phoneNum1, mailbox, email, secondaryBranch, secondaryAverage, secondarYear, area_agencyIssued, universityName, dateOfGraduation, universityLocation, universityDegree, OverallAppraisals, generalSpecialization, specificSpecialization, universityID, startYear, endYear, totalCreditsHours, totalEngineeringHours, nameOfGraduationProject, officeID, transactionID, salary, dateOfReceiptOfSalary, annualSubscriptionRepaymentDate from Engineer order by engID";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			// SimpleRecord rd = ;
			data.add(new Engineer(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3).charAt(0),
					rs.getString(4), rs.getString(5), rs.getString(6), new BigInteger(rs.getString(7)), rs.getString(8),
					rs.getString(9), Integer.parseInt(rs.getString(10)), rs.getString(11), rs.getString(12),
					rs.getString(13), new BigInteger(rs.getString(14)), rs.getString(15), rs.getString(16),
					rs.getString(17), Double.parseDouble(rs.getString(18)), Integer.parseInt(rs.getString(19)),
					rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24),
					rs.getString(25), rs.getString(26), rs.getString(27), Integer.parseInt(rs.getString(28)),
					Integer.parseInt(rs.getString(29)), Integer.parseInt(rs.getString(30)),
					Integer.parseInt(rs.getString(31)), Integer.parseInt(rs.getString(32)), rs.getString(33),
					Integer.parseInt(rs.getString(34)), Integer.parseInt(rs.getString(35)),
					Integer.parseInt(rs.getString(36)), rs.getString(37), rs.getString(38)

			));
		}

		rs.close();
		stmt.close();

		con.close();
		System.out.println("Connection closed " + data.size());

	}

	private void insertData(Engineer rc) {
	    try {
	        System.out.println("Insert into Engineer (engID, fullName, gender, maritalStatus, placeOfBirth, dateOfBirth, IDNumber, placeOfIssuingID, dateOfIssuingID, houseBuildingNumber, houseHeighborhood, houseStreet, city_village, phoneNum1, mailbox, email, secondaryBranch, secondaryAverage, secondarYear, area_agencyIssued, universityName, dateOfGraduation, universityLocation, universityDegree, OverallAppraisals, generalSpecialization, specificSpecialization, universityID, startYear, endYear, totalCreditsHours, totalEngineeringHours, nameOfGraduationProject, officeID, transactionID, salary, dateOfReceiptOfSalary, annualSubscriptionRepaymentDate) values (" + rc.getEngID() + ",'" + rc.getFullName() + "','" + rc.getGender() + "','" + rc.getMaritalStatus() + "','" + rc.getPlaceOfBirth() + "','" + rc.getDateOfBirth() + "'," + rc.getIDNumber() + ",'" + rc.getPlaceOfIssuingID() + "','" + rc.getDateOfIssuingID() + "'," + rc.getHouseBuildingNumber() + ",'" + rc.getHouseHeighborhood() + "','" + rc.getHouseStreet() + "','" + rc.getCity_village() + "'," + rc.getPhoneNum1() + ",'" + rc.getMailbox() + "','" + rc.getEmail() + "','" + rc.getSecondaryBranch() + "'," + rc.getSecondaryAverage() + "," + rc.getSecondarYear() + ",'" + rc.getArea_agencyIssued() + "','" + rc.getUniversityName() + "','" + rc.getDateOfGraduation() + "','" + rc.getUniversityLocation() + "','" + rc.getUniversityDegree() + "','" + rc.getOverallAppraisals() + "','" + rc.getGeneralSpecialization() + "','" + rc.getSpecificSpecialization() + "'," + rc.getUniversityID() + "," + rc.getStartYear() + "," + rc.getEndYear() + "," + rc.getTotalCreditsHours() + "," + rc.getTotalEngineeringHours() + ",'" + rc.getNameOfGraduationProject() + "'," + rc.getOfficeID() + "," + rc.getTransactionID() + "," + rc.getSalary() + ",'" + rc.getDateOfReceiptOfSalary() + "','" + rc.getAnnualSubscriptionRepaymentDate() + "')");

	        connectDB();
	        ExecuteStatement("Insert into Engineer (engID, fullName, gender, maritalStatus, placeOfBirth, dateOfBirth, IDNumber, placeOfIssuingID, dateOfIssuingID, houseBuildingNumber, houseHeighborhood, houseStreet, city_village, phoneNum1, mailbox, email, secondaryBranch, secondaryAverage, secondarYear, area_agencyIssued, universityName, dateOfGraduation, universityLocation, universityDegree, OverallAppraisals, generalSpecialization, specificSpecialization, universityID, startYear, endYear, totalCreditsHours, totalEngineeringHours, nameOfGraduationProject, officeID, transactionID, salary, dateOfReceiptOfSalary, annualSubscriptionRepaymentDate) values (" + rc.getEngID() + ",'" + rc.getFullName() + "','" + rc.getGender() + "','" + rc.getMaritalStatus() + "','" + rc.getPlaceOfBirth() + "','" + rc.getDateOfBirth() + "'," + rc.getIDNumber() + ",'" + rc.getPlaceOfIssuingID() + "','" + rc.getDateOfIssuingID() + "'," + rc.getHouseBuildingNumber() + ",'" + rc.getHouseHeighborhood() + "','" + rc.getHouseStreet() + "','" + rc.getCity_village() + "'," + rc.getPhoneNum1() + ",'" + rc.getMailbox() + "','" + rc.getEmail() + "','" + rc.getSecondaryBranch() + "'," + rc.getSecondaryAverage() + "," + rc.getSecondarYear() + ",'" + rc.getArea_agencyIssued() + "','" + rc.getUniversityName() + "','" + rc.getDateOfGraduation() + "','" + rc.getUniversityLocation() + "','" + rc.getUniversityDegree() + "','" + rc.getOverallAppraisals() + "','" + rc.getGeneralSpecialization() + "','" + rc.getSpecificSpecialization() + "'," + rc.getUniversityID() + "," + rc.getStartYear() + "," + rc.getEndYear() + "," + rc.getTotalCreditsHours() + "," + rc.getTotalEngineeringHours() + ",'" + rc.getNameOfGraduationProject() + "'," + rc.getOfficeID() + "," + rc.getTransactionID() + "," + rc.getSalary() + ",'" + rc.getDateOfReceiptOfSalary() + "','" + rc.getAnnualSubscriptionRepaymentDate() + "')");

	        con.close();
	        System.out.println("Connection closed. Number of engineers: " + dataList.size());
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

//	private void getData() {
//	    try {
//	        connectDB();
//	        System.out.println("Connection established");
//
//	        String SQL = "SELECT * FROM Engineer ORDER BY engID";
//	        Statement stmt = con.createStatement();
//	        ResultSet rs = stmt.executeQuery(SQL);
//
//	        while (rs.next()) {
//	            Engineer engineer = new Engineer(
//	                    rs.getInt("engID"),
//	                    rs.getString("fullName"),
//	                    rs.getString("gender").charAt(0),
//	                    rs.getString("maritalStatus"),
//	                    rs.getString("placeOfBirth"),
//	                    rs.getString("dateOfBirth"),
//	                    new BigInteger(rs.getString("IDNumber")),
//	                    rs.getString("placeOfIssuingID"),
//	                    rs.getString("dateOfIssuingID"),
//	                    rs.getInt("houseBuildingNumber"),
//	                    rs.getString("houseHeighborhood"),
//	                    rs.getString("houseStreet"),
//	                    rs.getString("city_village"),
//	                    new BigInteger(rs.getString("phoneNum1")),
//	                    rs.getString("mailbox"),
//	                    rs.getString("email"),
//	                    rs.getString("secondaryBranch"),
//	                    rs.getDouble("secondaryAverage"),
//	                    rs.getInt("secondarYear"),
//	                    rs.getString("area_agencyIssued"),
//	                    rs.getString("universityName"),
//	                    rs.getString("dateOfGraduation"),
//	                    rs.getString("universityLocation"),
//	                    rs.getString("universityDegree"),
//	                    rs.getString("OverallAppraisals"),
//	                    rs.getString("generalSpecialization"),
//	                    rs.getString("specificSpecialization"),
//	                    rs.getInt("universityID"),
//	                    rs.getInt("startYear"),
//	                    rs.getInt("endYear"),
//	                    rs.getInt("totalCreditsHours"),
//	                    rs.getInt("totalEngineeringHours"),
//	                    rs.getString("nameOfGraduationProject"),
//	                    rs.getInt("officeID"),
//	                    rs.getInt("transactionID"),
//	                    rs.getDouble("salary"),
//	                    rs.getString("dateOfReceiptOfSalary"),
//	                    rs.getString("annualSubscriptionRepaymentDate")
//	            );
//	            dataList.add(engineer);
//	        }
//
//	        rs.close();
//	        stmt.close();
//	        con.close();
//	        System.out.println("Connection closed. Number of engineers: " + dataList.size());
//	    } catch (SQLException | ClassNotFoundException e) {
//	        e.printStackTrace();
//	    }
//	}

	private void connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}

	public void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");

		}

	}

	public void updateFullName(int engID, String fullName) {

		try {
			System.out.println("update  Engineer set sname = '" + fullName + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set sname = '" + fullName + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateGender(int engID, char gender) {

		try {
			System.out.println("update  Engineer set gender = " + gender + " where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set age = " + gender + " where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateMaritalStatus(int engID, String maritalStatus) {

		try {
			System.out.println("update  Engineer set maritalStatus = '" + maritalStatus + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set maritalStatus = '" + maritalStatus + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updatePlaceOfBirth(int engID, String placeOfBirth) {

		try {
			System.out.println("update  Engineer set placeOfBirth = '" + placeOfBirth + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set placeOfBirth = '" + placeOfBirth + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateDateOfBirth(int engID, String placeOfBirth) {

		try {
			System.out.println("update  Engineer set placeOfBirth = '" + placeOfBirth + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set placeOfBirth = '" + placeOfBirth + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateIDNumber(int engID, BigInteger IDNumber) {
		try {
			System.out.println("update  Engineer set IDNumber = '" + IDNumber + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set IDNumber = '" + IDNumber + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updatePlaceOfIssuingID(int engID, String placeOfIssuingID) {
		try {
			System.out.println(
					"update  Engineer set placeOfIssuingID = '" + placeOfIssuingID + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set placeOfIssuingID = '" + placeOfIssuingID + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateDateOfIssuingID(int engID, String dateOfIssuingID) {
		try {
			System.out
					.println("update  Engineer set dateOfIssuingID = '" + dateOfIssuingID + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set dateOfIssuingID = '" + dateOfIssuingID + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateHouseBuildingNumber(int engID, Integer houseBuildingNumber) {
		try {
			System.out.println(
					"update  Engineer set houseBuildingNumber = '" + houseBuildingNumber + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set houseBuildingNumber = '" + houseBuildingNumber + "' where engID = "
					+ engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateHouseNeighborhood(int engID, String houseNeighborhood) {
		try {
			System.out.println(
					"update  Engineer set houseNeighborhood = '" + houseNeighborhood + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set houseNeighborhood = '" + houseNeighborhood + "' where engID = "
					+ engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateHouseStreet(int engID, String houseStreet) {
		try {
			System.out.println("update  Engineer set houseStreet = '" + houseStreet + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set houseStreet = '" + houseStreet + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateCityVillage(int engID, String cityVillage) {
		try {
			System.out.println("update  Engineer set cityVillage = '" + cityVillage + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set cityVillage = '" + cityVillage + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updatePhoneNum1(int engID, BigInteger phoneNum1) {
		try {
			System.out.println("update  Engineer set phoneNum1 = '" + phoneNum1 + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set phoneNum1 = '" + phoneNum1 + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateMailbox(int engID, String mailbox) {
		try {
			System.out.println("update  Engineer set mailbox = '" + mailbox + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set mailbox = '" + mailbox + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateEmail(int engID, String email) {
		try {
			System.out.println("update  Engineer set email = '" + email + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set email = '" + email + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateSecondaryBranch(int engID, String secondaryBranch) {
		try {
			System.out
					.println("update  Engineer set secondaryBranch = '" + secondaryBranch + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set secondaryBranch = '" + secondaryBranch + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateSecondaryAverage(int engID, Double secondaryAverage) {
		try {
			System.out.println(
					"update  Engineer set secondaryAverage = '" + secondaryAverage + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set secondaryAverage = '" + secondaryAverage + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateSecondarYear(int engID, Integer secondarYear) {
		try {
			System.out.println("update  Engineer set secondarYear = '" + secondarYear + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set secondarYear = '" + secondarYear + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateAreaAgencyIssued(int engID, String areaAgencyIssued) {
		try {
			System.out.println(
					"update  Engineer set areaAgencyIssued = '" + areaAgencyIssued + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set areaAgencyIssued = '" + areaAgencyIssued + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateUniversityName(int engID, String universityName) {
		try {
			System.out.println("update  Engineer set universityName = '" + universityName + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set universityName = '" + universityName + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateDateOfGraduation(int engID, String dateOfGraduation) {
		try {
			System.out.println(
					"update  Engineer set dateOfGraduation = '" + dateOfGraduation + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set dateOfGraduation = '" + dateOfGraduation + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateUniversityLocation(int engID, String universityLocation) {
		try {
			System.out.println(
					"update  Engineer set universityLocation = '" + universityLocation + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set universityLocation = '" + universityLocation + "' where engID = "
					+ engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateUniversityDegree(int engID, String universityDegree) {
		try {
			System.out.println(
					"update  Engineer set universityDegree = '" + universityDegree + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set universityDegree = '" + universityDegree + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateOverallAppraisals(int engID, String overallAppraisals) {
		try {
			System.out.println(
					"update  Engineer set overallAppraisals = '" + overallAppraisals + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set overallAppraisals = '" + overallAppraisals + "' where engID = "
					+ engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateGeneralSpecialization(int engID, String generalSpecialization) {
		try {
			System.out.println("update  Engineer set generalSpecialization = '" + generalSpecialization
					+ "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set generalSpecialization = '" + generalSpecialization
					+ "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateSpecificSpecialization(int engID, String specificSpecialization) {
		try {
			System.out.println("update  Engineer set specificSpecialization = '" + specificSpecialization
					+ "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set specificSpecialization = '" + specificSpecialization
					+ "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateUniversityID(int engID, Integer universityID) {
		try {
			System.out.println("update  Engineer set universityID = '" + universityID + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set universityID = '" + universityID + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateStartYear(int engID, Integer startYear) {
		try {
			System.out.println("update  Engineer set startYear = '" + startYear + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set startYears = '" + startYear + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateEndYear(int engID, Integer endYear) {
		try {
			System.out.println("update  Engineer set endYear = '" + endYear + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set endYear = '" + endYear + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateTotalCreditsHours(int engID, Integer totalCreditsHours) {
		try {
			System.out.println(
					"update  Engineer set totalCreditsHours = '" + totalCreditsHours + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set totalCreditsHours = '" + totalCreditsHours + "' where engID = "
					+ engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateTotalEngineeringHours(int engID, Integer totalEngineeringHours) {
		try {
			System.out.println("update  Engineer set totalEngineeringHours = '" + totalEngineeringHours
					+ "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set totalEngineeringHours = '" + totalEngineeringHours
					+ "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateNameOfGraduationProject(int engID, String nameOfGraduationProject) {

		try {
			System.out.println("update  Engineer set nameOfGraduationProject = '" + nameOfGraduationProject
					+ "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set nameOfGraduationProject = '" + nameOfGraduationProject
					+ "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateOfficeID(int engID, Integer officeID) {
		try {
			System.out.println("update  Engineer set officeID = '" + officeID + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set officeID = '" + officeID + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateTransactionID(int engID, Integer transactionID) {
		try {
			System.out.println("update  Engineer set transactionID = '" + transactionID + "' where engID = " + engID);
			connectDB();
			ExecuteStatement(
					"update  Engineer set transactionID = '" + transactionID + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateSalary(int engID, Double salary) {
		try {
			System.out.println("update  Engineer set salary = '" + salary + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set salary = '" + salary + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateDateOfReceiptOfSalary(int engID, String dateOfReceiptOfSalary) {

		try {
			System.out.println("update  Engineer set dateOfReceiptOfSalary = '" + dateOfReceiptOfSalary
					+ "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set dateOfReceiptOfSalary = '" + dateOfReceiptOfSalary
					+ "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateAnnualSubscriptionRepaymentDate(int engID, String annualSubscriptionRepaymentDate) {

		try {
			System.out.println("update  Engineer set annualSubscriptionRepaymentDate = '"
					+ annualSubscriptionRepaymentDate + "' where engID = " + engID);
			connectDB();
			ExecuteStatement("update  Engineer set annualSubscriptionRepaymentDate = '"
					+ annualSubscriptionRepaymentDate + "' where engID = " + engID + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void deleteRow(Engineer row) {
		// TODO Auto-generated method stub

		try {
			System.out.println("delete from  Engineer where engID=" + row.getEngID() + ";");
			connectDB();
			ExecuteStatement("delete from  Engineer where engID=" + row.getEngID() + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void showDialog(Window owner, Modality modality, TableView<Engineer> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
		// Label modalityLabel = new Label(modality.toString());

		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Engineer row : dataList) {
				deleteRow(row);
				table.refresh();
			}
			table.getItems().removeAll(dataList);
			stage.close();

		});

		Button noButton = new Button("Cancel");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
	}
}
