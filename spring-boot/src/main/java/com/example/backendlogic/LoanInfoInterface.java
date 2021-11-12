package com.example.backendlogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LoanInfoInterface {
    HashMap<String, Object> calculateLoans(HashMap<String, String> user, ArrayList<HashMap<String, String>> carlist) throws IOException, InterruptedException;
}
