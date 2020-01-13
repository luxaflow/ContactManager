/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

import core.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Database db = new Database();
        db.createDatabaseTables();


        //Send user to account creation page if no account for the user is found
        User user =  new User();
        user.get();

        Integer userId = user.getId();
        String view = "newUser";

        if (userId != null){
            view = "login";
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/" + view + ".fxml"));
        primaryStage.setTitle("Contact Manager");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }
}
