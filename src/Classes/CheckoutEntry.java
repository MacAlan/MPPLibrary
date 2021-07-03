package Classes;

import java.time.LocalDate;

public class CheckoutEntry {
    private final LocalDate dueDate;       
    private final LocalDate checkoutDate;      
    private final BookCopy book;
    private LibraryMember member;

    public CheckoutEntry(LocalDate dueDate, LocalDate checkoutDate, BookCopy book) {
        this.dueDate = dueDate;
        this.checkoutDate = checkoutDate;
        this.book = book;
    }

    public CheckoutEntry(LocalDate dueDate, LocalDate checkoutDate, BookCopy book, LibraryMember member) {
        this.dueDate = dueDate;
        this.checkoutDate = checkoutDate;
        this.book = book;
        this.member=member;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
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

}
