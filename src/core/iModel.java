/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package core;

import java.util.ArrayList;

public interface iModel {

    Boolean create();

    Boolean delete();

    Boolean update();

    void get();

    ArrayList<?> getAll();

}
