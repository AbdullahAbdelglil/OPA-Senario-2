package com.example.OPA_senario2;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpaService {
    private static HttpClient client = HttpClient.newHttpClient();
    private static String url = "http://localhost:2020/v1/data/authz/allow";

    public boolean isAllowed(String role, String path) {
        HttpRequest request = buildRequest(role, path);
        HttpResponse<String> response = sendTheRequest(request);

        assert response != null;
        JSONObject result = new JSONObject(response.body());

        return result.getBoolean("result");
    }

    private static HttpRequest buildRequest(String role, String path) {
        String jsonBody = buildRequestBody(role, path);
        System.out.println(jsonBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return request;
    }

    private static String buildRequestBody(String role, String path) {
        return "{\"input\": {\"role\":\"" + role + "\", \"path\":\"" + path + "\"}}";
    }

    private static HttpResponse<String> sendTheRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\n--------------------------\n");
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            System.out.println("\n--------------------------\n");

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}