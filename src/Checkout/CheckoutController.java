package Checkout;

import Classes.*;
import Classes.checkoutlist;
import DatabaseProvider.DatabaseProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CheckoutController {

    @FXML private Text checkoutDate;
    @FXML private Text dueDateLabel;
    @FXML private TabPane mainTab;
    @FXML private TableView<checkoutlist> CheckOutTable;
    @FXML
    TableColumn<checkoutlist, String> ISBN = new TableColumn("ISBN");
    @FXML
    TableColumn<checkoutlist, String> bookTitle = new TableColumn("bookTitle");
    @FXML TableColumn<checkoutlist, String> member = new TableColumn("member");
    @FXML private Text searchResult;
    @FXML private Text searchResultText;
    @FXML private TextField inputMID;
    @FXML private TextField inputISBN;
    @FXML private TextField registerISBN;
    @FXML private TextField registerMID;
    @FXML private ListView<String> CheckoutBooks;
    @FXML private Tab checkout;
    private  boolean goToReg = false;

    private List<BookCopy> books = new LinkedList<>();


    /***/
    public void mainTabLoad(){
        toDay();
    }


    @FXML
    public  void toDay(){
        checkoutDate.setText(LocalDate.now().toString());
        dueDateLabel.setText("some text");
    }

    @FXML
    public void initialize(){
        CheckOutTable.getItems().clear();
        ISBN.setCellValueFactory(new PropertyValueFactory<checkoutlist,String>("ISBN"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<checkoutlist,String>("bookTitle"));
        member.setCellValueFactory(new PropertyValueFactory<checkoutlist,String>("member"));

        List<checkoutlist> list = DatabaseProvider.getAllCheckoutList();

        CheckOutTable.getItems().addAll(list);
    }


    @FXML
    public void clearCheckOutTable(){
        CheckOutTable.getItems().clear();
    }

    @FXML
    public void checkISBMMemberId(){

        if (inputISBN.getText().equals("") || inputMID.getText().equals("")){
            searchResult.setText("Input ISBN and MemberID");
        }
        else {
            LibraryMember member = DatabaseProvider.getMemberById(inputMID.getText());
            List<Books> books = DatabaseProvider.getBooksByISBN(inputISBN.getText());
            if (books.size()==0){
                searchResult.setText("No Books found by ISBN");
                System.out.println(books.size());
            }
            if (books.size()>0 && !books.get(0).getAvailability()){

                searchResult.setText("Books not available");

            }
            if (member==null){
                searchResult.setText("No Member found by ID");
            }
            if (books.size()>0 && member!=null && books.get(0).getAvailability()){
                goToReg=true;
                searchResult.setText("Found member " + member.toString() + " book : " +  books);
            }
        }
    }

    @FXML
    public void GoToRegCheckout(){
        if (goToReg){
            registerISBN.setText(inputISBN.getText());
            registerMID.setText(inputMID.getText());
            checkoutDate.setText(LocalDate.now().toString());
            BookCopy b = DatabaseProvider.getBookCopyByISBN(registerISBN.getText());
            dueDateLabel.setText(LocalDate.now().plusDays(b.getBook().getDays()).toString());
            books.add(b);
            CheckoutBooks.getItems().add(b.toString());
        }
        mainTab.getSelectionModel().select(2);
    }


    @FXML
    public void addBookToCheckout(){

        if(registerISBN.getText().equals("")){
            searchResultText.setText("Fill up ISBN");
        }
        else{
            BookCopy b = DatabaseProvider.getBookCopyByISBN(registerISBN.getText());
            if (b!=null && !b.getBook().getAvailability()){
                searchResultText.setText("Book not available");
            }else if (b==null){
                searchResultText.setText("Book not found");
            }
            else{
                books.add(b);
                CheckoutBooks.getItems().add(b.toString());
            }
        }
    }

    @FXML
    public void register(){
        searchResultText.setText("");
        if(registerMID.getText().equals("") ){
            searchResultText.setText("Input MemberID");
        }
        if (books.size()==0){
            searchResultText.setText("Add some Books");
        }
        if(!registerMID.getText().equals("") && books.size()>0){
            List<CheckoutEntry> list = new LinkedList<>();
            for (BookCopy a: books) {
                list.add(new CheckoutEntry(LocalDate.now().plusDays(a.getBook().getDays()), LocalDate.now(),a ));
                System.out.println(LocalDate.now().plusDays(a.getBook().getDays()));
                System.out.println(a);
            }
            Checkout c = new Checkout(registerMID.getText(),list);
            DatabaseProvider.addCheckout(c);
            books.clear();
            registerMID.setText("");
            registerISBN.setText("");
            CheckoutBooks.getItems().clear();
            dueDateLabel.setText("");
        }
    }
}
