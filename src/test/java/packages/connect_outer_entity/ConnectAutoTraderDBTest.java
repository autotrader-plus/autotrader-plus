package packages.connect_outer_entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.*;

import java.sql.SQLException;


// Test class using Mockito to test that we can connect to the database correctly
class ConnectAutoTraderDBTest{

    ConnectAutoTraderDB connection;
    ConnectAutoTraderDB mockedConnection;

    @BeforeEach
    void setUp(){
        connection = new ConnectAutoTraderDB();
        mockedConnection = mock(ConnectAutoTraderDB.class);
    }

    @Test
    void ConnectAutoTraderDB() throws SQLException {
        assert(connection.getClass() == ConnectAutoTraderDB.class);
    }

    @Test
    void writeQuery() throws SQLException {
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = 1;";
        mockedConnection.writeQuery(query);
        when(mockedConnection.writeQuery(query)).thenReturn(connection.writeQuery(query));
        verify(mockedConnection).writeQuery(query);
    }

    @Test
    void executeQuery() throws SQLException {
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = 1;";
        mockedConnection.executeQuery(query);
        verify(mockedConnection).executeQuery(query);
    }
}