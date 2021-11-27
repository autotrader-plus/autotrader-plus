package packages.backendlogic;

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
    public final int CREDIT_SCORE_RANGE5 = 701;

    public final double PTI_RANGE1_MIN = 0.16;
    public final double PTI_RANGE1_MAX = 0.2;
    public final double PTI_RANGE2_MIN = 0.13;
    public final double PTI_RANGE2_MAX = 0.15;
    public final double PTI_RANGE3_MIN = 0.11;
    public final double PTI_RANGE3_MAX = 0.12;
    public final double PTI_RANGE4_MIN = 0.09;
    public final double PTI_RANGE4_MAX = 0.1;
    public final double PTI_RANGE5 = 0.09;

    public final double DTI_RANGE1 = 0.5;
    public final double DTI_RANGE2_MIN = 0.4;
    public final double DTI_RANGE2_MAX = 0.49;
    public final double DTI_RANGE3_MIN = 0.36;
    public final double DTI_RANGE3_MAX = 0.4;
    public final double DTI_RANGE4_MIN = 0.3;
    public final double DTI_RANGE4_MAX = 0.36;
    public final double DTI_RANGE5 = 0.3;

    public final double SCORE2 = 0.2;
    public final double SCORE3 = 0.3;
    public final double SCORE45 = 0.45;
    public final double SCORE5 = 0.5;
    public final double SCORE65 = 0.65;
    public final double SCORE7 = 0.7;
    public final double SCORE8 = 0.8;
    public final double SCORE85 = 0.85;
    public final double SCORE1 = 1.0;

    private int creditScore;
    private double PTI;
    private double DTI;
    private int employed;
    private int homeowner;

    public LoanTable(int creditScore, int employed, int homeowner, double PTI, double DTI){
        this.creditScore = creditScore;
        this.employed = employed;
        this.homeowner = homeowner;
        this.PTI = PTI;
        this.DTI = DTI;
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

    public double getFinalScore() {
        double scoreFromCreditScore, scoreFromPTI, scoreFromDTI;
        double calculatedDTIScore;

        // Checking cases for credit score
        if (creditScore >= CREDIT_SCORE_RANGE1_MIN && creditScore <= CREDIT_SCORE_RANGE1_MAX){
            scoreFromCreditScore = SCORE2;
        }
        else if (creditScore >= CREDIT_SCORE_RANGE2_MIN && creditScore <= CREDIT_SCORE_RANGE2_MAX){
            scoreFromCreditScore = SCORE45;
        }
        else if (creditScore >= CREDIT_SCORE_RANGE3_MIN && creditScore <= CREDIT_SCORE_RANGE3_MAX){
            scoreFromCreditScore = SCORE65;
        }
        else if (creditScore >= CREDIT_SCORE_RANGE4_MIN && creditScore <= CREDIT_SCORE_RANGE4_MAX){
            scoreFromCreditScore = SCORE8;
        }
        else{
            scoreFromCreditScore = SCORE1;
        }

        // Checking cases for PTI
        if (PTI >= PTI_RANGE1_MIN && PTI <= PTI_RANGE1_MAX){
            scoreFromPTI = SCORE2;
        }
        else if (PTI >= PTI_RANGE2_MIN && PTI <= PTI_RANGE2_MAX){
            scoreFromPTI = SCORE45;
        }
        else if (PTI >= PTI_RANGE3_MIN && PTI <= PTI_RANGE3_MAX){
            scoreFromPTI = SCORE7;
        }
        else if (PTI >= PTI_RANGE4_MIN && PTI <= PTI_RANGE4_MAX){
            scoreFromPTI = SCORE85;
        }
        else{
            scoreFromPTI = SCORE1;
        }

        // Checking cases for DTI
        if (DTI >= DTI_RANGE1){
            scoreFromDTI = SCORE3;
        }
        else if (DTI >= DTI_RANGE2_MIN && DTI <= DTI_RANGE2_MAX){
            scoreFromDTI = SCORE5;
        }
        else if (DTI >= DTI_RANGE3_MIN && DTI <= DTI_RANGE3_MAX){
            scoreFromDTI = SCORE7;
        }
        else if (DTI >= DTI_RANGE4_MIN && DTI <= DTI_RANGE4_MAX){
            scoreFromDTI = SCORE85;
        }
        else{
            scoreFromDTI = SCORE1;
        }

        return scoreFromCreditScore * 5 + scoreFromPTI * 4 + scoreFromDTI * 3 + employed * 2 + homeowner;
    }




}
