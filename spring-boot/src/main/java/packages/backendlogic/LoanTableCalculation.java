package packages.backendlogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import packages.backendlogic.LoanTableConstant;

public class LoanTableCalculation {
    private LoanTableConstant constant = new LoanTableConstant();
    private int creditScore;
    private double PTI;
    private double DTI;
    private int employed;
    private int homeowner;
    private int sensoScore;

    public LoanTableCalculation(int creditScore, int employed, int homeowner, double PTI, double DTI, String sensoScore){
        this.creditScore = creditScore;
        this.employed = employed;
        this.homeowner = homeowner;
        this.PTI = PTI;
        this.DTI = DTI;
        setSensoScore(sensoScore);
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

    public double getFinalScore() {
        double scoreFromCreditScore, scoreFromPTI, scoreFromDTI;
        scoreFromCreditScore = getScoreFromCreditScore();
        scoreFromPTI = getScoreFromPTI();
        scoreFromDTI = getScoreFromDTI();
        return scoreFromCreditScore * 5 + scoreFromPTI * 4 + scoreFromDTI * 3 + employed * 2 + homeowner + sensoScore;
    }

    private double getScoreFromCreditScore() {
        double scoreFromCreditScore;// Checking cases for credit score
        if (creditScore >= constant.CREDIT_SCORE_RANGE1_MIN && creditScore <= constant.CREDIT_SCORE_RANGE1_MAX){
            scoreFromCreditScore = constant.SCORE2;
        }
        else if (creditScore >= constant.CREDIT_SCORE_RANGE2_MIN && creditScore <= constant.CREDIT_SCORE_RANGE2_MAX){
            scoreFromCreditScore = constant.SCORE45;
        }
        else if (creditScore >= constant.CREDIT_SCORE_RANGE3_MIN && creditScore <= constant.CREDIT_SCORE_RANGE3_MAX){
            scoreFromCreditScore = constant.SCORE65;
        }
        else if (creditScore >= constant.CREDIT_SCORE_RANGE4_MIN && creditScore <= constant.CREDIT_SCORE_RANGE4_MAX){
            scoreFromCreditScore = constant.SCORE8;
        }
        else{
            scoreFromCreditScore = constant.SCORE1;
        }
        return scoreFromCreditScore;
    }

    private double getScoreFromPTI() {
        double scoreFromPTI;// Checking cases for PTI
        if (PTI >= constant.PTI_RANGE1_MIN && PTI <= constant.PTI_RANGE1_MAX){
            scoreFromPTI = constant.SCORE2;
        }
        else if (PTI >= constant.PTI_RANGE2_MIN && PTI <= constant.PTI_RANGE2_MAX){
            scoreFromPTI = constant.SCORE45;
        }
        else if (PTI >= constant.PTI_RANGE3_MIN && PTI <= constant.PTI_RANGE3_MAX){
            scoreFromPTI = constant.SCORE7;
        }
        else if (PTI >= constant.PTI_RANGE4_MIN && PTI <= constant.PTI_RANGE4_MAX){
            scoreFromPTI = constant.SCORE85;
        }
        else{
            scoreFromPTI = constant.SCORE1;
        }
        return scoreFromPTI;
    }

    private double getScoreFromDTI() {
        double scoreFromDTI;
        // Checking cases for DTI
        if (DTI >= constant.DTI_RANGE1){
            scoreFromDTI = constant.SCORE3;
        }
        else if (DTI >= constant.DTI_RANGE2_MIN && DTI <= constant.DTI_RANGE2_MAX){
            scoreFromDTI = constant.SCORE5;
        }
        else if (DTI >= constant.DTI_RANGE3_MIN && DTI <= constant.DTI_RANGE3_MAX){
            scoreFromDTI = constant.SCORE7;
        }
        else if (DTI >= constant.DTI_RANGE4_MIN && DTI <= constant.DTI_RANGE4_MAX){
            scoreFromDTI = constant.SCORE85;
        }
        else{
            scoreFromDTI = constant.SCORE1;
        }
        return scoreFromDTI;

    }

    private void setSensoScore(String sensoScore) {
        if (constant.VERY_HIGH.equals(sensoScore)) {
            this.sensoScore = -1;
        } else if (constant.HIGH.equals(sensoScore)) {
            this.sensoScore = 0;
        } else if (constant.MEDIUM.equals(sensoScore)) {
            this.sensoScore = 1;
        } else if (constant.LOW.equals(sensoScore) || constant.VERY_LOW.equals(sensoScore)) {
            this.sensoScore = 2;
        } else {
            this.sensoScore = 0; // setting this as default if sensoScore didn't hit any of the proper cases
        }
    }

}
