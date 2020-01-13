/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package controller;

import core.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Company;
import model.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController extends Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private MenuButton typeMenuButton;
    @FXML private Label firstNameLabel;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField mobileTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        this.firstNameTextField.setDisable(Boolean.TRUE);
        this.lastNameTextField.setDisable(Boolean.TRUE);
        this.emailTextField.setDisable(Boolean.TRUE);
        this.phoneTextField.setDisable(Boolean.TRUE);
        this.mobileTextField.setDisable(Boolean.TRUE);

    }

    public void setPerson(){

        this.typeMenuButton.setText("Person");
        this.firstNameLabel.setText("First Name");

        this.firstNameTextField.setDisable(Boolean.FALSE);
        this.lastNameTextField.setDisable(Boolean.FALSE);
        this.emailTextField.setDisable(Boolean.FALSE);
        this.phoneTextField.setDisable(Boolean.FALSE);
        this.mobileTextField.setDisable(Boolean.FALSE);

    }

    public void setCompany(){

        this.typeMenuButton.setText("Company");
        this.firstNameLabel.setText("Company name");

        this.firstNameTextField.setDisable(Boolean.FALSE);
        this.lastNameTextField.setDisable(Boolean.TRUE);
        this.emailTextField.setDisable(Boolean.FALSE);
        this.phoneTextField.setDisable(Boolean.FALSE);
        this.mobileTextField.setDisable(Boolean.FALSE);

    }

    public void contactCreate(){

        String first_name = this.firstNameTextField.getText();
        String last_name = this.lastNameTextField.getText();
        String email = this.emailTextField.getText();
        String phone = this.phoneTextField.getText();
        String mobile = this.mobileTextField.getText();
        String type = this.typeMenuButton.getText();

        Boolean status = null;

        if (type.equals("Person")) {

            Person person = new Person(first_name, last_name);

            person.setEmail(email);
            person.setPhone(phone);
            person.setMobile(mobile);

            status = person.create();

        } else if(type.equals("Company")){

            Company company = new Company(first_name);

            company.setEmail(email);
            company.setPhone(phone);
            company.setMobile(mobile);

            status = company.create();
        }

        if (status){

            this.backToManager();
        }
    }

    public void backToManager() {

      this.view(this.rootPane, "contactManager");
    }
}
