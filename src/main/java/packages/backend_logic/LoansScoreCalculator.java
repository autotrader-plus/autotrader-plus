package packages.backend_logic;

import packages.connect_api_db.ConnectSensoScoreAPI;
import packages.connect_api_db.SensoAPIInterface;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LoansScoreCalculator {
    private HashMap<String, Integer> loanTerm1;
    private HashMap<String, Integer> loanTerm2;
    private HashMap<String, Integer> loanTerm3;
    private HashMap<String, Object> loans1;
    private HashMap<String, Object> loans2;
    private HashMap<String, Object> loans3;
    private HashMap<String, String> loanScore1;
    private HashMap<String, String> loanScore2;
    private HashMap<String, String> loanScore3;
    private CarList<Car> cars;
    private User buyer;

    /**
     * Creates an empty HashMap LoansScoreCalculator that will be storing the SensoApi return values
     * Creates an advanced or basic User Object based on the amount of info given
     * Calculates the budget of the User
     * Determines loanAge of each individual loan in BasicLoans Objects
     * Collects and organizes Car, User, and Loan data for the SensoScoreApi call
     * Stores the SensoScoreApi return values in HashMap BasicLoans
     * @param user The User Object from User.java
     * @param carList The CarList Object CarList.java
     * @param loan1 The BasicLoans object from BasicLoans.java
     * @param loan2 The BasicLoans object from BasicLoans.java
     * @param loan3 The BasicLoans object from BasicLoans.java
     */
    public LoansScoreCalculator(HashMap<String, String> user, HashMap<String, Object> loans1,
                                HashMap<String, Object> loans2, HashMap<String, Object> loans3,
                                CarList<Car> cars) throws SensoConnectionFailureException {
        loanTerm1 = new HashMap<>();
        loanTerm2 = new HashMap<>();
        loanTerm3 = new HashMap<>();
        loanScore1 = new HashMap<>();
        loanScore2 = new HashMap<>();
        loanScore3 = new HashMap<>();
        UserFactory userfactory = new UserFactory();
        buyer = userfactory.createUser(user);
        this.loans1 = loans1;
        this.loans2 = loans2;
        this.loans3 = loans3;
        this.cars = cars;
        findLoanAge();
        // preps info and calls SensoAPI
        try {
            callAPI();
        } catch(IOException|InterruptedException e) {
            e.printStackTrace();
            throw new SensoConnectionFailureException();
        }
    }

    /**
     * Calculates the LoanAge of each individual loan by checking the size of the BasicLoan Object,
     * Adds LoanAge into loanTerm HashMaps
     */
    private void findLoanAge(){
        for (String key :this.loans1.keySet()){
            ArrayList loan = (ArrayList) this.loans1.get(key);
            this.loanTerm1.put(key, loan.size());
        }
        for (String key :this.loans2.keySet()){
            ArrayList loan = (ArrayList) this.loans2.get(key);
            this.loanTerm2.put(key, loan.size());
        }
        for (String key :this.loans3.keySet()){
            ArrayList loan = (ArrayList) this.loans3.get(key);
            this.loanTerm3.put(key, loan.size());
        }
    }

    /**
     * Calls the SensoScoreApi and adds the Api return values into the LoansScoreCalculator Object
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     */
    private void callAPI() throws IOException, InterruptedException{
        for (int i = 0; i< cars.size(); i++){
            Car car = cars.getCar(i);
            if(loanTerm1.size() != 0 && loanTerm1.get(car.getID()) != null){
                HashMap<String, String> mapping1 = prepAPICall(car, 1);
                SensoAPIInterface connector1 = new ConnectSensoScoreAPI(mapping1);
                loanScore1.put(car.getID(), (String) connector1.pingSensoAPI(mapping1).get("sensoScore"));
            }
            if(loanTerm2.size() != 0 && loanTerm2.get(car.getID()) != null) {
                HashMap<String, String> mapping2 = prepAPICall(car, 2);
                SensoAPIInterface connector2 = new ConnectSensoScoreAPI(mapping2);
                loanScore2.put(car.getID(), (String) connector2.pingSensoAPI(mapping2).get("sensoScore"));
            }
            if(loanTerm3.size() != 0 && loanTerm3.get(car.getID()) != null) {
                HashMap<String, String> mapping3 = prepAPICall(car, 3);
                SensoAPIInterface connector3 = new ConnectSensoScoreAPI(mapping3);
                loanScore3.put(car.getID(), (String) connector3.pingSensoAPI(mapping3).get("sensoScore"));
            }
        }
    }

    /**
     * Creates a HashMap containing User, Car, loanTerm info for the SensoScoreApi call
     * @param car The Car Object from Car.java
     * @param num The number indicating which LoanScore (1-3) is being prepared
     * @return returns HashMap mapping, which is all the info required for SensoScoreApi call
     */
    private HashMap<String, String> prepAPICall(Car car, int num){
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("balance", Integer.toString(car.getPrice()));
        mapping.put("credit_score", buyer.getCreditScore());
        mapping.put("vehicle_year", car.getYear());
        mapping.put("vehicle_make", car.getBrand());
        mapping.put("car_value", Integer.toString(car.getPrice()));
        mapping.put("vehicle_model", car.getBrand());
        mapping.put("loan_start_date", "2021-01-05");
        if(num == 1){
            mapping.put("loan_age", Integer.toString(loanTerm1.get(car.getID())));
        }
        if(num == 2){
            mapping.put("loan_age", Integer.toString(loanTerm2.get(car.getID())));
        }
        if(num == 3){
            mapping.put("loan_age", Integer.toString(loanTerm3.get(car.getID())));
        }
        return mapping;
    }

    /**
     * LoansScore1 getter method
     * @return returns LoansScore1 Object info when called, in the form of a HashMap<String, String>
     *     where the key is CarID, and element is the SensoScoreApi return info
     */
    public HashMap<String, String> getLoansScore1(){
        loanScore1.values().removeAll(Collections.singleton(null));
        return loanScore1;
    }

    /**
     * LoansScore2 getter method
     * @return returns LoansScore2 Object info when called, in the form of a HashMap<String, String>
     *     where the key is CarID, and element is the SensoScoreApi return info
     */
    public HashMap<String,  String> getLoansScore2(){
        loanScore2.values().removeAll(Collections.singleton(null));
        return loanScore2;
    }
    /**
     * LoansScore3 getter method
     * @return returns LoansScore3 Object info when called, in the form of a HashMap<String, String>
     *     where the key is CarID, and element is the SensoScoreApi return info
     */
    public HashMap<String, String> getLoansScore3(){
        loanScore3.values().removeAll(Collections.singleton(null));
        return loanScore3;
    }

    /**
     * The loanTerm1 getter method
     * @return returns loanTerm1 Object info when called, in the form of a HashMap<String, Integer>
     *     where the key is CarID, and element is the age of the loans.
     */
    public HashMap<String, Integer> getLoanTerm1(){
        return loanTerm1;
    }

    /**
     * The loanTerm2 getter method
     * @return returns loanTerm2 Object info when called, in the form of a HashMap<String, Integer>
     *     where the key is CarID, and element is the age of the loans.
     */
    public HashMap<String, Integer> getLoanTerm2(){
        return loanTerm2;
    }

    /**
     * The loanTerm3 getter method
     * @return returns loanTerm3 Object info when called, in the form of a HashMap<String, Integer>
     *     where the key is CarID, and element is the age of the loans.
     */
    public HashMap<String, Integer> getLoanTerm3(){
        return loanTerm3;
    }
}
