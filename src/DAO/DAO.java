package DAO;

import java.util.List;

import Classes.Person;

public interface DAO {
	public List<?> getAll();
	public Object findById();
	public Object getByName(String name);
}
