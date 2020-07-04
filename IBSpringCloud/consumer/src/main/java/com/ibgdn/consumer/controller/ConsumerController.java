package com.ibgdn.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 服务消费接口
 */
@RestController
public class ConsumerController {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

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


    /**
     * 通过 RestTemplate 动态均衡获取并调用服务提供者的接口
     *
     * @return String 服务提供者返回数据
     */
    @GetMapping("/restTemplate/consumer")
    public String restTemplateConsumer() {
        // 获取服务提供者列表。获取一次即可，需要做缓存操作。
        List<ServiceInstance> providerList = discoveryClient.getInstances("provider");
        // 通过取余获取线性负载均衡。
        ServiceInstance serviceInstance = providerList.get((count++) % providerList.size());
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        StringBuilder stringBuilder = new StringBuilder();
        // 拼接调用地址及接口
        stringBuilder.append("http://").append(host).append(":").append(port).append("/provider");
        String spec = stringBuilder.toString();
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", url: " + spec);

        String restTemplateStr = restTemplate.getForObject(spec, String.class);
        return restTemplateStr;
    }

    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    RestTemplate loadBalancedRestTemplate;

    /**
     * 通过 RestTemplate 动态均衡获取并调用服务提供者的接口
     *
     * @return String 服务提供者返回数据
     */
    @GetMapping("/loadBalanced/consumer")
    public String loadBalancedRestTemplateConsumer() {
        // 第一个 provider 属于模糊调用，第二个 provider 调用 provider 的 provider 接口。
        return loadBalancedRestTemplate.getForObject("http://provider/provider", String.class);
    }

    /**
     * RestTemplate Get 测试方法
     * <p>
     * 请求接口后的输出内容如下：
     * RestTemplate getForObject: Provider Controller providerGet. Name: OGet
     * Body: Provider Controller providerGet. Name: EGet
     * HttpStatus: 200 OK
     * StatusCodeValue: 200
     * ------------ headers --------------
     * Content-Type : [text/plain;charset=UTF-8]
     * Content-Length : [43]
     * Date : [Sat, 04 Jul 2020 14:54:44 GMT]
     * Keep-Alive : [timeout=60]
     * Connection : [keep-alive]
     * ============= 三种参数请求方式 ==============
     * key={1}: Provider Controller providerGet. Name: zhang
     * map: Provider Controller providerGet. Name: san
     * url?name=张三: Provider Controller providerGet. Name: 张三
     */
    @GetMapping("/restTemplateGet")
    public void restTemplateGet() throws UnsupportedEncodingException {
        String restTemplateForObject = loadBalancedRestTemplate.getForObject("http://provider/providerGet?name={1}", String.class, "OGet");
        System.out.println("RestTemplate getForObject: " + restTemplateForObject);

        ResponseEntity<String> responseEntity = loadBalancedRestTemplate.getForEntity("http://provider/providerGet?name={1}", String.class, "EGet");
        System.out.println("Body: " + responseEntity.getBody());
        System.out.println("HttpStatus: " + responseEntity.getStatusCode());
        System.out.println("StatusCodeValue: " + responseEntity.getStatusCodeValue());
        System.out.println("------------ headers --------------");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            System.out.println(key + " : " + headers.get(key));
        }

        System.out.println("============= 三种参数请求方式 ==============");
        String s1 = loadBalancedRestTemplate.getForObject("http://provider/providerGet?name={1}", String.class, "zhang");
        System.out.println("key={1}: " + s1);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "san");
        s1 = loadBalancedRestTemplate.getForObject("http://provider/providerGet?name={name}", String.class, map);
        System.out.println("map: " + s1);
        String url = "http://provider/providerGet?name=" + URLEncoder.encode("张三", "UTF-8");
        URI uri = URI.create(url);
        s1 = loadBalancedRestTemplate.getForObject(uri, String.class);
        System.out.println("url?name=张三: " + s1);
    }
}
