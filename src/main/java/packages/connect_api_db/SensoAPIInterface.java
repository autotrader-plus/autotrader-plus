package packages.connect_api_db;

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

public interface SensoAPIInterface {
    /**
     * A default method to ping senso api (this is to avoid duplicate codes in multiple classes)
     * @param senso_input - the input mapping for senso api call
     * @return a hashmap representation of return info from senso api
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    default HttpRequest buildPostRequest(String inputJson, String postEndpoint){
        // Build and send a POST request to senso endpoint
        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndpoint))
                .header("Content-Type", "application/json")
                .header("x-api-key", System.getenv("SENSO_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        return request;
    }

    /**
     * A default method to get senso api response (this is to avoid duplicate codes in multiple classes)
     * @param request the http request
     * @return a hashmap containing the senso api response
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    default HashMap<String, Object> getPostResponse(HttpRequest request) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();


        // Get response from senso api
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<Object, Object>>(){}.getType();
        return gson.fromJson(response.body(), mapType);
    }

    /**
     * A method that should be called by other classes when they need to ping senso api
     * @param senso_input - the necessary input in hashmap format for senso api call
     * @return a hashmap containing return information from senso api
     * @throws IOException - exception thrown if the input and output are missing or failed to comply with the data type
     * @throws InterruptedException - exception thrown when api call is interrupted or failed
     */
    HashMap<String, Object> pingSensoAPI(HashMap<String, String> senso_input) throws IOException, InterruptedException;
}
