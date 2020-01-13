/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package model;

import core.Database;
import core.iModel;

import java.sql.*;
import java.util.ArrayList;

public class User implements iModel {

    private Integer id;
    private String username = System.getProperty("user.name");
    private String firstName, lastName, email, phone, mobile, password;
    private Database db;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) {
        this.mobile = mobile; }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Boolean create() {

        String query = "INSERT INTO users (username, first_name, last_name, email, phone, mobile, password) VALUES (?,?,?,?,?,?,?)";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.username);
            stmt.setString(2, this.firstName);
            stmt.setString(3, this.lastName);
            stmt.setString(4, this.email);
            stmt.setString(5, this.phone);
            stmt.setString(6, this.mobile);
            stmt.setString(7, this.password);

            Integer status = stmt.executeUpdate();

            if (status > 0){
                return Boolean.TRUE;
            }

            stmt.close();
            conn.close();

        } catch (SQLException e){

            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean update(){

        String query = "UPDATE users SET first_name=?, last_name=?, email=?, phone=?, mobile=? WHERE id=?";

        try{

            this.db = new Database();

            Connection conn = this.db.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.firstName);
            stmt.setString(2, this.lastName);
            stmt.setString(3, this.email);
            stmt.setString(4, this.phone);
            stmt.setString(5, this.mobile);

            stmt.setInt(6, this.getId());

            Integer status = stmt.executeUpdate();

            if (status > 0){
                return Boolean.TRUE;
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();;
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean delete(){

        String query = "DELETE FROM users WHERE id=?";

        try{

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

        } catch (SQLException e){

            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    @Override
    public void get(){

        String query = "SELECT * FROM users WHERE username=?";

        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                this.id = rs.getInt("id");
                this.username = rs.getString("username");
                this.firstName = rs.getString("first_name");
                this.lastName = rs.getString("last_name");
                this.email = rs.getString("email");
                this.phone = rs.getString("phone");
                this.mobile = rs.getString("mobile");
                this.password = rs.getString("password");

            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getAll(){

        String query = "SELECT * FROM users";

        ArrayList<User> users = new ArrayList<>();


        try {

            this.db = new Database();

            Connection conn = this.db.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setMobile(rs.getString("mobile"));

                users.add(user);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    public Boolean comparePassword(String pwd){

        if (this.password.equals(pwd)){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
