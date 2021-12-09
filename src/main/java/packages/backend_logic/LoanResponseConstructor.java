package packages.backend_logic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class puts together loan information, including possible loan offers and approval likelihood into
 * one hashmap to return to front-end.
 */
public class LoanResponseConstructor implements LoanInfoInterface {
    private HashMap<String, Object> score;
    private HashMap<String, String> loanScore1;
    private HashMap<String, String> loanScore2;
    private HashMap<String, String> loanScore3;
    private HashMap<String, Integer> loanTerm1;
    private HashMap<String, Integer> loanTerm2;
    private HashMap<String, Integer> loanTerm3;
    private CarList<Car> cars;

    /**
     * Creates an empty HashMap LoanResponseConstructor that will be storing BasicLoans
     * and it's Loan approval likelihood score
     * Creates an advanced or basic User Object based on the amount of info given
     * Creates a CarList Object consisting of only cars affordable by the User based on User budget
     * Generates Loan Approval likelihood score and stores it in LoanResponseConstructor with CarID being the key
     * @param user The User Object from User.java
     * @param carList ArrayList containing the carlist from database
     */
    public LoanResponseConstructor(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList)
            throws SensoConnectionFailureException {
        BasicLoans loan = new BasicLoans(user, carList);
        HashMap<String, Object> loans1 = loan.getLoans1();
        HashMap<String, Object> loans2 = loan.getLoans2();
        HashMap<String, Object> loans3 = loan.getLoans3();
        cars = loan.getCars();
        LoansScoreCalculator sensoScore = new LoansScoreCalculator(user, loans1, loans2, loans3, cars);
        loanTerm1 = sensoScore.getLoanTerm1();
        loanTerm2 = sensoScore.getLoanTerm2();
        loanTerm3 = sensoScore.getLoanTerm3();
        this.loanScore1 = sensoScore.getLoansScore1();
        this.loanScore2 = sensoScore.getLoansScore2();
        this.loanScore3 = sensoScore.getLoansScore3();
        this.score = new HashMap<String, Object>();
        generateScore(user, loans1, loans2, loans3);
    }

    /**
     * Generates score based on the User and their 3 possible loans
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     * @param buyer The User Object from User.java
     * @param loans1 The BasicLoan Object from BasicLoans.java
     * @param loans2 The BasicLoan Object from BasicLoans.java
     * @param loans3 The BasicLoan Object from BasicLoans.java
     */
    private void generateScore(HashMap<String, String> user, HashMap<String, Object> loans1,
                               HashMap<String, Object> loans2, HashMap<String, Object> loans3) {
        UserFactory userfactory = new UserFactory();
        User buyer = userfactory.createUser(user);
        for (String key :loanScore1.keySet()) {
            createInfo(buyer, key, loanTerm1, loans1, loanScore1, "1");
        }
        for (String key :loanScore2.keySet()) {
            createInfo(buyer, key, loanTerm2, loans2, loanScore2, "2");
        }
        for (String key :loanScore3.keySet()) {
            createInfo(buyer, key, loanTerm3, loans3, loanScore3, "3");
        }
    }

    /**
     * Creates a LoanApprovalCalculator Object for the BasicLoan Object
     * Gets the first month installment from loans HashMap
     * Associates each loanScore with each installment
     * Adds the loanScore + installment into score as elements with the key corresponding to the
     * BasicLoan Object (1,2, or 3) used for generating the scores and installments
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     * @param buyer The User Object from User.java
     * @param key The key in the key set for loan scores
     * @param loanTerm The loanTerm HashMap from LoanScoreCalculator.java
     * @param loans The loans HashMap from LoanScoreCalculator.java
     * @param loanScore The loanScore HashMap from LoanScoreCalculator.java
     * @param num the number of the loan (1, 2, or 3)
     */
    private void createInfo(User buyer, String key, HashMap<String, Integer> loanTerm,
                            HashMap<String, Object> loans,
                            HashMap<String, String> loanScore, String num) {
        LoanApprovalCalculator score = new LoanApprovalCalculator(buyer, loanScore.get(key));
        ArrayList<Object> info = new ArrayList<>();
        boolean basic = buyer.getMonthlyIncome().equals("0");
        info.add(Integer.toString(loanTerm.get(key)));
        info.add(score.getApprovalLikelihood(basic));
        HashMap<String, Object> response = getFirstMonthLoan(key, loans);
        info.add(response);
        addTripleLoans(key, num, info);
    }

    /**
     * This is a private method that adds triple loans information to the score instant variable hashmap
     * @param key The key in the key set for loan scores
     * @param num the number of the loan (1, 2, or 3)
     * @param info the information to add to the score hashmap
     */
    private void addTripleLoans(String key, String num, ArrayList<Object> info) {
        HashMap<String, ArrayList<Object>> tripleLoan = new HashMap<>();
        tripleLoan.put(num, info);
        if (this.score.containsKey(key)){
            ArrayList<HashMap<String, ArrayList<Object>>> existingLoans =
                    (ArrayList<HashMap<String, ArrayList<Object>>>) this.score.get(key);
            HashMap<String, ArrayList<Object>> test = new HashMap<>();
            test.put("1", info);
            if(!existingLoans.contains(test)){
                existingLoans.add(tripleLoan);
                this.score.put(key, existingLoans);
            }
        } else {
            ArrayList<HashMap<String, ArrayList<Object>>> loanArray = new ArrayList<>();
            loanArray.add(tripleLoan);
            this.score.put(key, loanArray);
        }
    }

    /**
     * Gets the first month loan installment from BasicLoan Object
     * @param key the CarID from Car Object from Car.java
     * @param loans the BasicLoan Object from BasicLoan.java
     * @return returns HashMap mapping, which is the first month loan installment from BasicLoans Object
     */
    private HashMap<String, Object> getFirstMonthLoan(String key, HashMap<String, Object> loans) {
        ArrayList<Object> installment = (ArrayList<Object>) loans.get(key);
        Gson gsonObj = new Gson();
        JsonObject jsonObject = gsonObj.toJsonTree(installment.get(0)).getAsJsonObject();
        Gson gsonObj2 = new Gson();
        Type mapType = new TypeToken<Map<Object, Object>>(){}.getType();
        HashMap<String, Object> response = gsonObj2.fromJson(jsonObject, mapType);
        return response;
    }
    /**
     * Getter methods for TraderAutoScore (loan score)
     * @return returns Score, which contains a HashMap of different possible BasicLoans associated with a LoanScore (element)
     * And matched with the corresponding CarID (key)
     */
    public HashMap<String, Object> getTraderAutoScore(){
        return this.score;
    }

    /**
     * Overriden method for LoanInfoInterface that allows other classes to directly access this class through this
     * Interface method.
     * @param user - a mapping of user info
     * @param carList - a list of cars we want the loan info for
     * @return A hashmap of cars with the loan info for the car
     */
    @Override
    public HashMap<String, Object> calculateLoans(HashMap<String, String> user,
                                                  ArrayList<HashMap<String, Object>> carList)
            throws SensoConnectionFailureException{
        LoanResponseConstructor loans = new LoanResponseConstructor(user, carList);
        return loans.getTraderAutoScore();
    }
}
