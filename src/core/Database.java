/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package core;

import java.sql.*;

public class Database {

    public Connection getConnection(){

        String conn_string = "jdbc:sqlite:db.sqlite";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(conn_string);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    public void createDatabaseTables(){
        this.createContactsTable();
        this.createUserTable();
    }

    public void createUserTable() {

        String query = "CREATE TABLE IF NOT EXISTS contacts ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "first_name TEXT DEFAULT NULL, " +
                "last_name TEXT DEFAULT NULL, " +
                "email TEXT DEFAULT NULL, " +
                "phone TEXT DEFAULT NULL, " +
                "mobile TEXT DEFAULT NULL, " +
                "type TEXT DEFAULT NULL );";

        try {

            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createContactsTable(){

        String query = "CREATE TABLE IF NOT EXISTS users ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, " +
                "first_name TEXT, " +
                "last_name TEXT, " +
                "email TEXT UNIQUE, " +
                "phone TEXT, " +
                "mobile TEXT, " +
                "password TEXT )";

        try {

            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
