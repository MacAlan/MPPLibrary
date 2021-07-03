package addMember;

import Classes.*;
import DatabaseProvider.DatabaseProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddMemberController {
	@FXML private TextField first_name;
	@FXML private TextField last_name;
	@FXML private TextField street;
	@FXML private TextField city;
	@FXML private TextField state;
	@FXML private TextField zip;
	@FXML private TextField phone_number;
	@FXML private Label status;
	
	
	@FXML
	private void addMember(ActionEvent event) {
		String fName = first_name.getText();
		String lName= last_name.getText();
		String str= street.getText();
		String cit = city.getText();
		String st=state.getText();
		String zp =zip.getText();
		String phoneN= phone_number.getText();
		
		if(fName.isEmpty()||lName.isEmpty()||str.isEmpty()||cit.isEmpty()||st.isEmpty()||zp.isEmpty()||phoneN.isEmpty()) {
			status.setText("Fill in all the fields");
		}
		else {
			/*DatabaseProvider.addLibraryMember(fName,lName,phoneN,str,cit,st,zp);
			status.setText("User is added to database");
			first_name.clear();
			last_name.clear();
			street.clear();
			city.clear();
			state.clear();
			zip.clear();
			phone_number.clear();
*/
		}
	}
}
	
	
