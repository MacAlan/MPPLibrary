package Classes;

import java.time.LocalDate;

public class CheckoutEntry {
    private final LocalDate dueDate;       
    private final LocalDate checkoutDate;      
    private final BookCopy book;
    private int member;

    public CheckoutEntry(LocalDate dueDate, LocalDate checkoutDate, BookCopy book) {
        this.dueDate = dueDate;
        this.checkoutDate = checkoutDate;
        this.book = book;
    }

    public CheckoutEntry(LocalDate dueDate, LocalDate checkoutDate, BookCopy book, int member) {
        this.dueDate = dueDate;
        this.checkoutDate = checkoutDate;
        this.book = book;
        this.member=member;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
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

    @Override
    public String toString() {
        return "CheckoutEntry{" +
                "dueDate=" + dueDate +
                ", checkoutDate=" + checkoutDate +
                ", book=" + book +
                ", member=" + member +
                '}';
    }
}
