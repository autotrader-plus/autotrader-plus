package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanTableTest {

    LoanTableCalculation basicUser;
    LoanTableCalculation advancedUser;

    @BeforeEach
    void setUp() {
        basicUser = new LoanTableCalculation(730, 0, 0, 0.13, 0.39, "Very High");
        advancedUser = new LoanTableCalculation(730, 1, 1, 0.13, 0.39, "Very High");
    }

    @Test
    void getCreditScoreBasic() {
        assert basicUser.getCreditScore()  == 730;
    }

    // No difference in functionality for basic and advanced for getter methods, so is there a need
    // for these testers?
//    @Test
//    void getCreditScoreAdvanced() {
//
//    }

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
