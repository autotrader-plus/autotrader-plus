package com.example.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
        User basic = new User(750, 300, 200, "M4Y111", "Bob Du");
        User advanced = new User(700, 400, 100, "111M4Y", "Dod Bu",
                4000, true, true, 1000);
    }

    @Test
    void getPriceRangeBasic(User basic) {
        assert basic.getPriceRange().equals("10800");
    }

    @Test
    void getPriceRangeAdvanced(User advanced) {
        assert advanced.getPriceRange().equals("14400");
    }

    @Test
    void getMonthlybudgetBasic(User basic) {
        assert basic.getMonthlybudget().equals("300");
    }

    @Test
    void getMonthlybudgetAdvanced(User advanced) {
        assert advanced.getMonthlybudget().equals("400");
    }

    @Test
    void getCreditscoreBasic(User basic) {
        assert basic.getCreditscore().equals("750");
    }

    @Test
    void getCreditscoreAdvanced(User advanced) {
        assert advanced.getCreditscore().equals("700");
    }

    @Test
    void getLocationBasic(User basic) {
        assert basic.getLocation().equals("M4Y111");
    }

    @Test
    void getLocationAdvanced(User advanced) {
        assert advanced.getLocation().equals("111M4Y");
    }

    @Test
    void getNameBasic(User basic) {
        assert basic.getName().equals("Bob Du");
    }

    @Test
    void getNameAdvanced(User advanced) {
        assert advanced.getName().equals("Dod Bu");
    }

    @Test
    void getDownpaymentBasic(User basic) {
        assert basic.getDownpayment().equals("200");
    }

    @Test
    void getDownpaymentAdvanced(User advanced) {
        assert advanced.getDownpayment().equals("100");
    }

    @Test
    void getMonthlyincomeBasic(User basic) {
        assert basic.getMonthlyincome().equals("0");
    }

    @Test
    void getMonthlyincomeAdvanced(User advanced) {
        assert advanced.getMonthlyincome().equals("4000");
    }

    @Test
    void isEmployedBasic(User basic) {
        assert basic.isEmployed().equals("not Employed");
    }

    @Test
    void isEmployedAdvanced(User advanced) {
        assert advanced.isEmployed().equals("Employed");
    }

    @Test
    void isHomeownerBasic(User basic) {
        assert basic.isHomeowner().equals("not Homeowner");
    }

    @Test
    void isHomeownerAdvanced(User advanced) {
        assert advanced.isHomeowner().equals("Homeowner");
    }

    @Test
    void getMonthlydebtBasic(User basic) {
        assert basic.getMonthlydebt().equals("0");
    }

    @Test
    void getMonthlydebtAdvanced(User advanced) {
        assert advanced.getMonthlydebt().equals("1000");
    }
}