package com.example.connectouterentity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AutoTraderDBInterface {

    ResultSet writeQuery(String query) throws SQLException;
    void exceuteQuery(String query) throws SQLException;
}
