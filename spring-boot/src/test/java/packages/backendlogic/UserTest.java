package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

class UserTest {
    User basic;
    User advanced;

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
    void getDTIAdvanced(){
        assert this.advanced.getDTI().equals("0.25");
    
    @Test
    void getPTIBasic() {
        assert this.basic.getPTI().equals("0.00");
    }

    @Test
    void getPTIAdvanced() {
        assert this.advanced.getPTI().equals("0.10");
    }
}