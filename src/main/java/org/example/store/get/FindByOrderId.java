package org.example.store.get;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FindByOrderId {
    public static void main(String[] args) {
        String urlAdress = "https://petstore.swagger.io/v2/store/order/1";
        HttpsURLConnection connection = null;
        URL url = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try{
            url = new URL(urlAdress);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(500);
            connection.setReadTimeout(1000);
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null)
                    System.out.println(line);
                System.out.printf("OK %s", connection.getResponseCode());
            }
            else {
                System.out.printf("Fail %s", connection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStreamReader.close();
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
