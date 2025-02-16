package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class Test extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FullDBTableEngTable dbTableEngTable = new FullDBTableEngTable();
		dbTableEngTable. data = new ArrayList<>();

		try {

			dbTableEngTable.getData();

			dbTableEngTable.dataList = FXCollections.observableArrayList(dbTableEngTable.data);

			dbTableEngTable.tableView(primaryStage);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
