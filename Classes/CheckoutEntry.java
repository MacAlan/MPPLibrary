package Classes;

import java.time.LocalDate;

public class CheckoutEntry {
    private final LocalDate dueDate;       
    private final LocalDate checkoutDate;      
    private final BookCopy book;  

    public CheckoutEntry(LocalDate dueDate, LocalDate checkoutDate, BookCopy book) {
        this.dueDate = dueDate;
        this.checkoutDate = checkoutDate;
        this.book = book;
    }


    public LocalDate getDueDate() {
        return this.dueDate;
    }


    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }


    public BookCopy getBook() {
        return this.book;
    }

}
