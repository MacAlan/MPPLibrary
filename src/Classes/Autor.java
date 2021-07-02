package Classes;

class Autor extends Person
{
    private String biography;       

    public Autor() {
        super();
    }

    public Autor(long id, String firstname, String lastname, String phone, Address address,String biography) {
//        super(id, firstname, lastname, phone, address);
        this.biography = biography;
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Autor biography(String biography) {
        setBiography(biography);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " biography='" + getBiography() + "'" +
            "}";
    }
 
}
