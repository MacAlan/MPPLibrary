package login;

import Classes.Address;
import Classes.Person;
import DAO.PersonDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Login {
	private PersonDao personDao;
	@FXML private TextField name;
	@FXML private PasswordField password;
	
	@FXML
    private void login(ActionEvent event) {
		String inputName = name.getText();
		String inputPassword = password.getText();
		System.out.println("Inserted: "+inputName+" "+inputPassword);
		
		personDao = new PersonDao();
		Person person = personDao.getByName(inputName);
		
		if(person != null && person.getCredential().getPassword().equals(inputPassword)) {
			System.out.println("Success");
		} else {
			System.out.println("Fail");
		}
    }
}
