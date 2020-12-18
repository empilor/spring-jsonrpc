package com.vzelenin.edu.spring.jsonrpc.config;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import com.vzelenin.edu.spring.jsonrpc.client.ClientAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${jsonrpc.endpoint}")
    private String endpoint;
    @Autowired
    private Environment env;

    public String getProperty(String key) {
        return env.getProperty(key);
    }

    @Bean
    public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
        return new BeanNameUrlHandlerMapping();
    }

    @Bean
    public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        //You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<>();
        try {
            url = new URL(endpoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JsonRpcHttpClient(url, map);
    }

    @Bean
    public ClientAPI exampleClientAPI(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), ClientAPI.class, jsonRpcHttpClient);
    }
}
