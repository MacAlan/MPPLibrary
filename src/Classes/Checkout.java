package Classes;
import java.util.*;

public class Checkout {
    private final String person_id;
    public List<CheckoutEntry> CheckoutEntries;

    public Checkout(String person_id, List<CheckoutEntry> checkoutEntries) {
        this.person_id = person_id;
        CheckoutEntries = checkoutEntries;
    }

    public String getPerson() {
        return this.person_id;
    }



}
