/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package controller;

import core.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Contact;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField mobileTextField;
    @FXML private Text msgText;
    @FXML private TextField searchTextField;

    @FXML private TableView contactsTableView;
    @FXML private TableColumn<Contact, String> nameTableColumn;
    @FXML private TableColumn<Contact, String> emailTableColumn;
    @FXML private TableColumn<Contact, String> phoneTableColumn;
    @FXML private TableColumn<Contact, String> mobileTableColumn;

    private User user;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        //Set column values
        this.nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.phoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.mobileTableColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        // Make Column Cells editable
        this.contactsTableView.setEditable(Boolean.TRUE);
        this.emailTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.phoneTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.mobileTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.listContacts();
    }

    public void formView() {

        this.view(this.rootPane,"contactForm");
    }

    public void deleteContact(){

        Contact contact = (Contact) this.contactsTableView.getSelectionModel().getSelectedItem();

        if (contact.delete()){
            this.listContacts();
        }
    }

    public void updateEmail(TableColumn.CellEditEvent value){

        Contact contactSelected = (Contact) this.contactsTableView.getSelectionModel().getSelectedItem();

        contactSelected.setEmail(value.getNewValue().toString());

        contactSelected.update();
    }

    public void updatePhone(TableColumn.CellEditEvent value){

        Contact contactSelected = (Contact) this.contactsTableView.getSelectionModel().getSelectedItem();

        contactSelected.setPhone(value.getNewValue().toString());

        contactSelected.update();
    }

    public void updateMobile(TableColumn.CellEditEvent value){

        Contact contactSelected = (Contact) this.contactsTableView.getSelectionModel().getSelectedItem();

        contactSelected.setMobile(value.getNewValue().toString());

        contactSelected.update();
    }

    public void listContacts(){

        this.searchTextField.setText("");

        Contact contact = new Contact();

        this.contactsTableView.setItems(FXCollections.observableArrayList(contact.getAll()));
    }

    public void searchContacts(){

        String searchTextField = this.searchTextField.getText();

        Contact contact = new Contact();

        this.contactsTableView.setItems(FXCollections.observableArrayList(contact.search(searchTextField)));
    }

    public void getProfile(){

        this.user = new User();

        this.user.get();

        this.firstNameTextField.setText(this.user.getFirstName());
        this.lastNameTextField.setText(this.user.getLastName());
        this.emailTextField.setText(this.user.getEmail());
        this.phoneTextField.setText(this.user.getPhone());
        this.mobileTextField.setText(this.user.getMobile());

    }

    public void updateProfile(){

        this.user = new User();

        this.user.get();

        this.user.setFirstName(this.firstNameTextField.getText());
        this.user.setLastName(this.lastNameTextField.getText());
        this.user.setEmail(this.emailTextField.getText());
        this.user.setPhone(this.phoneTextField.getText());
        this.user.setMobile(this.mobileTextField.getText());

        if(this.user.update()){

            this.msgText.setText("Your profile has been updated!");

            Timeline tl = new Timeline(new KeyFrame(
                    Duration.millis(2500),
                    ae -> this.msgText.setText("")));
            tl.play();
        }
    }
}
