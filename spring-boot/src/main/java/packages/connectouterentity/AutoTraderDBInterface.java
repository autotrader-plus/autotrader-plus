package packages.connectouterentity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AutoTraderDBInterface {
    /**
     * An interface that prevents backend from directly interacting with database and database connectors.
     */
    ResultSet writeQuery(String query) throws SQLException;
    void exceuteQuery(String query) throws SQLException;
}
