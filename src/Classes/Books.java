package Classes;

import java.util.*;

class Books
{
    private String title;       
    private String ISBN;        
    private List<Autor> autors = new LinkedList<>();     
    private boolean availability;       
    private int days;       

    public Books() {
    }

    public Books(String title, String ISBN, boolean availability, int days,List<Autor> autors) {
        this.title = title;
        this.ISBN = ISBN;
        this.availability = availability;
        this.days = days;
        this.autors = autors;
    }
    public Books(String title, String ISBN, boolean availability, int days, Autor autor) {
        this.title = title;
        this.ISBN = ISBN;
        this.availability = availability;
        this.days = days;
        this.autors = new LinkedList<>();
        this.autors.add(autor);

    }
    public void addAutor(Autor autor){
        autors.add(autor);
    }

    public List<Autor> getAutors(){
        return this.autors;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailability() {
        return this.availability;
    }

    public boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }


    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", ISBN='" + getISBN() + "'" +
            ", autors='" + getAutors() + "'" +
            ", availability='" + isAvailability() + "'" +
            ", days='" + getDays() + "'" +
            "}";
    }
      
}
