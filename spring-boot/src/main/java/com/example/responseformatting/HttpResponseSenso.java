package com.example.responseformatting;

/** This class helps provide the formatting for the response body of the HTTP request to the "/senso" endpoint. **/
public class HttpResponseSenso {

	private static long id;
	private static String content;

	public HttpResponseSenso(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}