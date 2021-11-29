package packages.connectouterentity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/** This is a class that calls Senso Rate API, and return information from Senso Rate API.**/
public class ConnectSensoRateAPI implements SensoAPIInterface{

    private static HashMap<String, Object> apiContent; // the return info from senso api call
    // the following variables are the fields needed for senso api call
    private int loanAmount;
    private int creditScore;
    private int pytBudget;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private int vehicleKms;
    private int listPrice;
    private int downpayment;

    /**
     * This is a method that collects input data for SensoAPI, then pings the senso API using the input data
     * @param sensoInput - a hashmap that contains all input information used to ping senso API
     * @throws IOException - IOexception
     * @throws InterruptedException - exception when senso api call is interrupted or failed
     */
    public ConnectSensoRateAPI(HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        populateSensoInputs(sensoInput);
        String input_body = createJson();
        apiContent = CallSensoAPI(input_body);
    }

    /**
     * This is a helper method that helps populate the private static variables which are also inputs to the senso
     * api call.
     * @param sensoInput - a hashmap containing all input information used to ping senso api
     */
    private void populateSensoInputs(HashMap<String, String> sensoInput) {
        loanAmount = getfromMapping(sensoInput, "loan_amount");
        creditScore = getfromMapping(sensoInput, "credit_score");
        pytBudget = getfromMapping(sensoInput,"payment_budget");
        vehicleYear = getfromMapping(sensoInput, "vehicle_year");
        vehicleKms = getfromMapping(sensoInput, "vehicle_kms");
        listPrice = getfromMapping(sensoInput, "list_price");
        downpayment = getfromMapping(sensoInput, "downpayment");
        vehicleMake = sensoInput.get("vehicle_make");
        vehicleModel = sensoInput.get("vehicle_model");
    }

    /**
     * A helper method that converts a value from a hashmap from the given key field
     * @param sensoInput - a hashmap containing all input information used to ping senso api
     * @param field - the key of the hashmap of which the return value is returned
     * @return the integer format of the value of the given key in the hashmap
     */
    private Integer getfromMapping(HashMap<String, String> sensoInput, String field){
        return Integer.parseInt(sensoInput.get(field));
    }

    /**
     * A helper method that creates a json to send to senso api.
     * @return a json string format of all the key-value pairs to sent to senso api
     */
    private String createJson(){
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("loanAmount", loanAmount);
        inputMap.put("creditScore", creditScore);
        inputMap.put("pytBudget", pytBudget);
        inputMap.put("vehicleMake", vehicleMake);
        inputMap.put("vehicleModel", vehicleModel);
        inputMap.put("vehicleYear", vehicleYear);
        inputMap.put("vehicleKms", vehicleKms);
        inputMap.put("listPrice", listPrice);
        inputMap.put("downpayment", downpayment);

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
    private HashMap<String, Object> CallSensoAPI(String inputJson) throws IOException, InterruptedException{

        String postEndpoint = System.getenv("SENSO_API_URL");

        // Build and send a POST request to senso endpoint
        HttpRequest request = buildPostRequest(inputJson, postEndpoint);
        return getPostResponse(request);
    }

    /**
     * A getter method to get the return info from senso api call based on the input information
     * @return a hashmap representation of the return info from senso api call
     */
    public HashMap<String, Object> getReturnInfo() {
        return apiContent;
    }

    /**
     * A overriden method from SensoAPIInterface.
     * @param sensoInput - the input mapping for senso api call
     * @return a hashmap representation of return info from senso api
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    @Override
    public HashMap<String, Object> pingSensoAPI(HashMap<String, String> sensoInput) throws IOException, InterruptedException {
        ConnectSensoRateAPI sensoConnector = new ConnectSensoRateAPI(sensoInput);
        return sensoConnector.getReturnInfo();
    }
}
