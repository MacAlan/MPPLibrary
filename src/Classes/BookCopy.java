package Classes;

import java.util.*;
public class BookCopy {
    private final Books book;
    private final int copyNumber;     
    private final int count;   


    public BookCopy(Books book, int copyNumber, int count) {
        this.book = book;
        this.copyNumber = copyNumber;
        this.count = count;
    }

    public BookCopy(String title, String ISBN, boolean availability, int days,List<Autor> autors, int copyNumber, int count) {
        this.book = new Books(title, ISBN, availability, days, autors);
        this.copyNumber = copyNumber;
        this.count = count;
    }

    public BookCopy(String title, String ISBN, boolean availability, int days,Autor autor, int copyNumber, int count) {
        this.book = new Books(title, ISBN, availability, days, autor);
        this.copyNumber = copyNumber;
        this.count = count;
    }


    public Books getBook() {
        return this.book;
    }


    public int getCopyNumber() {
        return this.copyNumber;
    }



    public int getCount() {
        return this.count;
    }

}