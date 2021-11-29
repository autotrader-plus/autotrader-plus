package packages.responseformatting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

class HttpRequestParserTest {

    @Test
    void parseRequestBody() throws JsonProcessingException {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("username", "Paul");
        mapping.put("password", "123");

        Gson gsonObj = new Gson();
        String request = gsonObj.toJson(mapping);

        HttpRequestParser parser = new HttpRequestParser();
        HashMap<String, String> result = parser.parseRequestBody(request);

        assert Objects.equals(result, mapping);

    }
}