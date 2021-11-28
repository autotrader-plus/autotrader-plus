package packages.backendlogic;

import java.io.IOException;
import java.util.HashMap;
import packages.connectouterentity.ConnectSensoScoreAPI;



/**
 * Represents a calculation using the loan table given information speciifc to the user.
 */
public class LoanTableCalculation {
    private final LoanTableConstant constant = new LoanTableConstant();
    private int creditScore;
    private double PTI;
    private double DTI;
    private int employed;
    private int homeowner;
    private int sensoScore;

    public LoanTableCalculation(int creditScore, int employed, int homeowner, double PTI, double DTI,
                                HashMap<String, String> sensoScore) throws IOException, InterruptedException {
        this.creditScore = creditScore;
        this.employed = employed;
        this.homeowner = homeowner;
        this.PTI = PTI;
        this.DTI = DTI;
        setSensoScore(sensoScore);
    }

    /**
     * Gets the credit score of this user
     * @return An integer representing this user's credit score
     */
    public int getCreditScore(){
        return creditScore;
    }

    /**
     * Gets the employment status of this user
     * @return An integer representing this user's employment status
     */
    public int getEmployed(){
        return employed;
    }

    /**
     * Gets the homeowner status of this user
     * @return An integer representing this user's homeowner status
     */
    public int getHomeowner(){
        return homeowner;
    }

    /**
     * Gets the PTI ratio of this user
     * @return A double representing this user's PTI ratio
     */
    public double getPTI(){
        return PTI;
    }

    /**
     * Gets the DTI ratio of this user
     * @return A double representing this user's DTI ratio
     */
    public double getDTI(){
        return DTI;
    }

    /**
     * Gets the Senso score of this user
     * @return An integer representing this user's Senso score
     */
    public int getSensoScore(){
        return sensoScore;
    }

    public String getApprovalLikelihood(boolean basic) {
        double finalScore = getFinalScore();
        if (basic){
            return getApprovalLikelihoodBasic(finalScore);
        }
        else{
            return getApprovalLikelihoodAdvanced(finalScore);
        }
    }

    private String getApprovalLikelihoodBasic(double finalScore) {
        if (finalScore == constant.FINAL_BASIC1){
            return "Guaranteed";
        }
        else if (finalScore == constant.FINAL_BASIC2){
            return "Very Likely";
        }
        else if (finalScore == constant.FINAL_BASIC3) {
            return "Likely";
        }
        else if (finalScore == constant.FINAL_BASIC4) {
            return "Possible";
        }
        else{
            return "Very Unlikely";
        }
    }

    private String getApprovalLikelihoodAdvanced(double finalScore) {
        if (constant.FINAL_ADVANCED1_MIN <= finalScore && finalScore <= constant.FINAL_ADVANCED1_MAX){
            return "Guaranteed";
        }
        else if (constant.FINAL_ADVANCED2_MIN <= finalScore && finalScore <= constant.FINAL_ADVANCED2_MAX){
            return "Very Likely";
        }
        else if (constant.FINAL_ADVANCED3_MIN <= finalScore && finalScore <= constant.FINAL_ADVANCED3_MAX) {
            return "Likely";
        }
        else if (constant.FINAL_ADVANCED4_MIN <= finalScore && finalScore <= constant.FINAL_ADVANCED4_MAX) {
            return "Possible";
        }
        else{
            return "Very Unlikely";
        }
    }

    /**
     * Helper that calculates the final score of this user
     * @return A double representing the final score of this user.
     */
    private double getFinalScore() {
        double scoreFromCreditScore, scoreFromPTI, scoreFromDTI;
        scoreFromCreditScore = getScoreFromCreditScore();
        scoreFromPTI = getScoreFromPTI();
        scoreFromDTI = getScoreFromDTI();
        return scoreFromCreditScore * 5 + scoreFromPTI * 4 + scoreFromDTI * 3 + employed * 2 + homeowner + sensoScore;
    }

    /**
     * Helper method to translate the credit score to a score used in the final score calculation
     * @return A double representing the score used from credit score
     */
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

    /**
     * Helper method to translate the PTI ratio to a score used in the final score calculation
     * @return A double representing the score used from PTI ratio
     */
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

    /**
     * Helper method to translate the DTI to a score used in the final score calculation
     * @return A double representing the score used from DTI
     */
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

    /**
     * Sets the senso score to be used in the final calculation
     * @param sensoInput A HashMap of the Senso input required
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     */
    private void setSensoScore(HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        String sensoString = calculateSensoScore(sensoInput);

        if (constant.VERY_HIGH.equals(sensoString)) {
            this.sensoScore = -1;
        } else if (constant.HIGH.equals(sensoString)) {
            this.sensoScore = 0;
        } else if (constant.MEDIUM.equals(sensoString)) {
            this.sensoScore = 1;
        } else if (constant.LOW.equals(sensoString) || constant.VERY_LOW.equals(sensoString)) {
            this.sensoScore = 2;
        } else {
            this.sensoScore = 0; // setting this as default if sensoScore didn't hit any of the proper cases
        }
    }

    /**
     * Helper method that gets the Senso score from the ConnectSensoScoreAPI
     * @param sensoInput A HashMap of the Senso input required
     * @return A string representing the output of the Senso Score API
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     */
    private String calculateSensoScore(HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        ConnectSensoScoreAPI connect = new ConnectSensoScoreAPI(sensoInput);
        HashMap<String, Object> sensoReturn = connect.getReturnInfo();
        if (sensoReturn.size() > 1){
            return "Error 400 or 500";
        }
        else {
            return (String) sensoReturn.get("sensoScore");
        }

    }

}
