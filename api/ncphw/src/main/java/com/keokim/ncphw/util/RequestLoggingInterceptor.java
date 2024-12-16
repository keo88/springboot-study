package com.keokim.ncphw.util;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.println("Request URI: " + request.getURI());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request Headers: " + request.getHeaders());
        System.out.println("Request Body: " + new String(body, StandardCharsets.UTF_8));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Status Text: " + response.getStatusText());
        System.out.println("Response Headers: " + response.getHeaders());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
        String responseBody = bufferedReader.lines().collect(Collectors.joining("\n"));
        System.out.println("Response Body: " + responseBody);
    }
}

