/**
* COMP 580 Final Project (Spring 2017)
* A School Library Database System using SQLite, support feature to searching,
* borrowing, and returning books.
*
* @author  Chi Ho Lee, Bijay Maharjan
* @since   2017-4-26
*/
package sample;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private static BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library System");
		
		showMainView();
		//showMainItems();
	}

	// MainView is the top panel with a Home button and Library name.Faculty
    private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("addBooks/AddBooks.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
/*	// MainItem is the center section with 3 buttons
	public static void showMainItems() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("addBooks/AddBooks.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
	}*/
		public static void main(String[] args) {
		launch(args);
	}
}
