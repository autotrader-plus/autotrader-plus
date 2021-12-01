package packages.backend_logic;

import packages.exceptions.SensoConnectionFailureException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface used to allow other classes to directly access the LoanResponseConstructor class.
 */
public interface LoanInfoInterface {
    /**
     * Calculate the loans based on user information from the user hashmap for the car in carList
     * @param user - A mapping of user information
     * @param carList - A list of Cars we want loan information from
     * @return A mapping of each car to its respective possible loans
     * @throws SensoConnectionFailureException If failed to connect to the Senso API
     */
    HashMap<String, Object> calculateLoans(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList) throws SensoConnectionFailureException;
}
