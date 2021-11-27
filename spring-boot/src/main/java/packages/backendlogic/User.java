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
    private final String password;

    /**
     * Creates a basic User Object based on provided information
     * @param creditScore The User's credit score
     * @param monthlyBudget The User's monthly budget
     * @param downPayment The User's down payment
     * @param zipCode The User's zip code
     * @param name The User's name
     * @param User's login password
     */
    public User(int creditScore, int monthlyBudget, int downPayment, String zipCode, String name, String password){
        this.creditScore = creditScore;
        this.monthlyBudget = monthlyBudget;
        this.downPayment = downPayment;
        this.zipCode = zipCode;
        this.name = name;
        this.password = password;
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
    public User(int creditScore, int monthlyBudget, int downPayment, String zipCode, String name,
                String password, int monthlyIncome, boolean employed, boolean homeowner, int monthlyDebt){
        this.creditScore = creditScore;
        this.monthlyBudget = monthlyBudget;
        this.downPayment = downPayment;
        this.zipCode = zipCode;
        this.name = name;
        this.password = password;
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

    // will be used in the future

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
     * Gets the User's password
     * @return A string representing the User's password
     */
    public String getPassword() {
        return this.password;
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
     * Gets the User's debt to income ratio (DTI)
     * @return A String representing this User's DTI
     */
    public String getDTI() {
        if (monthlyIncome == 0){
            return "0.00";
        }
        DecimalFormat twoDecimals = new DecimalFormat("#0.00");
        return twoDecimals.format((double) monthlyDebt / monthlyIncome);
    }

    /**
     * Gets the User's payment to income ratio (PTI)
     * @return A String representing this User's PTI
     */
    public String getPTI() {
        if (monthlyIncome == 0){
            return "0.00";
        }
        DecimalFormat twoDecimals = new DecimalFormat("#0.00");
        return twoDecimals.format((double) monthlyBudget / monthlyIncome);
    }
}
