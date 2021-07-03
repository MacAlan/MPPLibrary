package mainBoard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.Main;

public class MainBoardController implements Initializable {
	@FXML private Label welcome;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcome.setText("Welcome "+Main.user.getFirstname());
	} 
	
	@FXML
    private void addMember(ActionEvent event) throws Exception {
		System.out.println("add library member implement here !");
		Utility.showAddMember();
	}
	
	@FXML
    private void checkoutBook(ActionEvent event) throws Exception {
		System.out.println("checkout book implement here !");
		Utility.showChechout();
	}
	
	@FXML
    private void addCopyBook(ActionEvent event) throws Exception {
		System.out.println("add copy of a existing book implement here !");
	}
	@FXML
	private void addBook(ActionEvent event) throws Exception {
		System.out.println("add copy of a existing book implement here !");
		Utility.showAddBooks();
	}

}
