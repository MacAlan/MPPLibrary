package login;

import Classes.*;
import DatabaseProvider.DatabaseProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.MainController;

public class Login {
    private LibraryMember user;
    @FXML private TextField name;
    @FXML private PasswordField password;

    @FXML
    private void login(ActionEvent event) {
        String inputName = name.getText();
        String inputPassword = password.getText();

        user = DatabaseProvider.getMemberById(inputName);
        if (user.getCredential().getPassword().equals(inputPassword)){
            System.out.println("Success");
            MainController.user = user;
        }
        else
        {
            System.out.println("Fail");
        }
    }
}