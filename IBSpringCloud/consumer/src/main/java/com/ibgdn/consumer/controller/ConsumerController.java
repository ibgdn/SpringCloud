package com.ibgdn.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 服务消费接口
 */
@RestController
public class ConsumerController {
    @Autowired
    DiscoveryClient discoveryClient;

    int count;

    /**
     * 静态地址调用服务提供者的接口
     *
     * @return String 服务提供者返回数据
     */
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

    /**
     * 动态获取并调用服务提供者的接口
     *
     * @return String 服务提供者返回数据
     */
    @GetMapping("/dynamic/consumer")
    public String dynamicConsumer() {
        // 获取服务提供者列表
        List<ServiceInstance> providerList = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = providerList.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        StringBuilder stringBuilder = new StringBuilder();
        // 拼接调用地址及接口
        stringBuilder.append("http://").append(host).append(":").append(port).append("/provider");
        HttpURLConnection connection;
        try {
            URL url = new URL(stringBuilder.toString());
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
        return "Consumer Controller Dynamic Error.";
    }

    /**
     * 动态均衡获取并调用服务提供者的接口
     *
     * @return String 服务提供者返回数据
     */
    @GetMapping("/dynamic/balance/consumer")
    public String dynamicBalanceConsumer() {
        // 获取服务提供者列表。获取一次即可，需要做缓存操作。
        List<ServiceInstance> providerList = discoveryClient.getInstances("provider");
        // 通过取余获取线性负载均衡。
        ServiceInstance serviceInstance = providerList.get((count++) % providerList.size());
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        StringBuilder stringBuilder = new StringBuilder();
        // 拼接调用地址及接口
        stringBuilder.append("http://").append(host).append(":").append(port).append("/provider");
        HttpURLConnection connection;
        try {
            String spec = stringBuilder.toString();
            System.out.println("Class: " + this.getClass().getName()
                    + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + ", url: " + spec);
            URL url = new URL(spec);
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
        return "Consumer Controller Dynamic Error.";
    }
}
