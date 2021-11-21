package packages.backendlogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LoanInfoInterface {
    /**
     * Calculate the loans based on user information from user hashmap for the car in carlist
     * @param user - a mapping of user info
     * @param carlist - a list of cars we want the loan info for
     * @return the mapping of each car to the loans for the car
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    HashMap<String, Object> calculateLoans(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carlist) throws IOException, InterruptedException;
}
