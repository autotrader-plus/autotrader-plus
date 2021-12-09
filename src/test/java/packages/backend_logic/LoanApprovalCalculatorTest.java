package packages.backend_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class LoanApprovalCalculatorTest {

    User basic;
    User advanced;
    HashMap<String, String> sensoInput;
    LoanApprovalCalculator basicLoanApprovalCalc;
    LoanApprovalCalculator advancedLoanApprovalCalc;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        basic = new User(780, 300, 500, "M4Y111", "Bob Du",
                "123");
        advanced = new User(780, 300, 500, "M4Y111", "Bob Du",
                "123", 8000, true, true, 2000);


        basicLoanApprovalCalc = new LoanApprovalCalculator(basic, "VERY HIGH");
        advancedLoanApprovalCalc = new LoanApprovalCalculator(advanced, "VERY HIGH");
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
        assert basicLoanApprovalCalc.getSensoScore() == -1;
    }

    @Test
    void getSensoScoreAdvanced() {
        assert advancedLoanApprovalCalc.getSensoScore() == -1;
    }

    @Test
    void getApprovalLikelihoodBasic(){
        assert basicLoanApprovalCalc.getApprovalLikelihood(true).equals("Very Unlikely");
    }

    @Test
    void getApprovalLikelihoodAdvanced(){
        assert advancedLoanApprovalCalc.getApprovalLikelihood(false).equals("Very Likely");
    }
}
