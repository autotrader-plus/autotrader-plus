package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class LoanTableCalculationTest {

    LoanTableCalculation basicUser;
    LoanTableCalculation advancedUser;
    HashMap<String, String> sensoInput;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        sensoInput = new HashMap<>();
        sensoInput.put("remainingBalance", "10000");
        sensoInput.put("creditScore", "780");
        sensoInput.put("loanAge", "36");
        sensoInput.put("vehicleMake", "Honda");
        sensoInput.put("vehicleModel", "Civic");
        sensoInput.put("vehicleYear", "2021");
        sensoInput.put("carValue", "10000");
        sensoInput.put("loanStartDate", "2021-12-01");

        basicUser = new LoanTableCalculation(730, 0, 0, 0.13, 0.39,
                sensoInput);
        advancedUser = new LoanTableCalculation(730, 1, 1, 0.13, 0.39,
                sensoInput);
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

//    @Test
//    void getEmployedAdvanced() {
//
//    }

    @Test
    void getHomeownerBasic() {
        assert basicUser.getHomeowner() == 0;
    }

//    @Test
//    void getHomeownerAdvanced() {
//
//    }

    @Test
    void getPTIBasic() {
        assert basicUser.getPTI() == 0.13;
    }

//    @Test
//    void getPTIAdvanced() {
//
//    }

    @Test
    void getDTIBasic() {
        assert basicUser.getDTI() == 0.39;

    }

//    @Test
//    void getDTIAdvanced() {
//
//    }

    @Test
    void getSensoScoreBasic() {
        assert basicUser.getSensoScore() == 0;
    }

    @Test
    void getSensoScoreAdvanced() {
        assert advancedUser.getSensoScore() == 2;
    }

    @Test
    void getFinalScoreBasic() {
        assert basicUser.getFinalScore() == 8.899999999999999;
    }

//    @Test
//    void getFinalScoreAdvanced() {
//
//    }
}
