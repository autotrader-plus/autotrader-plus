package packages.connectouterentity;

import java.sql.*;

public class ConnectAutoTraderDB implements AutoTraderDBInterface {
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
            String dburl = System.getenv("TRADERAUTO_SQLDB_URL");
            String username= System.getenv("TRADERAUTO_SQLDB_USER");
            String password= System.getenv("TRADERAUTO_SQLDB_PWD");

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
    @Override
    public ResultSet writeQuery(String query) throws SQLException {

        return this.objStatement.executeQuery(query);

    }
    @Override
    public void exceuteQuery(String query) throws SQLException {

        this.objStatement.executeUpdate(query);
    }
}

