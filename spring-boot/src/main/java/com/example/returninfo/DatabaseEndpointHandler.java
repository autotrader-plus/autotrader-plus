package com.example.returninfo;

import java.sql.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseformatting.*;
import com.example.connectouterentity.*;

@RestController
@CrossOrigin(origins ="*")
public class ReturnDatabaseInfo {

    private final AtomicLong counter = new AtomicLong();

    public String getContent() throws SQLException {
        /**
         * This will return the information queried by the ReturnDatabaseInfo class
         */
        ConnectAutoTraderDB db_object = new ConnectAutoTraderDB();
        return db_object.returnCarDetailsString(0);

    }

    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @GetMapping("/database")
    public HttpResponseDatabase httpResponseDatabase(@RequestParam(required = false, defaultValue = "") String name) throws SQLException{
        System.out.println("==== Connecting to Autotrader Database ====");
        HttpResponseDatabase db_response = new HttpResponseDatabase(counter.incrementAndGet(), getContent());
        return db_response;
    }
}