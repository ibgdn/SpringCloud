package com.ibgdn.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 服务消费接口
 */
@RestController
public class ConsumerController {
    @GetMapping("/static/consumer")
    public String staticConsumer() {
        HttpURLConnection connection;
        try {
            URL url = new URL("http://localhost:1120/provider");
            connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                return readLine;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Consumer Controller Error.";
    }
}
