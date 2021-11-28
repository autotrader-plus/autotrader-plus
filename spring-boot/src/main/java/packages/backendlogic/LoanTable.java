package packages.backendlogic;

import packages.connectouterentity.ConnectSensoScoreAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanTable {
    public final int CREDIT_SCORE_RANGE1_MIN = 300;
    public final int CREDIT_SCORE_RANGE1_MAX = 500;
    public final int CREDIT_SCORE_RANGE2_MIN = 501;
    public final int CREDIT_SCORE_RANGE2_MAX = 600;
    public final int CREDIT_SCORE_RANGE3_MIN = 601;
    public final int CREDIT_SCORE_RANGE3_MAX = 660;
    public final int CREDIT_SCORE_RANGE4_MIN = 661;
    public final int CREDIT_SCORE_RANGE4_MAX = 700;

    public final double PTI_RANGE1_MIN = 0.16;
    public final double PTI_RANGE1_MAX = 0.2;
    public final double PTI_RANGE2_MIN = 0.13;
    public final double PTI_RANGE2_MAX = 0.15;
    public final double PTI_RANGE3_MIN = 0.11;
    public final double PTI_RANGE3_MAX = 0.12;
    public final double PTI_RANGE4_MIN = 0.09;
    public final double PTI_RANGE4_MAX = 0.1;

    public final double DTI_RANGE1 = 0.5;
    public final double DTI_RANGE2_MIN = 0.4;
    public final double DTI_RANGE2_MAX = 0.49;
    public final double DTI_RANGE3_MIN = 0.36;
    public final double DTI_RANGE3_MAX = 0.4;
    public final double DTI_RANGE4_MIN = 0.3;
    public final double DTI_RANGE4_MAX = 0.36;

    public final double SCORE2 = 0.2;
    public final double SCORE3 = 0.3;
    public final double SCORE45 = 0.45;
    public final double SCORE5 = 0.5;
    public final double SCORE65 = 0.65;
    public final double SCORE7 = 0.7;
    public final double SCORE8 = 0.8;
    public final double SCORE85 = 0.85;
    public final double SCORE1 = 1.0;

    public final String VERY_HIGH = "Very High";
    public final String HIGH = "High";
    public final String MEDIUM = "Medium";
    public final String LOW = "Low";
    public final String VERY_LOW = "Very Low";

    private int creditScore;
    private double PTI;
    private double DTI;
    private int employed;
    private int homeowner;
    private int sensoScore;

    public LoanTable(int creditScore, int employed, int homeowner, double PTI, double DTI,
                     HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        this.creditScore = creditScore;
        this.employed = employed;
        this.homeowner = homeowner;
        this.PTI = PTI;
        this.DTI = DTI;
        setSensoScore(sensoInput);
    }

    // getters
    public int getCreditScore(){
        return creditScore;
    }

    public int getEmployed(){
        return employed;
    }

    public int getHomeowner(){
        return homeowner;
    }

    public double getPTI(){
        return PTI;
    }

    public double getDTI(){
        return DTI;
    }

    public int getSensoScore(){
        return sensoScore;
    }

    public void setSensoScore(HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        String sensoString = calculateSensoScore(sensoInput);

        switch(sensoString) {
            case VERY_HIGH:
                this.sensoScore = -1;
                break;
            case HIGH:
                this.sensoScore = 0;
                break;
            case MEDIUM:
                this.sensoScore = 1;
                break;
            case LOW:
            case VERY_LOW:
                this.sensoScore = 2;
                break;
            default:
                this.sensoScore = 0; // setting this as default if sensoScore didn't hit any of the proper cases
                break;
        }
    }

    public String calculateSensoScore(HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        ConnectSensoScoreAPI sensoScore = new ConnectSensoScoreAPI(sensoInput);
        HashMap<String, Object> sensoReturn = sensoScore.getReturnInfo();
        if (sensoReturn.size() > 1){
            return "Error 400 or Error 500";
        }
        else{
            return (String) sensoReturn.get("sensoScore");
        }
    }

    public double getFinalScore() {
        double scoreFromCreditScore = setScoreFromCreditScore();
        double scoreFromPTI = setScoreFromPTI();
        double scoreFromDTI = setScoreFromDTI();

        return scoreFromCreditScore * 5 + scoreFromPTI * 4 + scoreFromDTI * 3 + employed * 2 + homeowner + sensoScore;
    }

    private double setScoreFromCreditScore() {
        // Checking cases for credit score
        if (creditScore >= CREDIT_SCORE_RANGE1_MIN && creditScore <= CREDIT_SCORE_RANGE1_MAX){
            return SCORE2;
        }
        else if (creditScore >= CREDIT_SCORE_RANGE2_MIN && creditScore <= CREDIT_SCORE_RANGE2_MAX){
            return SCORE45;
        }
        else if (creditScore >= CREDIT_SCORE_RANGE3_MIN && creditScore <= CREDIT_SCORE_RANGE3_MAX){
            return SCORE65;
        }
        else if (creditScore >= CREDIT_SCORE_RANGE4_MIN && creditScore <= CREDIT_SCORE_RANGE4_MAX){
            return SCORE8;
        }
        else{
            return SCORE1;
        }
    }

    private double setScoreFromPTI() {
        // Checking cases for PTI
        if (PTI >= PTI_RANGE1_MIN && PTI <= PTI_RANGE1_MAX){
            return SCORE2;
        }
        else if (PTI >= PTI_RANGE2_MIN && PTI <= PTI_RANGE2_MAX){
            return SCORE45;
        }
        else if (PTI >= PTI_RANGE3_MIN && PTI <= PTI_RANGE3_MAX){
            return SCORE7;
        }
        else if (PTI >= PTI_RANGE4_MIN && PTI <= PTI_RANGE4_MAX){
            return SCORE85;
        }
        else{
            return SCORE1;
        }
    }

    private double setScoreFromDTI() {
        // Checking cases for DTI
        if (DTI >= DTI_RANGE1){
            return SCORE3;
        }
        else if (DTI >= DTI_RANGE2_MIN && DTI <= DTI_RANGE2_MAX){
            return SCORE5;
        }
        else if (DTI >= DTI_RANGE3_MIN && DTI <= DTI_RANGE3_MAX){
            return SCORE7;
        }
        else if (DTI >= DTI_RANGE4_MIN && DTI <= DTI_RANGE4_MAX){
            return SCORE85;
        }
        else{
            return SCORE1;
        }
    }

}
