package Classes;

import java.util.*;

public class LibraryMember extends Person
{
    private List<role> role;        
    private Credential credential;      

    public LibraryMember(long id, String firstname, String lastname, String phone, Address address, List<role> role, Credential credential ) {
     super( id,  firstname,  lastname,  phone,  address);
     this.role = role;
     this.credential = credential;
    }

    public LibraryMember(long id, String firstname, String lastname, String phone, String address, List<role> role, Credential credential ) {
        super( id,  firstname,  lastname,  phone,  null);
        this.role = new LinkedList<>();
        this.role.addAll(role);
        this.credential = credential;
       }

    public LibraryMember(long id, String firstname, String lastname, String phone, String street, String city, String state, String zip , List<role> role, Credential credential) {
        super(id, firstname, lastname, phone, street, city, state, zip);
        this.role = role;
        this.credential = credential;
    }

    public LibraryMember(long id, String firstname, String lastname, String phone, String street, String city, String state, String zip , role role, Credential credential) {
        super(id, firstname, lastname, phone, street, city, state, zip);
        this.role = new LinkedList<>();
        this.role.add(role);
        this.credential = credential;
    }

    public void addRole(role role){
        this.role.add(role);
    }

    public void removeRole(role role){
        this.role.remove(role);
    }

    public Credential getCredential() {
        return this.credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
    public void setCredential(String user, String password) {
        this.credential =  new Credential(user,password);
    }

    public String getPassword(){
        return credential.getPassword();
    }

	public List<role> getRole() {
		return role;
	}
}
