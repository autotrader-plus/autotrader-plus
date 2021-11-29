package packages.backendlogic;

import packages.exceptions.SensoConnectionFailureException;
import packages.informationmanipulation.ReturnMultipleCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

class LoansTest {

    ArrayList<HashMap<String, Object>> carList;
    HashMap<String, String> userInfo;
    Loans loan;

    @BeforeEach
    void setUp() throws SQLException, SensoConnectionFailureException {
        ReturnMultipleCars returnMultipleCars = new ReturnMultipleCars();
        carList = returnMultipleCars.returnAllCars();
        userInfo = new HashMap<>();
        userInfo.put("credit-score", "750");
        userInfo.put("monthlybudget", "600");
        userInfo.put("downpayment", "200");
        userInfo.put("zip-code", "M4y111");
        userInfo.put("name", "Bob Du");
        userInfo.put("password", "123");
        loan = new Loans(userInfo, carList);
    }
    @Test
    void getLoans() {
        HashMap<String, Object> output = loan.getLoans();
        assert output.size() == 2 && output.containsKey("13");
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
        assert buyer.getCreditScore().equals("750") && buyer.getName().equals("Bob Du");
    }

    @Test
    void calculateLoans() throws SensoConnectionFailureException {
        LoanInfoInterface getLoans = new Loans(userInfo, carList);
        System.out.println(getLoans.calculateLoans(userInfo, carList));
        assert(getLoans.calculateLoans(userInfo, carList) != null);
    }
}