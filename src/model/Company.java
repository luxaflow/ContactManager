/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package model;

import core.iModel;

public class Company extends Contact implements iModel {

    public Company(String name){
        this.name = name;
        this.type = "company";
    }
}
