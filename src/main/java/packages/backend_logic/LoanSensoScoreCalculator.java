package packages.backend_logic;

import packages.connect_api_db.ConnectSensoScoreAPI;
import packages.connect_api_db.SensoAPIInterface;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class LoanSensoScoreCalculator {
    private ArrayList<HashMap<String, Object>> loanList;
    private CarList<Car> cars;
    private User buyer;
    private ArrayList<HashMap<String, Integer>> loanTerms = new ArrayList<>(); // initialize an empty array list
    private ArrayList<HashMap<String, String>> loanScores = new ArrayList<>(); // initialize an empty array list

    public LoanSensoScoreCalculator(HashMap<String, String> user, ArrayList<HashMap<String, Object>> loans,
                                    CarList<Car> cars) throws SensoConnectionFailureException {
        UserFactory userfactory = new UserFactory();
        this.buyer = userfactory.createUser(user);
        this.loanList = loans;
        this.cars = cars;
        populateLoanTerms();
        populateLoanSensoScore();
    }

    private void populateLoanSensoScore() throws SensoConnectionFailureException {
        try {
            for (int i = 0; i < loanList.size(); i++) {
                int index = i + 1;
                callAPI(index);
            }
        } catch(IOException|InterruptedException e) {

            e.printStackTrace();
            throw new SensoConnectionFailureException();
        }
    }

    private void populateLoanTerms() {
        for (int i = 0; i < loanList.size(); i++) {
            int index = i + 1;
            findLoanAge(index);
        }
    }

    private void findLoanAge(int loanNumber){
        HashMap<String, Object> loanSet = loanList.get(loanNumber-1);
        for (String key : loanSet.keySet()){
            ArrayList loan = (ArrayList) loanSet.get(key);
            HashMap<String, Integer> loanTerm = new HashMap<>();
            loanTerm.put(key, loan.size());
            loanTerms.add(loanTerm);
        }
    }

    private void callAPI(int loanNumber) throws IOException, InterruptedException{
        HashMap<String, Integer> loanSet = loanTerms.get(loanNumber-1);
        for (int i = 0; i< cars.size(); i++) {
            Car car = cars.getCar(i);
            if(loanSet.size() != 0 && loanSet.get(car.getID()) != null){
                HashMap<String, String> mapping = prepAPICall(car, loanNumber);
                SensoAPIInterface connector = new ConnectSensoScoreAPI(mapping);
                HashMap<String, String> loanScore = new HashMap<>();
                loanScore.put(car.getID(), (String) connector.pingSensoAPI(mapping).get("sensoScore"));
                loanScores.add(loanScore);
            }
        }
    }

    private HashMap<String, String> prepAPICall(Car car, int loanNumber){
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("balance", Integer.toString(car.getPrice()));
        mapping.put("credit_score", buyer.getCreditScore());
        mapping.put("vehicle_year", car.getYear());
        mapping.put("vehicle_make", car.getBrand());
        mapping.put("car_value", Integer.toString(car.getPrice()));
        mapping.put("vehicle_model", car.getBrand());
        mapping.put("loan_start_date", "2021-01-05");
        HashMap<String, Integer> loanSet = loanTerms.get(loanNumber-1);
        mapping.put("loan_age", Integer.toString(loanSet.get(car.getID())));
        return mapping;
    }

    public HashMap<String, String> getLoansScore(int i){
        return loanScores.get(i-1);
    }

    public HashMap<String, Integer> getLoansTerm(int i){
        return loanTerms.get(i-1);
    }

    public User getUser() {
        return buyer;
    }

    public CarList<Car> getCars(){
        return cars;
    }

    public int getLoanSize(){
        return loanList.size();
    }
}
