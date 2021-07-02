package sample;

import Classes.LibraryMember;
import Utility.Utility;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static LibraryMember user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Utility.showLogin();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
