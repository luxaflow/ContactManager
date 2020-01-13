/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package model;

import core.Database;
import core.iModel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person extends Contact implements iModel {

    private String firstName;
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName){

        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + " " + lastName;
        this.type = "person";

    }

    @Override
    public Boolean create(){

        String query = "INSERT INTO contacts (" +
                "name, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "phone, " +
                "mobile," +
                "type) " +
                "VALUES (?,?,?,?,?,?,?)";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.name);
            stmt.setString(2, this.firstName);
            stmt.setString(3, this.lastName);
            stmt.setString(4, this.email);
            stmt.setString(5, this.phone);
            stmt.setString(6, this.mobile);
            stmt.setString(7, this.type);

            Integer status = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if(status > 0){

                return Boolean.TRUE;

            }

        } catch (SQLException e){

            e.printStackTrace();

        }

        return Boolean.FALSE;
    }

       @Override
        public void get() {

            String query = "SELECT * FROM contacts WHERE id=?";

            try {

                this.db = new Database();

                Connection conn =  this.db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, this.id);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    this.name = rs.getString("name");
                    this.firstName = rs.getString("first_name");
                    this.lastName = rs.getString("last_name");
                    this.email = rs.getString("email");
                    this.phone = rs.getString("phone");
                    this.mobile = rs.getString("mobile");

                }

                stmt.close();
                conn.close();

            } catch (SQLException e){
                e.printStackTrace();
            }

        }
}