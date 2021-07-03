package sample.addBooks;


import Classes.Autor;
import DatabaseProvider.DatabaseProvider;
import DatabaseProvider.InsertDB;
import DatabaseProvider.SelectDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddBooksController {

       private Main main;
       @FXML
       private TextField ISBN;
       @FXML
       private TextField title;
       @FXML
       private TextField borrowDays;
       @FXML
       private TextField numberCopies;
       @FXML
       private Label error;
       @FXML
       private ChoiceBox<String> authors;
       @FXML
       private Button selectBtn;
    @FXML
    private TextField CountCopies;
    @FXML
    private Button addCopies;

       @FXML
       private TableView<BooksDto> copyBook;
       @FXML
       TableColumn<BooksDto, String> ISBN1 = new TableColumn("ISBN1");
       @FXML
       TableColumn<BooksDto, String> bookTitle = new TableColumn("bookTitle");
       @FXML
       TableColumn<BooksDto, String> id = new TableColumn("id");
       @FXML
       TableColumn<BooksDto, String>  author= new TableColumn("author");

    @FXML
    TableColumn<BooksDto, String>  copies= new TableColumn("copies");


       @FXML
       private TextField isbnSearch;

    @FXML
    private Button search;
    @FXML
    private Button addCopy;


    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private TextField state;
    @FXML private TextField zip;
    @FXML private TextField phone_number;
    @FXML private Label status;

       private InsertDB insertDB;
       private SelectDB selectDB;

    @FXML
    private void addMember(ActionEvent event) {
        String fName = first_name.getText();
        String lName= last_name.getText();
        String str= street.getText();
        String cit = city.getText();
        String st=state.getText();
        String zp =zip.getText();
        String phoneN= phone_number.getText();

        if(fName.isEmpty()||lName.isEmpty()||str.isEmpty()||cit.isEmpty()||st.isEmpty()||zp.isEmpty()||phoneN.isEmpty()) {
            status.setText("Fill in all the fields");
        }
        else {
            DatabaseProvider.addLibraryMember(fName,lName,phoneN,str,cit,st,zp);
            status.setText("User is added to database");
            first_name.clear();
            last_name.clear();
            street.clear();
            city.clear();
            state.clear();
            zip.clear();
            phone_number.clear();

        }
    }

    @FXML
    public void initialize(){
        System.out.println("1============================");
        ObservableList<String> authorsList = FXCollections.observableArrayList();
        System.out.println("2============================");
        List<Autor> preAutors = DatabaseProvider.getMAuthors();
        System.out.println("3============================" + preAutors.size());
        for (Autor a: preAutors ) {
            String b = new String(a.toString());
            System.out.println("==================");
            System.out.println(a.toString());
            authorsList.add(b);
            System.out.println("==================");
        }
        authors.getItems().clear();
        authors.setItems(authorsList);




    }

    @FXML
    private void insertCopy(){

        copyBook.getItems().clear();
        ISBN1.setCellValueFactory(new PropertyValueFactory<BooksDto,String>("ISBN1"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<BooksDto,String>("title"));
        id.setCellValueFactory(new PropertyValueFactory<BooksDto,String>("id"));
        author.setCellValueFactory(new PropertyValueFactory<BooksDto,String>("author"));
        copies.setCellValueFactory(new PropertyValueFactory<BooksDto,String>("copies"));
        List<BooksDto> list = DatabaseProvider.getBookByISBN1(isbnSearch.getText().trim());
        copyBook.getItems().addAll(list);
    }
    @FXML
    private void insertCountCopy(){
        for (int i = 0; i < Integer.parseInt(CountCopies.getText().trim()); i++) {
            DatabaseProvider.InsertCopyBooks(DatabaseProvider.selectBookbyISBN(ISBN1.getText().trim()));
        }

        insertCopy();


    }

    @FXML
    private void insertBook()  {
        try {
            System.out.println(authors.getValue());
            String str = authors.getValue();
            int sepPos = str.lastIndexOf(":");
            if (sepPos == -1) {
                System.out.println("");
            }
            Integer authorId = Integer.valueOf(str.substring(0, sepPos).trim());
            System.out.println(title.getText().trim());
            System.out.println(ISBN.getText().trim());
            System.out.println(authorId);
            System.out.println(Integer.parseInt(borrowDays.getText().trim()));
            System.out.println(Integer.parseInt(numberCopies.getText().trim()));

            DatabaseProvider.InsertBooks(title.getText().trim(), ISBN.getText().trim(), authorId, Integer.parseInt(borrowDays.getText().trim()), 1);

            for (int i = 0; i < Integer.parseInt(numberCopies.getText().trim()); i++) {
                DatabaseProvider.InsertCopyBooks(DatabaseProvider.selectBookbyISBN(ISBN.getText().trim()));
            }

            handleSuccesfully("Successfully");
        } catch (Exception e){
            handleError(e);
        }

    }

    private void handleSuccesfully(String e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, e, ButtonType.CLOSE);
        alert.show();
    }

    private void handleError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
        alert.show();
    }




}
