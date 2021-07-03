package Classes;

class Person
{
    private long id;        
    private String firstname;       
    private String lastname;        
    private String phone;       
    private String street;
    private String city;
    private String state;
    private String zip;

    public Person(long id, String firstname, String lastname, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Person(long id, String firstname, String lastname, String phone, String street, String city, String state, String zip) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

   
}
