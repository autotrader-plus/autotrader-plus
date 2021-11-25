package packages.connectouterentity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/** This is a class that calls Senso Score API, and return information from Senso Score API.**/
public class ConnectSensoScoreAPI implements SensoAPIInterface{

    private HashMap<Object, Object> apiContent; // the return info from senso api call
    // the following variables are the fields needed for senso api call
    private int remainingBalance;
    private int creditScore;
    private int loanAge;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private int carValue;
    private String loanStartDate = "2021-11-11";

    /**
     * This is a method that collects input data for SensoAPI, then pings the senso API using the input data
     * @param senso_input - a hashmap that contains all input information used to ping senso API
     * @throws IOException - IOexception
     * @throws InterruptedException - exception when senso api call is interrupted or failed
     */
    public ConnectSensoScoreAPI(HashMap<String, String> senso_input) throws IOException, InterruptedException {
        populateSensoInputs(senso_input);
        String input_body = createJson();
        apiContent = CallSensoAPI(input_body);
    }

    /**
     * This is a helper method that helps populate the private variables which are also inputs to the senso
     * api call.
     * @param senso_input - a hashmap containing all input information used to ping senso api
     */
    private void populateSensoInputs(HashMap<String, String> senso_input) {
        remainingBalance = getfromMapping(senso_input, "balance");
        creditScore = getfromMapping(senso_input, "credit_score");
        loanAge = getfromMapping(senso_input,"loan_age");
        vehicleYear = getfromMapping(senso_input, "vehicle_year");
        carValue = getfromMapping(senso_input, "car_value");
        vehicleMake = senso_input.get("vehicle_make");
        vehicleModel = senso_input.get("vehicle_model");
        loanStartDate = senso_input.get("loan_start_date");
    }

    /**
     * A helper method that converts a value from a hashmap from the given key field
     * @param senso_input - a hashmap containing all input information used to ping senso api
     * @param field - the key of the hashmap of which the return value is returned
     * @return the integer format of the value of the given key in the hashmap
     */
    private Integer getfromMapping(HashMap<String, String> senso_input, String field){
        return Integer.parseInt(senso_input.get(field));
    }

    /**
     * A helper method that creates a json to send to senso api.
     * @return a json string format of all the key-value pairs to sent to senso api
     */
    private String createJson(){
        Map<String, Object> inputMap = new HashMap<String, Object>();
        inputMap.put("remainingBalance", remainingBalance);
        inputMap.put("creditScore", creditScore);
        inputMap.put("loanAge", loanAge);
        inputMap.put("vehicleMake", vehicleMake);
        inputMap.put("vehicleModel", vehicleModel);
        inputMap.put("vehicleYear", vehicleYear);
        inputMap.put("carValue", carValue);
        inputMap.put("loanStartDate", loanStartDate);

        // convert map to JSON String
        Gson gson = new Gson();
        return gson.toJson(inputMap);
    }

    /**
     * A helper method to call senso api and get back response based on information given in inputJson.
     * @param inputJson - a json string format of the input body to send to senso api
     * @return a hashmap representation of the return info from the senso api call
     * @throws IOException - IOexception
     * @throws InterruptedException - exception when the senso api call is interrupted or failed
     */
    private HashMap<Object, Object> CallSensoAPI(String inputJson) throws IOException, InterruptedException{

        String postEndpoint = "https://auto-loan-api.senso.ai/score";

        // Build and send a POST request to senso endpoint
        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndpoint))
                .header("Content-Type", "application/json")
                .header("x-api-key", System.getenv("SENSO_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        var client = HttpClient.newHttpClient();

        // Get response from senso api
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<Object, Object>>(){}.getType();
        return gson.fromJson(response.body(), mapType);
    }

    /**
     * A getter method to get the return info from senso api call based on the input information
     * @return a hashmap representation of the return info from senso api call
     */
    public HashMap<Object, Object> getReturnInfo() {
        return apiContent;
    }

    /**
     * A overriden method from SensoAPIInterface.
     * @param senso_input - the input mapping for senso api call
     * @return a hashmap representation of return info from senso api
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    @Override
    public HashMap<Object, Object> pingSensoAPI(HashMap<String, String> senso_input) throws IOException, InterruptedException {
        ConnectSensoScoreAPI senso_connector = new ConnectSensoScoreAPI(senso_input);
        return senso_connector.getReturnInfo();
    }
}
