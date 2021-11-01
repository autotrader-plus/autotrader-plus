package com.example.responseformatting;

/** This class helps provide the formatting for the response body of the HTTP request to the "/senso" endpoint. **/
public class HttpResponseMain {

    private static long id;
    private static String content;

    public HttpResponseMain(long id, String content) {
        HttpResponseMain.id = id;
        HttpResponseMain.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}