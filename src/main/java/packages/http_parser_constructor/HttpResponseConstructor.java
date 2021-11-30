package packages.http_parser_constructor;

import com.google.gson.Gson;
import java.util.HashMap;

/** This class helps provide the formatting for the response body of the HTTP request to the
 * server. **/

public class HttpResponseConstructor {

    private HashMap<String, Object> responseContent;

    /**
     * Constructor for the class
     * @param content - the response body for the http request
     */
    public HttpResponseConstructor(HashMap<String, Object> content) {
        responseContent = content;
    }

    // Getter

    /**
     * Get the Json string representation of the HTTP response content.
     * @return Json string representation of the HTTP response content
     */
    public String getContent() {
        Gson gsonObj = new Gson();
        return gsonObj.toJson(responseContent);
    }
}