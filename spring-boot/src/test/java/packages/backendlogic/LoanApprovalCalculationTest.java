package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class LoanApprovalCalculationTest {

    LoanApprovalCalculation basicUser;
    LoanApprovalCalculation advancedUser;
    HashMap<String, String> sensoInput;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        sensoInput = new HashMap<>();
        sensoInput.put("balance", "10000");
        sensoInput.put("credit_score", "780");
        sensoInput.put("loan_age", "36");
        sensoInput.put("vehicle_make", "Honda");
        sensoInput.put("vehicle_model", "Civic");
        sensoInput.put("vehicle_year", "2021");
        sensoInput.put("car_value", "10000");
        sensoInput.put("loan_start_date", "2021-12-01");

//        basicUser = new LoanApprovalCalculation(730, 0, 0, 0.13, 0.39,
//                sensoInput);
//        advancedUser = new LoanApprovalCalculation(730, 1, 1, 0.14, 0.38,
//                sensoInput);
    }

    @Test
    void getCreditScoreBasic() {
        assert basicUser.getCreditScore() == 730;
    }

    @Test
    void getCreditScoreAdvanced() {
        assert advancedUser.getCreditScore() == 730;
    }

    @Test
    void getEmployedBasic() {
        assert basicUser.getEmployed() == 0;
    }

    @Test
    void getEmployedAdvanced() {
        assert advancedUser.getEmployed() == 1;
    }

    @Test
    void getHomeownerBasic() {
        assert basicUser.getHomeowner() == 0;
    }

    @Test
    void getHomeownerAdvanced() {
        assert advancedUser.getHomeowner() == 1;
    }

    @Test
    void getPTIBasic() {
        assert basicUser.getPTI() == 0.13;
    }

    @Test
    void getPTIAdvanced() {
        assert advancedUser.getPTI() == 0.14;
    }

    @Test
    void getDTIBasic() {
        assert basicUser.getDTI() == 0.39;
    }

    @Test
    void getDTIAdvanced() {
        assert advancedUser.getDTI() == 0.38;
    }

    @Test
    void getSensoScoreBasic() {
        assert basicUser.getSensoScore() == 0;
    }

    @Test
    void getSensoScoreAdvanced() {
        assert advancedUser.getSensoScore() == 0;
    }

    @Test
    void getApprovalLikelihoodBasic(){
        assert basicUser.getApprovalLikelihood(true).equals("Very Unlikely");
    }

    @Test
    void getApprovalLikelihoodAdvanced(){
        assert advancedUser.getApprovalLikelihood(false).equals("Very Unlikely");
    }
}
