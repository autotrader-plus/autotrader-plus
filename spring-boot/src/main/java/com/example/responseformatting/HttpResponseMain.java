package com.example.responseformatting;

import java.util.concurrent.atomic.AtomicLong;

/** This class helps provide the formatting for the response body of the HTTP request to the "/senso" endpoint. **/
public class HttpResponseMain {

    private static AtomicLong id;
    private static String content;

    public HttpResponseMain(AtomicLong id, String content) {
        HttpResponseMain.id = id;
        HttpResponseMain.content = content;
    }

    public AtomicLong getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}