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
import java.util.ArrayList;
import java.util.Collections;

public class Contact implements iModel {

    protected Integer id;
    protected String name, email, phone, mobile, type;
    protected Database db;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Contact(){
        // Adds contact with type "unknown" as types are defined in the extended models
        this.type = "unknown";
    }

    public Boolean create(){

        String query = "INSERT INTO contacts (name, email, phone, mobile, type) VALUES (?,?,?,?,?)";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.name);
            stmt.setString(2, this.email);
            stmt.setString(3, this.phone);
            stmt.setString(4, this.mobile);
            stmt.setString(5, this.type);

            Integer status = stmt.executeUpdate();

            if (status > 0){
                return Boolean.TRUE;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    public void get(){

        String query = "SELECT FROM contacts WHERE id=?";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, this.id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                this.name = rs.getString("name");
                this.email = rs.getString("email");
                this.phone = rs.getString("phone");
                this.mobile = rs.getString(".mobile");
                this.type = rs.getString("type");

            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean update(){

        String query = "UPDATE contacts SET email=?, phone=?, mobile=? WHERE id=?";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.email);
            stmt.setString(2, this.phone);
            stmt.setString(3, this.mobile);

            stmt.setInt(4, this.id);

            Integer status = stmt.executeUpdate();

            if(status > 0){

                return Boolean.TRUE;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    public Boolean delete(){

        String query = "DELETE FROM contacts WHERE id = ?";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, this.id);

            Integer status = stmt.executeUpdate();

            if(status > 0) {

                return Boolean.TRUE;
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    @Override
    public ArrayList<Contact> getAll(){

        ArrayList<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM contacts";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                String type = rs.getString("type");

                if(type.equals("person")) {

                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");

                    Person person = new Person(first_name, last_name);

                    person.id = rs.getInt("id");
                    person.email = rs.getString("email");
                    person.phone = rs.getString("phone");
                    person.mobile = rs.getString("mobile");

                    contacts.add(person);

                } else if (type.equals("company")){
                    String name = rs.getString("name");

                    Company company = new Company(name);

                    company.id = rs.getInt("id");
                    company.email = rs.getString("email");
                    company.phone = rs.getString("phone");
                    company.mobile = rs.getString("mobile");

                    contacts.add(company);
                }
            }

            Collections.sort(contacts, (c1, c2) -> c1.getName().compareTo(c2.getName()));

            stmt.close();
            conn.close();

        } catch (SQLException e){

            e.printStackTrace();
        }

        return contacts;
    }

    public static ArrayList<Contact> search(String value){

        String like_value = "%"+value+"%";

        ArrayList<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM contacts WHERE " +
                "name LIKE ? OR " +
                "first_name LIKE ? OR " +
                "last_name LIKE ? OR " +
                "email LIKE ?";

        try{

            Database db = new Database();

            Connection conn =  db.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, like_value);
            stmt.setString(2, like_value);
            stmt.setString(3, like_value);
            stmt.setString(4, like_value);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                String type = rs.getString("type");

                if(type.equals("person")) {

                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");

                    Person person = new Person(first_name, last_name);

                    person.setId(rs.getInt("id"));
                    person.setEmail(rs.getString("email"));
                    person.setPhone(rs.getString("phone"));
                    person.setMobile(rs.getString("mobile"));

                    contacts.add(person);

                } else if (type.equals("company")){

                    String name = rs.getString("name");

                    Company company = new Company(name);

                    company.setId(rs.getInt("id"));
                    company.setEmail(rs.getString("email"));
                    company.setPhone(rs.getString("phone"));
                    company.setMobile(rs.getString("mobile"));

                    contacts.add(company);
                }
            }

            Collections.sort(contacts, (c1, c2) -> c1.getName().compareTo(c2.getName()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

}
