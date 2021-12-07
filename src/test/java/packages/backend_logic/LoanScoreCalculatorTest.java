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

        assert score1.size() == 1 && score1.containsKey("13");
        assert score2.size() == 1 && score2.containsKey("13");
        assert score3.size() == 0;
    }

    @Test
    void getLoanTerm() throws SensoConnectionFailureException, IOException, InterruptedException{
        HashMap<String, Object> output = loan.getLoans1();
        HashMap<String, Object> output2 = loan.getLoans2();
        HashMap<String, Object> output3 = loan.getLoans3();
        score = new LoansScoreCalculator(userInfo, output, output2, output3, loan.getCars());
        HashMap<String, Integer> loanTerm1 = score.getLoanTerm1();
        HashMap<String, Integer> loanTerm2 = score.getLoanTerm2();
        HashMap<String, Integer> loanTerm3 = score.getLoanTerm3();

        assert loanTerm1.size() == 1 && loanTerm1.containsKey("13");
        assert loanTerm2.size() == 1 && loanTerm2.containsKey("13");
        assert loanTerm3.size() == 0;
    }
    @Test
    void getCars() {
        CarList cars = score.getCars();
        Car car = (Car) cars.getCar(0);
        assert car.getBrand().equals("Mercedes-Benz S-Class");
    }

    @Test
    void getBuyer() {
        User buyer = score.getUser();
        assert buyer.getCreditScore().equals("770") && buyer.getName().equals("Bob Du");
    }
}
