package packages.backend_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class LoanApprovalCalculationTest {

    User basic;
    User advanced;
    HashMap<String, String> sensoInput;
    LoanApprovalCalculation basicLoanApprovalCalc;
    LoanApprovalCalculation advancedLoanApprovalCalc;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        basic = new User(780, 300, 500, "M4Y111", "Bob Du",
                "123");
        advanced = new User(780, 300, 500, "M4Y111", "Bob Du",
                "123", 8000, true, true, 2000);

        sensoInput = new HashMap<>();
        sensoInput.put("balance", "10000");
        sensoInput.put("credit_score", "780");
        sensoInput.put("loan_age", "36");
        sensoInput.put("vehicle_make", "Honda");
        sensoInput.put("vehicle_model", "Civic");
        sensoInput.put("vehicle_year", "2021");
        sensoInput.put("car_value", "10000");
        sensoInput.put("loan_start_date", "2021-12-01");

        basicLoanApprovalCalc = new LoanApprovalCalculation(basic, sensoInput);
        advancedLoanApprovalCalc = new LoanApprovalCalculation(advanced, sensoInput);
    }

    @Test
    void getCreditScoreBasic() {
        assert basicLoanApprovalCalc.getCreditScore() == 780;
    }

    @Test
    void getCreditScoreAdvanced() {
        assert advancedLoanApprovalCalc.getCreditScore() == 780;
    }

    @Test
    void getEmployedBasic() {
        assert basicLoanApprovalCalc.getEmployed() == 0;
    }

    @Test
    void getEmployedAdvanced() {
        assert advancedLoanApprovalCalc.getEmployed() == 1;
    }

    @Test
    void getHomeownerBasic() {
        assert basicLoanApprovalCalc.getHomeowner() == 0;
    }

    @Test
    void getHomeownerAdvanced() {
        assert advancedLoanApprovalCalc.getHomeowner() == 1;
    }

    @Test
    void getPTIBasic() {
        assert basicLoanApprovalCalc.getPTI() == 0.00;
    }

    @Test
    void getPTIAdvanced() {
        assert advancedLoanApprovalCalc.getPTI() == 0.04;
    }

    @Test
    void getDTIBasic() {
        assert basicLoanApprovalCalc.getDTI() == 0.00;
    }

    @Test
    void getDTIAdvanced() {
        assert advancedLoanApprovalCalc.getDTI() == 0.25;
    }

    @Test
    void getSensoScoreBasic() {
        assert basicLoanApprovalCalc.getSensoScore() == 0;
    }

    @Test
    void getSensoScoreAdvanced() {
        assert advancedLoanApprovalCalc.getSensoScore() == 0;
    }

    @Test
    void getApprovalLikelihoodBasic(){
        assert basicLoanApprovalCalc.getApprovalLikelihood(true).equals("Very Unlikely");
    }

    @Test
    void getApprovalLikelihoodAdvanced(){
        assert advancedLoanApprovalCalc.getApprovalLikelihood(false).equals("Almost Guaranteed");
    }
}
