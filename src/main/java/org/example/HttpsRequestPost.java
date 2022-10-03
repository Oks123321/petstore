package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpsRequestPost {
    public static void main(String[] args) {
        String urlAdress = "https://petstore.swagger.io/v2/pet";
        HttpsURLConnection httpsURLConnection = null;
        URL url = null;
        OutputStream outputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            Map<String, String> postargs = new HashMap<>();
            postargs.put("user", "Bob");
            postargs.put("password", "123");


            byte[] out = postargs.toString().getBytes();


            url = new URL(urlAdress);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);

            httpsURLConnection.addRequestProperty("User-Agent", "Chrome/106.0.5249.91");
            httpsURLConnection.addRequestProperty("Content-type", "application-x-www-form-urlencoded");

            httpsURLConnection.setConnectTimeout(500);
            httpsURLConnection.setReadTimeout(500);
            httpsURLConnection.connect();



            try{
                outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(out);
            }catch (Exception e){
                System.err.print(e.getMessage());
            }
            if(httpsURLConnection.HTTP_OK == httpsURLConnection.getResponseCode()){
                inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while((line = bufferedReader.readLine()) != null){
                 stringBuilder.append(line);
                }

            }
            System.out.println(stringBuilder);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        }
    }
