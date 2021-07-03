package mainBoard;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Classes.role;
import Utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Main;

public class MainBoardController implements Initializable {
	@FXML private GridPane greadpane;
	@FXML private Label uniqid;
	@FXML private Label fname;
	@FXML private Label lname;
	@FXML private Label phone;
	@FXML private Button addMember;
	@FXML private Button checkoutBook;
	@FXML private Button addCopyBook;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		uniqid.setText(""+Main.user.getId());
		fname.setText(Main.user.getFirstname());
		lname.setText(Main.user.getLastname());
		phone.setText(Main.user.getPhone());
		
		List<role> roles = Main.user.getRole();
		System.out.println("ROLES ARE:"+roles);
		if(roles.contains(role.admin)) {
			System.out.println("has admin!");
			addCopyBook.setVisible(true);
		}
		if(roles.contains(role.librarian)) {
			System.out.println("has library!");
			checkoutBook.setVisible(true);
		}
	} 
	
	@FXML
    private void addMember(ActionEvent event) throws Exception {
		System.out.println("add library member implement here !");
		Utility.showAddMember();
	}
	
	@FXML
    private void checkoutBook(ActionEvent event) throws Exception {
		System.out.println("checkout book implement here !");
	}
	
	@FXML
    private void addCopyBook(ActionEvent event) throws Exception {
		System.out.println("add copy of a existing book implement here !");
	}
}