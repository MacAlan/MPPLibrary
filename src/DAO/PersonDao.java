package DAO;

import java.util.ArrayList;
import java.util.List;

import Classes.Credential;
import Classes.Person;

public class PersonDao implements DAO {

	// Erase after db complete ! just for test
	private List<Person> persons = new ArrayList<>();
	
	@Override
	public List<Person> getAll() {
		return null;
	}

	@Override
	public Person getByName(String name) {
		// Erase after db complete ! just for test
		createDummyUserData();
		Person result = null;
		for(Person person : persons) {
			if(person.getFirstname().equals(name)) {
				result = person;
			}
		}
		return result;
	}

	@Override
	public Person findById() {
		return null;
	}
	
	// Erase after db complete ! just for test
	private void createDummyUserData() {
		persons.add(new Person(1, "One", "1", "1", null));
		persons.add(new Person(2, "Two", "2", "2", null));
		persons.add(new Person(3, "One", "3", "3", null));
	}
}
