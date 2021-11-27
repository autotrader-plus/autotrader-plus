package packages.connectouterentity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


// Test class using Mockito to test that we can connect to the database correctly
class ConnectAutoTraderDBTest{

    @Test
    void ConnectAutoTraderDB() throws SQLException {
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        assert(connection.getClass() == ConnectAutoTraderDB.class);
    }

    @Test
    void writeQuery() throws SQLException {
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ConnectAutoTraderDB mockedConnection = mock(ConnectAutoTraderDB.class);
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = 1;";
        mockedConnection.writeQuery(query);
        when(mockedConnection.writeQuery(query)).thenReturn(connection.writeQuery(query));
        verify(mockedConnection).writeQuery(query);
    }

    @Test
    void executeQuery() throws SQLException {
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ConnectAutoTraderDB mockedConnection = mock(ConnectAutoTraderDB.class);
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = 1;";
        mockedConnection.executeQuery(query);
        verify(mockedConnection).executeQuery(query);
    }

    @Test
    void getObjStatement() throws SQLException {
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ConnectAutoTraderDB mockedConnection = mock(ConnectAutoTraderDB.class);
        mockedConnection.getObjStatement();
        when(mockedConnection.getObjStatement()).thenReturn(connection.getObjStatement());
        verify(mockedConnection).getObjStatement();
    }


}