package com.example.responseformatting;

/** This class helps provide the formatting for the response body of the HTTP request to the
 * "/traderauto-plus" endpoint. **/

// This class may not be necessary (we need to look through where this class is used and decide whether it is safe
// to delete this class

public class HttpResponseMain {

    private static long id;
    private static String content;

    /**
     * Constructor for the class
     * @param id - the id of the request
     * @param content - the response body for the requet
     */
    public HttpResponseMain(long id, String content) {
        HttpResponseMain.id = id;
        HttpResponseMain.content = content;
    }

    // Getter
    public long getId() {
        return id;
    }

    // Getter
    public String getContent() {
        return content;
    }
}