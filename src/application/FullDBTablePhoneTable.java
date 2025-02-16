package application;

import static javafx.stage.Modality.NONE;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.util.converter.BigIntegerStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * A JavaFX ConnectDB Application
 */
public class FullDBTablePhoneTable extends Application {
	/**
	 * @param args the command line arguments
	 * 
	 * 
	 */
	private ArrayList<Phone> data;

	private ObservableList<Phone> dataList;

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

			// convert data from arraylist to observable arraylist
			dataList = FXCollections.observableArrayList(data);

			// really bad method
			tableView(stage);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")

	private void tableView(Stage stage) {

		TableView<Phone> myDataTable = new TableView<Phone>();

		Scene scene = new Scene(new Group());
		stage.setTitle("Phone Table");
		stage.setWidth(550);
		stage.setHeight(500);

		Label label = new Label("Phone Table");
		label.setFont(new Font("Arial", 20));
		label.setTextFill(Color.BLUE);

		myDataTable.setEditable(true);
		myDataTable.setMaxHeight(200);
		myDataTable.setMaxWidth(408);

		// phone1
		TableColumn<Phone, BigInteger> phoneNum1Col = new TableColumn<Phone, BigInteger>("Phone Number 1");
		phoneNum1Col.setMinWidth(100);
		phoneNum1Col.setCellValueFactory(new PropertyValueFactory<Phone, BigInteger>("phoneNum1"));

		TableColumn<Phone, BigInteger> phoneNum2Col = new TableColumn<Phone, BigInteger>("Phone Number 2");
		phoneNum2Col.setMinWidth(100);
		phoneNum2Col.setCellValueFactory(new PropertyValueFactory<Phone, BigInteger>("phoneNum2"));

		phoneNum2Col.setCellFactory(TextFieldTableCell.forTableColumn(new BigIntegerStringConverter()));
		phoneNum1Col.setOnEditCommit((CellEditEvent<Phone, BigInteger> t) -> {
			((Phone) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhoneNum1(t.getNewValue());
			updatePhoneNum2(t.getRowValue().getPhoneNum2(), t.getNewValue());
		});

		TableColumn<Phone, BigInteger> landLineTelephoneCol = new TableColumn<Phone, BigInteger>("Land Line Telephone");
		landLineTelephoneCol.setMinWidth(100);
		landLineTelephoneCol.setCellValueFactory(new PropertyValueFactory<Phone, BigInteger>("landLineTelephone"));

		landLineTelephoneCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigIntegerStringConverter()));
		landLineTelephoneCol.setOnEditCommit((CellEditEvent<Phone, BigInteger> t) -> {
			((Phone) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhoneNum1(t.getNewValue());
			updatelandLineTelephone(t.getRowValue().getLandLineTelephone(), t.getNewValue());
		});

		myDataTable.setItems(dataList);

		myDataTable.getColumns().addAll(phoneNum1Col, phoneNum2Col, landLineTelephoneCol);

		final TextField addPhoneNum1 = new TextField();
		addPhoneNum1.setPromptText("Phone Number 1");
		addPhoneNum1.setMaxWidth(phoneNum1Col.getPrefWidth());

		final TextField addPhoneNum2 = new TextField();
		addPhoneNum2.setMaxWidth(phoneNum2Col.getPrefWidth());
		addPhoneNum2.setPromptText("Phone Number 2");

		final TextField addLandLineTelephone = new TextField();
		addLandLineTelephone.setMaxWidth(landLineTelephoneCol.getPrefWidth());
		addLandLineTelephone.setPromptText("Land Line Telephone");

		final Button addButton = new Button("Add");
		addButton.setOnAction((ActionEvent e) -> {
			Phone phone = new Phone(new BigInteger(addPhoneNum1.getText()), new BigInteger(addPhoneNum2.getText()),
					new BigInteger(addLandLineTelephone.getText()));
			dataList.add(phone);
			insertData(phone);
			addPhoneNum1.clear();
			addPhoneNum2.clear();
			addLandLineTelephone.clear();
		});

		final HBox hb = new HBox();

		final Button deleteButton = new Button("Delete");
		deleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Phone> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
			ArrayList<Phone> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				myDataTable.getItems().remove(row);
				deleteRow(row);
				myDataTable.refresh();
			});
		});

		hb.getChildren().addAll(addPhoneNum1, addPhoneNum2, addLandLineTelephone, addButton, deleteButton);
		hb.setSpacing(3);

		final Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction((ActionEvent e) -> {
			myDataTable.refresh();
		});

//		Button ownedNoneButton = new Button("Owned None");
//		ownedNoneButton.setOnAction(c -> );

		final Button clearButton = new Button("Clear All");
		clearButton.setOnAction((ActionEvent e) -> {
			showDialog(stage, NONE, myDataTable);

		});

		
		
		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(clearButton, refreshButton);
		hb2.setAlignment(Pos.CENTER_RIGHT);
		hb2.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(label, myDataTable, hb, hb2);
		// vbox.getChildren().addAll(label, myDataTable);
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
	}

	private void insertData(Phone phone) {
		try {
			System.out.println("Insert into phone (phoneNum1, phoneNum2, landLineTelephone) values ("
					+ phone.getPhoneNum1() + ", " + phone.getPhoneNum2() + ", " + phone.getLandLineTelephone() + ")");

			connectDB();
			ExecuteStatement("Insert into phone (phoneNum1, phoneNum2, landLineTelephone) values ("
					+ phone.getPhoneNum1() + ", " + phone.getPhoneNum2() + ", " + phone.getLandLineTelephone() + ");");
			con.close();
			System.out.println("Connection closed" + dataList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void getData() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String SQL;

		connectDB();
		System.out.println("Connection established");

		SQL = "SELECT phoneNum1, phoneNum2, landLineTelephone FROM phone ORDER BY phoneNum1";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			data.add(new Phone(new BigInteger(rs.getString(1)), new BigInteger(rs.getString(2)),
					new BigInteger(rs.getString(3))));
		}

		rs.close();
		stmt.close();

		con.close();
		System.out.println("Connection closed " + data.size());
	}

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

	public void updatePhoneNum2(BigInteger phoneNum1, BigInteger phoneNum2) {
		try {
			System.out.println("update  Engineer set phoneNum2 = '" + phoneNum2 + "' where engID = " + phoneNum1);
			connectDB();
			ExecuteStatement("update  Engineer set phoneNum2 = '" + phoneNum2 + "' where engID = " + phoneNum1 + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updatelandLineTelephone(BigInteger phoneNum1, BigInteger landLineTelephone) {
		try {
			System.out
					.println("update  Engineer set phoneNum2 = '" + landLineTelephone + "' where engID = " + phoneNum1);
			connectDB();
			ExecuteStatement(
					"update  Engineer set phoneNum2 = '" + landLineTelephone + "' where engID = " + phoneNum1 + ";");
			con.close();
			System.out.println("Connection closed.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void deleteRow(Phone phone) {
		try {
			System.out.println("delete from phone where phoneNum1=" + phone.getPhoneNum1() + ";");
			connectDB();
			ExecuteStatement("delete from phone where phoneNum1=" + phone.getPhoneNum1() + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void showDialog(Window owner, Modality modality, TableView<Phone> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
		// Label modalityLabel = new Label(modality.toString());

		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Phone row : dataList) {
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
