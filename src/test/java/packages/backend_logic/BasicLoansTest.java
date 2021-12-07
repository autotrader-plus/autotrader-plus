package packages.backend_logic;

import packages.exceptions.SensoConnectionFailureException;
import packages.database_info_manipulation.ReturnMultipleCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

class BasicLoansTest {

    ArrayList<HashMap<String, Object>> carList;
    HashMap<String, String> userInfo;
    BasicLoans loan;
    LoansScoreCalculator score; //temp
    LoanResponseConstructor finalscore; //temp

    @BeforeEach
    void setUp() throws SQLException, SensoConnectionFailureException {
        ReturnMultipleCars returnMultipleCars = new ReturnMultipleCars();
        carList = returnMultipleCars.returnAllCars();
        userInfo = new HashMap<>();
        userInfo.put("credit-score", "770");
        userInfo.put("monthlybudget", "360"); //360 for easy test
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
    void getLoans() throws SensoConnectionFailureException, IOException, InterruptedException { //temp exception
        HashMap<String, Object> output = loan.getLoans1();
        HashMap<String, Object> output2 = loan.getLoans2();
        HashMap<String, Object> output3 = loan.getLoans3();

        //  ArrayList hello = (ArrayList) output.get("13");
        //System.out.println(hello.size());
        // ArrayList hello2 = (ArrayList) output2.get("13");
        // System.out.println(hello2.size());
        // ArrayList hello3 = (ArrayList) output3.get("13");
        //System.out.println(hello3.size());

        //  System.out.println(output2.keySet());
        // System.out.println(output3.keySet());
        // System.out.println(output);
        //System.out.println(output2);
        // System.out.println(output3);

        //temp below
        //     score = new LoansScoreCalculator(userInfo, output, output2, output3, loan.getCars());
        //   System.out.println(1337);
        //   System.out.println(score.getLoanTerm1());
        //   HashMap<String, String> score1 = score.getLoansScore1();
        //   System.out.println(score1);
        //    HashMap<String, Integer> loanage1 = score.getLoanTerm1();
        //    System.out.println(1);
        // System.out.println(loanage1);

        //   HashMap<String,  String> score2 = score.getLoansScore2();
        //    System.out.println(score2.get("13"));
        //    HashMap<String, Integer> loanage2 = score.getLoanTerm2();
        //   System.out.println(2);
        // System.out.println(loanage2);

        //    HashMap<String, String> score3 = score.getLoansScore3();
        //    System.out.println(score3.get("13"));
        //     HashMap<String, Integer> loanage3 = score.getLoanTerm3();
        //   System.out.println(3);
        //  System.out.println(loanage3);

//test for traderAutoScore
        //finalscore = new LoanResponseConstructor(userInfo, carList);
       // HashMap<String, Object> outy = finalscore.getTraderAutoScore();
      //  System.out.println(outy);

        //temp above

        assert output.size() == 1 && output.containsKey("13");
        assert output2.size() == 1 && output.containsKey("13");
        assert output3.size() == 0;

    }

    @Test
    void getCars() {
        CarList cars = loan.getCars();
        Car car = (Car) cars.getCar(0);
        assert car.getBrand().equals("Mercedes-Benz S-Class");
    }

    @Test
    void getBuyer() {
        User buyer = loan.getBuyer();
        assert buyer.getCreditScore().equals("770") && buyer.getName().equals("Bob Du");
    }
}