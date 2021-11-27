package packages.backendlogic;

import java.text.DecimalFormat;

/**
 * Represents a User Object based on the user info inputted by the user from the frontend
 */
public class User {
    private final int creditScore;
    private final int monthlyBudget;
    private final int downPayment;
    private final String zipCode;
    private final String name;
    private final int monthlyIncome;
    private final boolean employed;
    private final boolean homeowner;
    private final int monthlyDebt;

    /**
     * Creates a basic User Object based on provided information
     * @param creditScore The User's credit score
     * @param monthlyBudget The User's monthly budget
     * @param downPayment The User's down payment
     * @param zipCode The User's zip code
     * @param name The User's name
     */
    public User(int creditScore, int monthlyBudget, int downPayment, String zipCode, String name){
        this.creditScore = creditScore;
        this.monthlyBudget = monthlyBudget;
        this.downPayment = downPayment;
        this.zipCode = zipCode;
        this.name = name;
        this.monthlyIncome = 0; //set to 0 as default
        this.employed = false;  //set to false as default
        this.homeowner = false; //set to false as default
        this.monthlyDebt = 0;   //set to 0 as default
    }

    /**
     * Creates advanced User Object based on provided info
     * @param creditScore The User's credit score
     * @param monthlyBudget The User's monthly budget
     * @param downPayment The User's down payment
     * @param zipCode The User's zip code
     * @param name The User's name
     * @param monthlyIncome The User's monthly income
     * @param employed The User's employment status
     * @param homeowner The User's homeowner status
     * @param monthlyDebt The User's monthly debt
     */
    public User(int creditScore, int monthlyBudget, int downPayment, String zipCode, String name, int monthlyIncome,
                boolean employed, boolean homeowner, int monthlyDebt){
        this.creditScore = creditScore;
        this.monthlyBudget = monthlyBudget;
        this.downPayment = downPayment;
        this.zipCode = zipCode;
        this.name = name;
        this.monthlyIncome = monthlyIncome;
        this.employed = employed;
        this.homeowner = homeowner;
        this.monthlyDebt = monthlyDebt;
    }

    /**
     * Gets the User's price range
     * @return A string representing the User's price range
     */
    public String getPriceRange(){
        return Integer.toString((36 * this.monthlyBudget) + this.downPayment);
    }

    /**
     * Will be used in the future
     * Overloaded method that gets the User's price range for a given term
     * @param term The term of the User's potential loan
     * @return A string representing the User's price range
     */
    public String getPriceRange(int term){
        return Integer.toString((term * this.monthlyBudget) + this.downPayment);
    }

    /**
     * Gets the User's monthly budget
     * @return A string representing the User's monthly budget
     */
    public String getMonthlyBudget(){
        return Integer.toString(this.monthlyBudget);
    }

    /**
     * Gets the User's credit score
     * @return A string representing the User's credit score
     */
    public String getCreditScore(){
        return Integer.toString(this.creditScore);
    }

    /**
     * Gets the User's zip code (location)
     * @return A string representing the User's zip code (location)
     */
    public String getLocation(){
        return this.zipCode;
    }

    /**
     * Gets the User's name
     * @return A string representing the User's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the User's down payment
     * @return A string representing the User's down payment
     */
    public String getDownPayment() {
        return Integer.toString(this.downPayment);
    }

    /**
     * Gets the User's monthly income
     * @return A string representing the User's monthly income
     */
    public String getMonthlyIncome() {
        return Integer.toString(this.monthlyIncome);
    }

    /**
     * Gets the User's employment status
     * @return A string representing the User's employment status
     */
    public String isEmployed() {
        if (this.employed) {
            return "Employed";
        }
        return "not Employed";
    }

    /**
     * Gets the User's homeowner status
     * @return A string representing the User's homeowner status
     */
    public String isHomeowner() {
        if (this.homeowner){
            return "Homeowner";
        }
        return "not Homeowner";
    }

    /**
     * Gets the User's monthly debt
     * @return A string representing the User's monthly debt
     */
    public String getMonthlyDebt() {
        return Integer.toString(this.monthlyDebt);
    }

    /**
     * Gets the User's final score
     * @return A string representing the User's final score
     */
    public String getFinalScore() {
        int employed;
        int homeowner;

        if (this.employed){
            employed = 1;
        }
        else{
            employed = 0;
        }

        if (this.homeowner){
            homeowner = 1;
        }
        else{
            homeowner = 0;
        }

        LoanTable loanTable = new LoanTable(creditScore, employed, homeowner, this.getPTI(), this.getDTI());
        DecimalFormat twoDecimals = new DecimalFormat("#0.00");
        return twoDecimals.format(loanTable.getFinalScore());
    }
}
