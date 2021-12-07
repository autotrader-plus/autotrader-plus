package packages.backend_logic;

import packages.exceptions.SensoConnectionFailureException;
import packages.database_info_manipulation.ReturnMultipleCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoanScoreCalculatorTest {

    ArrayList<HashMap<String, Object>> carList;
    HashMap<String, String> userInfo;
    BasicLoans loan;
    LoansScoreCalculator score;

    @BeforeEach
    void setUp() throws SQLException, SensoConnectionFailureException {
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
        loan = new BasicLoans(userInfo, carList);
    }

    @Test
    void getLoansScore() throws SensoConnectionFailureException, IOException, InterruptedException{
        HashMap<String, Object> output = loan.getLoans1();
        HashMap<String, Object> output2 = loan.getLoans2();
        HashMap<String, Object> output3 = loan.getLoans3();
        score = new LoansScoreCalculator(userInfo, output, output2, output3, loan.getCars());
        HashMap<String, String> score1 = score.getLoansScore1();
        HashMap<String, String> score2 = score.getLoansScore2();
        HashMap<String, String> score3 = score.getLoansScore3();
        System.out.println(score1);
        System.out.println(score2);
        System.out.println(score3);
    }
}
