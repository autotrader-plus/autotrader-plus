package com.example.informationmanipulation;

import com.example.backendlogic.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddUserTest {

    @Test
    @DisplayName("addUser: Basic Case")
    void addUser() throws SQLException {
        User user = new User("Mike", 730, 1000, 5000, "M4Y111",
                8500, true, true, 500);

        AddUser.addUser(user);

        // TODO: Check that the user has been added to database
        //       Unsure how to do that...
    }

}