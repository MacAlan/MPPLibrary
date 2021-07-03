package Classes;

class Person
{
    private long id;        
    private String firstname;       
    private String lastname;        
    private String phone;       
    private Address address;        

    public Person() {
    }
    public Person(long id, String firstname, String lastname, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Person(long id, String firstname, String lastname, String phone, Address address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
    }

    public Person(long id, String firstname, String lastname, String phone, String street, String city, String state, String zip) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = new Address (street, city, state, zip);
    }
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person id(long id) {
        setId(id);
        return this;
    }

    public Person firstname(String firstname) {
        setFirstname(firstname);
        return this;
    }

    public Person lastname(String lastname) {
        setLastname(lastname);
        return this;
    }

    public Person phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Person address(Address address) {
        setAddress(address);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
   
}
