package addBooks;

import javafx.beans.property.SimpleStringProperty;

public class BooksDto {

    SimpleStringProperty id;
    SimpleStringProperty ISBN1;
    SimpleStringProperty title;
    SimpleStringProperty author;
    SimpleStringProperty copies;

    public BooksDto(String id, String ISBN1, String title, String author,String copies) {
        this.id = new SimpleStringProperty(id);
        this.ISBN1 = new SimpleStringProperty(ISBN1);
        this.author = new SimpleStringProperty(author);
        this.title = new SimpleStringProperty(title);
        this.copies = new SimpleStringProperty(copies);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getISBN1() {
        return ISBN1.get();
    }

    public SimpleStringProperty ISBN1Property() {
        return ISBN1;
    }

    public void setISBN1(String ISBN1) {
        this.ISBN1.set(ISBN1);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getCopies() {
        return copies.get();
    }

    public SimpleStringProperty copiesProperty() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies.set(copies);
    }
}
