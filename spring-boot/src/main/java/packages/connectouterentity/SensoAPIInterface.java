package packages.connectouterentity;

import java.io.IOException;
import java.util.HashMap;

public interface SensoAPIInterface {
    /**
     * A method to ping senso api
     * @param senso_input - the input mapping for senso api call
     * @return a hashmap representation of return info from senso api
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    HashMap<Object, Object> pingSensoAPI(HashMap<String, String> senso_input) throws IOException, InterruptedException;
}
