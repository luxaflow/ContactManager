/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class Controller {

    public AnchorPane view(AnchorPane rootPane, String view){

        String path = "/view/" + view + ".fxml";

        AnchorPane pane = null;

        try {

            pane = FXMLLoader.load(getClass().getResource(path));
            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pane;
    }

}
