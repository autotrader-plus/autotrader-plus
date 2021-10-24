package com.example.responseformatting;

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