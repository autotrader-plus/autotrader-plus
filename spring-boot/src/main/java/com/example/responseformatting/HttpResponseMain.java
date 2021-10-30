package com.example.responseformatting;

import org.json.JSONObject;

/** This class helps provide the formatting for the response body of the HTTP request to the "/senso" endpoint. **/
public class HttpResponseMain {

    private static long id;
    private static JSONObject content;

    public HttpResponseMain(long id, JSONObject content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public JSONObject getContent() {
        return content;
    }
}