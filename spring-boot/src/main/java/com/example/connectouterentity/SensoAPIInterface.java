package com.example.connectouterentity;

import java.io.IOException;
import java.util.HashMap;

public interface SensoAPIInterface {
    HashMap<Object, Object> pingSensoAPI(HashMap<String, String> senso_input) throws IOException, InterruptedException;
}
