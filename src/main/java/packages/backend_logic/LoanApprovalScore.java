package packages.backend_logic;

import packages.connect_outer_entity.ConnectSensoScoreAPI;
import packages.connect_outer_entity.SensoAPIInterface;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LoanApprovalScore {
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

    public LoanApprovalScore(HashMap<String, String> user, HashMap<String, Object> loans1,
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

    public HashMap<String, String> getLoansScore1(){
        loanScore1.values().removeAll(Collections.singleton(null));
        return loanScore1;
    }

    public HashMap<String,  String> getLoansScore2(){
        loanScore2.values().removeAll(Collections.singleton(null));
        return loanScore2;
    }
    public HashMap<String, String> getLoansScore3(){
        loanScore3.values().removeAll(Collections.singleton(null));
        return loanScore3;
    }

    public HashMap<String, Integer> getLoanTerm1(){
        return loanTerm1;
    }

    public HashMap<String, Integer> getLoanTerm2(){
        return loanTerm2;
    }

    public HashMap<String, Integer> getLoanTerm3(){
        return loanTerm3;
    }

    public User getUser() {
        return buyer;
    }

    public CarList<Car> getCars(){
        return cars;
    }
}
