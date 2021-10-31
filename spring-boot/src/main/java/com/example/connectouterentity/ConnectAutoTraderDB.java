package com.example.connectouterentity;

import java.sql.*;

public class ConnectAutoTraderDB{

    /**
     * Represents a connection to AutoTraderDB.
     * Can call data in string form and Dictionary form
     */

    // This variable essentially represents the connection to the SQLDB; it holds information about that connection
    private final Statement objStatement;

    /**
     * Initialize a Connection. We will automatically establish the DB connection at each instaitation of
     * an object.
     */
    public ConnectAutoTraderDB(){

        this.objStatement = ConnectAutoTraderDB.establishConnection();
    }

    /**
     * Establishing a connection to the database. This method is private as it should only be accessed by the
     * initializer.
     */
    private static Statement establishConnection(){

        try{
            try{
                // Connecting to an API that allows us to use Java and a MySQLdb
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();

            }
            // JDBC URL. Tells Java where to find the DB to connect to. Also give username and password to
            // grant permission
            String dburl="jdbc:mysql://traderautoplusdb.cs3kq1tsgpar.ca-central-1.rds.amazonaws.com:3306/cars";
            String username="sweatless";
            String password="PaulGries123";

            // Establish and return a connection
            Connection myConnection= DriverManager.getConnection(dburl, username, password);
            return myConnection.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Write a query for the SQL database to access
     */
    public ResultSet writeQuery(String query) throws SQLException {

        return this.objStatement.executeQuery(query);

    }

    public void exceuteQuery(String query) throws SQLException {

        this.objStatement.executeUpdate(query);
    }
}

