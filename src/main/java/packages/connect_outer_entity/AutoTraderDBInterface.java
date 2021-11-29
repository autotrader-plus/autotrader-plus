package packages.connect_outer_entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**An interface that prevents backend from directly interacting with database and database connectors.**/
public interface AutoTraderDBInterface {
    /**
     *Write the query for database
     * @param query - the query for the database
     * @return the result of the query
     * @throws SQLException - exception thrown when connection to database fails
     */
    ResultSet writeQuery(String query) throws SQLException;

    /**
     * Execute the query for the database
     * @param query - the query for the database
     * @throws SQLException - exception thrown when connection to database fails
     */
    void executeQuery(String query) throws SQLException;
}
