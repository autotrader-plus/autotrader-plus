package packages.informationmanipulation;

import packages.backendlogic.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

public class AddUserLoginTest {
    @Test
    void addUserLogin() throws SQLException {
        User user = new User(690, 4200, 1337, "KEKW", "Lord Pikachu");
        String password = "GottaCatchThemAll";
        AddUserLogin.addUser(user, password);

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "1");
        testMap.put("Name", "Lord Pikachu");
        testMap.put("Password", "GottaCatchThemAll");


        //assert testMap.equals(ReturnUserInformation.returnUser(3));
    }
}
