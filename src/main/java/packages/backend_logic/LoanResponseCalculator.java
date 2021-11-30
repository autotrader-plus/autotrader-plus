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

public class LoanResponseCalculator implements LoanInfoInterface {
    private HashMap<String, Object> Score;
    private HashMap<String, String> loanScore1;
    private HashMap<String, String> loanScore2;
    private HashMap<String, String> loanScore3;
    private HashMap<String, Integer> loanTerm1;
    private HashMap<String, Integer> loanTerm2;
    private HashMap<String, Integer> loanTerm3;
    private CarList<Car> cars;

    public LoanResponseCalculator(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList)
            throws SensoConnectionFailureException, IOException, InterruptedException {
        Loans loan = new Loans(user, carList);
        HashMap<String, Object> loans1 = loan.getLoans1();
        HashMap<String, Object> loans2 = loan.getLoans2();
        HashMap<String, Object> loans3 = loan.getLoans3();
        cars = loan.getCars();
        LoanApprovalScore score = new LoanApprovalScore(user, loans1, loans2, loans3, cars);
        loanTerm1 = score.getLoanTerm1();
        loanTerm2 = score.getLoanTerm2();
        loanTerm3 = score.getLoanTerm3();
        this.loanScore1 = score.getLoansScore1();
        this.loanScore2 = score.getLoansScore2();
        this.loanScore3 = score.getLoansScore3();
        this.Score = new HashMap<String, Object>();
        generateScore(user, loans1, loans2, loans3);
    }

    private void generateScore(HashMap<String, String> user, HashMap<String, Object> loans1,
                               HashMap<String, Object> loans2, HashMap<String, Object> loans3)
            throws IOException, InterruptedException {
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

    private void createInfo(User buyer, String key, HashMap<String, Integer> loanTerm,
                            HashMap<String, Object> loans,
                            HashMap<String, String> loanScore, String num)
            throws IOException, InterruptedException {
        LoanApprovalCalculation score = new LoanApprovalCalculation(buyer, loanScore.get(key));
        ArrayList<Object> info = new ArrayList<>();
        boolean basic = buyer.getMonthlyIncome().equals("0");
        info.add(Integer.toString(loanTerm.get(key)));
        info.add(score.getApprovalLikelihood(basic));
        HashMap<String, Object> response = getFirstMonthLoan(key, loans);
        info.add(response);

        HashMap<String, ArrayList<Object>> tripleLoan = new HashMap<>();
        tripleLoan.put(num, info);

//        System.out.println(tripleLoan);
        if (this.Score.containsKey(key)){
            ArrayList<HashMap<String, ArrayList<Object>>> existingLoans =
                    (ArrayList<HashMap<String, ArrayList<Object>>>) this.Score.get(key);
            HashMap<String, ArrayList<Object>> test = new HashMap<>();
            test.put("1", info);
            if(!existingLoans.contains(test)){
                existingLoans.add(tripleLoan);
                this.Score.put(key, existingLoans);
            }

        } else {
            ArrayList<HashMap<String, ArrayList<Object>>> loanArray = new ArrayList<>();
            loanArray.add(tripleLoan);
            this.Score.put(key, loanArray);
        }
    }

    private HashMap<String, Object> getFirstMonthLoan(String key, HashMap<String, Object> loans) {
        ArrayList<Object> installment = (ArrayList<Object>) loans.get(key);
        Gson gsonObj = new Gson();
        JsonObject jsonObject = gsonObj.toJsonTree(installment.get(0)).getAsJsonObject();
        Gson gsonObj2 = new Gson();
        Type mapType = new TypeToken<Map<Object, Object>>(){}.getType();
        HashMap<String, Object> response = gsonObj2.fromJson(jsonObject, mapType);
        return response;
    }

    public HashMap<String, Object> getTraderAutoScore(){
        return this.Score;
    }

    /**
     * Overriden method for LoanInfoInterface that allows other classes to directly access this class through this
     * interface method.
     * @param user - a mapping of user info
     * @param carList - a list of cars we want the loan info for
     * @return a hashmap of cars with the loan info for the car
     */
    @Override
    public HashMap<String, Object> calculateLoans(HashMap<String, String> user,
                                                  ArrayList<HashMap<String, Object>> carList)
            throws SensoConnectionFailureException{
        try {
            LoanResponseCalculator loans = new LoanResponseCalculator(user, carList);
            return loans.getTraderAutoScore();
        }catch(IOException|InterruptedException e){
            throw new SensoConnectionFailureException();
        }
    }
}
