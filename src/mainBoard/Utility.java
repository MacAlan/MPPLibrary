package mainBoard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utility extends Application {
	static Stage stage = new Stage();
	static Utility utility = new Utility();
	
	public static void showLogin() throws Exception {
		utility.start("/login/login.fxml", "login/login.css", 1200, 700);
	}
	
	public static void hideLogin() {
		stage.hide();
	}
	
	public static void showMainWindow() throws Exception {
		utility.start("/mainBoard/mainBoard.fxml", "mainBoard/mainBoard.css", 1200, 700);
	}
	
	public static void showAddMember() throws Exception {
		utility.start("/addMember/addMember.fxml", "addMember/addMember.css", 1200, 700);
	}

	public static void showChechout() throws Exception {
		utility.start("/Checkout/Checkout.fxml", "addMember/addMember.css", 1200, 700);
	}
	public static void showAddBooks() throws Exception {
		utility.start("/addBooks/AddBooks.fxml", "addMember/addMember.css", 1200, 700);
	}

	@Override
	public void start(Stage stage) throws Exception {
	}

	private void start(String view, String css, int width, int height) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(view));
		Scene scene = new Scene(root, width, height);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
}
