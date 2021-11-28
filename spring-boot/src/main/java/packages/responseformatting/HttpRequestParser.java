package packages.responseformatting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;


/**This class helps parse the http request body and returns the request in hashmap format**/
public class HttpRequestParser {
    /**
     * Parse the request body
     * @param reqBody - a string representation of the request json
     * @return a hashmap representation of the request json
     * @throws JsonProcessingException - error thrown when json cannot be processed
     */
    public HashMap<String, String> parseRequestBody(String reqBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> userInfoHash = objectMapper.readValue(reqBody, HashMap.class);

        return userInfoHash;
    }
}
