package packages.backendlogic;

import packages.informationmanipulation.ReturnMultipleCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

class LoansTest {

    ArrayList<HashMap<String, Object>> carlist;
    HashMap<String, String> userinfo;
    Loans loan;

    @BeforeEach
    void setUp() throws SQLException, IOException, InterruptedException {
        carlist = ReturnMultipleCars.returnAllCars();
        userinfo = new HashMap<>();
        userinfo.put("credit-score", "750");
        userinfo.put("monthlybudget", "600");
        userinfo.put("downpayment", "200");
        userinfo.put("zip-code", "M4y111");
        userinfo.put("name", "Bob Du");
        loan = new Loans(userinfo, carlist);
    }
    @Test
    void getLoans() {
        HashMap<String, Object> output = loan.getLoans();
        assert output.size() == 1 && output.containsKey("13");
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
        assert buyer.getCreditscore().equals("750") && buyer.getName().equals("Bob Du");
    }

    @Test
    void calculateLoans() throws IOException, InterruptedException {
        LoanInfoInterface getLoans = new Loans();
        assert(getLoans.calculateLoans(userinfo, carlist) != null);
    }
}