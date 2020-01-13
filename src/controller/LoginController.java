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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class LoginController extends Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private TextField loginPwdTextField;
    @FXML private Label msgLabel;
    @FXML private TextField firstTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField mobileTextField;
    @FXML private PasswordField pwdPasswordField;
    @FXML private PasswordField confirmPwdPasswordField;

    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(){

        String pwd = loginPwdTextField.getText();

        if(pwd.isEmpty()){

            msgLabel.setText("Password is required!");

        } else {

            this.user = new User();

            user.get();

            if(user.comparePassword(pwd)){

                this.view(this.rootPane,"contactManager");

            } else {

                msgLabel.setText("Incorrect password!");
            }
        }
    }

     public void createUser(){

        String username = System.getProperty("user.name");
        String firstName = firstTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String mobile = mobileTextField.getText();
        String pwd = pwdPasswordField.getText();
        String confirmPwd = confirmPwdPasswordField.getText();

        if(Stream.of(firstName, lastName, email, phone, mobile, pwd, confirmPwd).anyMatch(String::isEmpty)){

            msgLabel.setText("All fields are required!");

        } else {

            User user = new User();

            user.get();

            if(user.getId() != null) {

                msgLabel.setText("Username or Email already in use!");

            } else {

                if (pwd.equals(confirmPwd) ){

                    user.setUsername(username);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setMobile(mobile);
                    user.setPassword(pwd);

                    if(user.create()){

                        this.loginView();

                    } else {

                        msgLabel.setText("Something went wrong!");
                    }
                } else {

                    msgLabel.setText("Passwords do not match!");
                }
            }
        }
    }

    public void loginView(){

        this.view(this.rootPane, "login");
    }
}