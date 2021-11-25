package packages.returninfo;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class SignInEndpointHandlerTest {
    HashMap<String, String> mapping;
    Gson gsonObj;
    SignInEndpointHandler test;

    @BeforeEach
    void setUp(){
        mapping = new HashMap<>();
        gsonObj = new Gson();
        test = new SignInEndpointHandler();
    }

    @Test
    void httpResponseSensoSuccessCredentials() {
        mapping.put("username", "Lord Pikachu");
        mapping.put("password", "GottaCatchThemAll");

        String request = gsonObj.toJson(mapping);

        String response = test.httpResponseSignIn( request );

        assert(Objects.equals( response, "true"));
    }
    
    @Test
    void httpResponseSensoFailedCredentials() {
        mapping.put("username", "DNE");
        mapping.put("password", "DNE");

        String request = gsonObj.toJson(mapping);

        String response = test.httpResponseSignIn( request );

        assert(Objects.equals( response, ""));
    }

}