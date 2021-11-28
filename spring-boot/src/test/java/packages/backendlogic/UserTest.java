package packages.backendlogic;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

class UserTest {
    User basic;
    User advanced;
    HashMap<String, String> sensoInputVeryHigh, sensoInputHigh, sensoInputMedium, sensoInputLow, sensoInputVeryLow;

    @BeforeEach
    void setUp() {
        this.basic = new User(750, 300, 200, "M4Y111", "Bob Du");
        this.advanced = new User(700, 400, 100, "111M4Y", "Dod Bu",
                4000, true, true, 1000);
    }

    @Test
    void getPriceRangeBasic() {
        assert this.basic.getPriceRange().equals("11000");
    }

    @Test
    void getPriceRangeAdvanced() {
        assert this.advanced.getPriceRange().equals("14500");
    }

    @Test
    void getMonthlyBudgetBasic() {
        assert this.basic.getMonthlyBudget().equals("300");
    }

    @Test
    void getMonthlyBudgetAdvanced() {
        assert this.advanced.getMonthlyBudget().equals("400");
    }

    @Test
    void getCreditScoreBasic() {
        assert this.basic.getCreditScore().equals("750");
    }

    @Test
    void getCreditScoreAdvanced() {
        assert this.advanced.getCreditScore().equals("700");
    }

    @Test
    void getLocationBasic() {
        assert this.basic.getLocation().equals("M4Y111");
    }

    @Test
    void getLocationAdvanced() {
        assert this.advanced.getLocation().equals("111M4Y");
    }

    @Test
    void getNameBasic() {
        assert this.basic.getName().equals("Bob Du");
    }

    @Test
    void getNameAdvanced() {
        assert this.advanced.getName().equals("Dod Bu");
    }

    @Test
    void getDownPaymentBasic() {
        assert this.basic.getDownPayment().equals("200");
    }

    @Test
    void getDownPaymentAdvanced() {
        assert this.advanced.getDownPayment().equals("100");
    }

    @Test
    void getMonthlyIncomeBasic() {
        assert this.basic.getMonthlyIncome().equals("0");
    }

    @Test
    void getMonthlyIncomeAdvanced() {
        assert this.advanced.getMonthlyIncome().equals("4000");
    }

    @Test
    void isEmployedBasic() {
        assert this.basic.isEmployed().equals("not Employed");
    }

    @Test
    void isEmployedAdvanced() {
        assert this.advanced.isEmployed().equals("Employed");
    }

    @Test
    void isHomeownerBasic() {
        assert this.basic.isHomeowner().equals("not Homeowner");
    }

    @Test
    void isHomeownerAdvanced() {
        assert this.advanced.isHomeowner().equals("Homeowner");
    }

    @Test
    void getMonthlyDebtBasic() {
        assert this.basic.getMonthlyDebt().equals("0");
    }

    @Test
    void getMonthlyDebtAdvanced() {
        assert this.advanced.getMonthlyDebt().equals("1000");
    }

    @Test
    void getDTIBasic(){
        assert this.basic.getDTI().equals("0.00");
    }

    @Test
    void getDTIAdvanced() {
        assert this.advanced.getDTI().equals("0.25");
    }

    @Test
    void getPTIBasic() {
        assert this.basic.getPTI().equals("0.00");
    }

    @Test
    void getPTIAdvanced() {
        assert this.advanced.getPTI().equals("0.10");
    }

    @Before
    void setUpGetSensoScoreBasicVeryHigh(){
        sensoInputVeryHigh = new HashMap<>();
        sensoInputVeryHigh.put("remainingBalance", "10000");
        sensoInputVeryHigh.put("creditScore", "780");
        sensoInputVeryHigh.put("loanAge", "36");
        sensoInputVeryHigh.put("vehicleMake", "Honda");
        sensoInputVeryHigh.put("vehicleModel", "Civic");
        sensoInputVeryHigh.put("vehicleYear", "2021");
        sensoInputVeryHigh.put("carValue", "10000");
        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
    }

    @Test
    // NOT WORKING RIGHT NOW
    void getSensoScoreBasicVeryHigh() throws IOException, InterruptedException {
        String expected = "Very High";
        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
    }

//    @Before
//    void setUpGetSensoScoreAdvancedVeryHigh(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreAdvancedVeryHigh() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreBasicHigh(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//    // NOT WORKING RIGHT NOW
//    void getSensoScoreBasicHigh() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreAdvancedHigh(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreAdvancedHigh() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreBasicMedium(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreBasicMedium() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreAdvancedMedium(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreAdvancedMedium() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreBasicLow(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreBasicLow() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreAdvancedLow(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreAdvancedLow() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreBasicVeryLow(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreBasicVeryLow() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }
//
//    @Before
//    void setUpGetSensoScoreAdvancedVeryLow(){
//        sensoInputVeryHigh = new HashMap<>();
//        sensoInputVeryHigh.put("remainingBalance", "10000");
//        sensoInputVeryHigh.put("creditScore", "780");
//        sensoInputVeryHigh.put("loanAge", "36");
//        sensoInputVeryHigh.put("vehicleMake", "Honda");
//        sensoInputVeryHigh.put("vehicleModel", "Civic");
//        sensoInputVeryHigh.put("vehicleYear", "2021");
//        sensoInputVeryHigh.put("carValue", "10000");
//        sensoInputVeryHigh.put("loanStartDate", "2021-12-01");
//    }
//
//    @Test
//        // NOT WORKING RIGHT NOW
//    void getSensoScoreAdvancedVeryLow() throws IOException, InterruptedException {
//        String expected = "Very High";
//        assert basic.getSensoScore(sensoInputVeryHigh).equals(expected);
//    }

    @Test
    void getFinalScoreBasic() {
        assert basic.getFinalScore("Very High").equals("11.00");
    }

    @Test
    void getFinalScoreAdvanced() {
        assert advanced.getFinalScore("Very High").equals("12.40");
    }
}