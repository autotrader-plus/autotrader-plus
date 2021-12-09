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
    void getLoans() throws SensoConnectionFailureException, IOException, InterruptedException {
        HashMap<String, Object> output = loan.getLoans1();
        HashMap<String, Object> output2 = loan.getLoans2();
        HashMap<String, Object> output3 = loan.getLoans3();

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