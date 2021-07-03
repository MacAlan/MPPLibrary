package Classes;
import java.util.*;

public class Checkout {
    private final Person person;      
    private List<CheckoutEntry> CheckoutEntries;   


    public Checkout(Person person) {
        this.person = person;
    }

    public void addEntry(CheckoutEntry entry){
        CheckoutEntries.add(entry);
    }


    public Person getPerson() {
        return this.person;
    }



}
