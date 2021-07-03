package login;

import Classes.*;
import DatabaseProvider.DatabaseProvider;
import mainBoard.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;

public class LoginController {
    private LibraryMember user;
    @FXML private TextField uid;
    @FXML private PasswordField password;
    @FXML private Label error;

    @FXML
    private void login(ActionEvent event) throws Exception {
        String inputId = uid.getText();
        String inputPassword = password.getText();
        
        if(!inputId.equals(null) && !inputId.equals("") && !inputPassword.equals(null) && !inputPassword.equals("")) {
        	if(!isNumeric(inputId)) {
        		error.setText("Please insert number in login id");
        	} else {
        		user = DatabaseProvider.getMemberById(inputId);
                if (user != null && user.getCredential().getPassword().equals(inputPassword)) {
                    Main.user = user;
                    Utility.hideLogin();
                    Utility.showMainWindow();
                } else {
                    error.setText("Authentication is failed");
                }
        	}
        } else {
        	error.setText("Insert Id and Password");
        }
    }
    
    public static boolean isNumeric(String str) { 
	  try {  
	    Double.parseDouble(str);  
	    return true;
	  } catch(NumberFormatException e){  
	    return false;  
	  }  
	}
}
