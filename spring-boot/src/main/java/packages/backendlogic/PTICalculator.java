package packages.backendlogic;


import packages.informationmanipulation.ReturnCarInformation;
import packages.informationmanipulation.ReturnUserInformation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PTICalculator {
    private static int userID;
    private static Car car;
    private static double PTI = 0;

    /**
     * creates a PTICalculator Object based on provided info
     * @param user_id user's id number
     * @param c Car object
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException
     */
    public PTICalculator(int user_id, Car c) throws SQLException, IOException, InterruptedException {
        userID = user_id;
        car = c;
        calculatePTI();
    }

    /**
     * The following are getter methods
     * @return returns int/Car Object info when called
     */
    public int getUserID() {return userID;}

    public Car getCar() {return car;}

    public double getPTI() {return PTI;}

    /**
     * calculates user's PTI for a given car
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException
     */
    private void calculatePTI() throws IOException, InterruptedException, SQLException {
        HashMap<String, String> userInfo = ReturnUserInformation.returnUser(userID);

        ArrayList<HashMap<String, String>> loans = getLoans(userInfo);
        // For now assuming installment is the same for every loan listed under given car
        int monthly_pmt = Integer.parseInt(loans.get(0).get("installment"));

        int monthly_income = Integer.parseInt(userInfo.get("Monthly Income"));
        PTI = (double) Math.round( (double) monthly_pmt / monthly_income);
    }

    /**
     * gets the loans available for a given user and car
     * @param userInfo user information
     * @return available loans to this user for the given car
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException
     */
    private ArrayList<HashMap<String, String>> getLoans(HashMap<String, String> userInfo) throws SQLException, IOException, InterruptedException {
        // Create new car list containing the car given
        ArrayList<HashMap<String, String>> car_list = new ArrayList<>();
        car_list.add(ReturnCarInformation.returnCarDetails(Integer.parseInt(car.returnID())));

        // Create Loans object to generate available loans
        Loans loans = new Loans(userInfo, car_list);
        // return available loans for the specific car
        return (ArrayList<HashMap<String, String>>) loans.getLoans().get(car.returnID());
    }

}
