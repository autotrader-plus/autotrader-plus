package com.example.responseformatting;

import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicLong;

/** This class helps provide the formatting for the response body of the HTTP request to the "/senso" endpoint. **/
public class HttpResponseMain {

    private static AtomicLong id;
    private static JSONObject content;

    public HttpResponseMain(AtomicLong id, JSONObject content) {
        HttpResponseMain.id = id;
        HttpResponseMain.content = content;
    }

    public AtomicLong getId() {
        return id;
    }

    public JSONObject getContent() {
        return content;
    }
}