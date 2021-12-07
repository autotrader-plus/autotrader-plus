package packages.backend_logic;

import packages.exceptions.SensoConnectionFailureException;
import packages.database_info_manipulation.ReturnMultipleCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoanResponseConstructorTest {
    ArrayList<HashMap<String, Object>> carList;
    HashMap<String, String> userInfo;
    LoanResponseConstructor score;

    @BeforeEach
    void setUp() throws SQLException, SensoConnectionFailureException, IOException, InterruptedException {
        ReturnMultipleCars returnMultipleCars = new ReturnMultipleCars();
        carList = returnMultipleCars.returnAllCars();
        userInfo = new HashMap<>();
        userInfo.put("credit-score", "770");
        userInfo.put("monthlybudget", "360");
        userInfo.put("downpayment", "200");
        userInfo.put("zip-code", "M4y111");
        userInfo.put("name", "Bob Du");
        userInfo.put("password", "123");
        userInfo.put("monthlyincome", "8500");
        userInfo.put("employed", "employed");
        userInfo.put("homeowner", "homeowner");
        userInfo.put("monthlydebt", "500");
        score = new LoanResponseConstructor(userInfo, carList);
    }

    @Test
    void getTraderAutoScoreTest(){
        HashMap<String, Object> score = this.score.getTraderAutoScore();

        assert score.size() == 1 && score.containsKey("13");
    }

}
