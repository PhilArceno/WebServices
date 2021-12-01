package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerApi {

    private String baseUrl;

    public CustomerApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Customer> getAllCustomers() throws ApiErrorException {
        try {
            URL url = new URL(baseUrl + "/customers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }

            StringBuilder wholeText = new StringBuilder();
            try (Scanner scanner = new Scanner(url.openStream())) {
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    wholeText.append(scanner.nextLine());
                }
            }

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONArray topArray = (JSONArray) parse.parse(wholeText.toString());

            ArrayList<Customer> list = new ArrayList<>();

            for (Object o : topArray) {
                JSONObject customerObj = (JSONObject) o;
                long id = (Long) customerObj.get("id");
                String email = (String) customerObj.get("email");
                String name = (String) customerObj.get("name");
                long active = (Long) customerObj.get("active");
                // System.out.printf("id=%d, email=%s, name=%s, active=%d\n", id, email, name, active);
                list.add(new Customer(id, email, name, active));
            }
            return list;
        }
        catch (IOException | RuntimeException | ParseException ex) {
            ex.printStackTrace();
            // System.out.println("Error: " + ex.getMessage());
            throw new ApiErrorException(ex);
        }

    }

    public Customer getCustomerById(long id) throws ApiErrorException {
        try {
            URL url = new URL(baseUrl + "/customers/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }

            StringBuilder wholeText = new StringBuilder();
            try (Scanner scanner = new Scanner(url.openStream())) {
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    wholeText.append(scanner.nextLine());
                }
            }
            JSONParser parse = new JSONParser();
            JSONObject o = (JSONObject) parse.parse(wholeText.toString());

            return new Customer(
                    (Long) o.get("id"),
                    (String) o.get("email"),
                    (String) o.get("name"),
                    (Long) o.get("active")
            );
        }
        catch (IOException | RuntimeException | ParseException ex) {
            ex.printStackTrace();
            // System.out.println("Error: " + ex.getMessage());
            throw new ApiErrorException(ex);
        }
    }

    // returns id of the newly created record
    public long addCustomer(Customer customer)  throws ApiErrorException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(baseUrl + "/customers");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //WARNING: do NOT put utf-8 here or nodejs will reject it
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Ensure the Connection Will Be Used to Send Content
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            JSONObject customerObj = new JSONObject();
            customerObj.put("email", customer.email);
            customerObj.put("name", customer.name);
            customerObj.put("active", customer.active);
            String jsonStr = customerObj.toJSONString();

            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonStr.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //read id sent back to body
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                //Getting the response code
                int responseCode = conn.getResponseCode();
                if (responseCode != 201) {
                    throw new RuntimeException("HttpResponseCode: " + responseCode);
                }
                return Integer.parseInt(response.toString());
            }
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
            // System.out.println("Error: " + ex.getMessage());
            throw new ApiErrorException(ex);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    // customer must exist, otherwise exception is thrown
    public void updateCustomer(Customer customer)  throws ApiErrorException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(baseUrl + "/customers/" + customer.id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            //WARNING: do NOT put utf-8 here or nodejs will reject it
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Ensure the Connection Will Be Used to Send Content
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            JSONObject customerObj = new JSONObject();
            customerObj.put("email", customer.email);
            customerObj.put("name", customer.name);
            customerObj.put("active", customer.active);
            String jsonStr = customerObj.toJSONString();

            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonStr.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            System.out.println("Updated");
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
            // System.out.println("Error: " + ex.getMessage());
            throw new ApiErrorException(ex);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public void deleteCustomerById(long id) throws ApiErrorException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(baseUrl + "/customers/" + id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            //Ensure the Connection Will Be Used to Send Content
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
            // System.out.println("Error: " + ex.getMessage());
            throw new ApiErrorException(ex);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

}
