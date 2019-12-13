package com.experthealth.pharmacy.ms.service;

import com.experthealth.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;

@Configuration
public class RestTemplateConfig {

    @Value("${api.sal.password}")
    private String apiSalPassword;

    @Value("${api.sal.env}")
    private String apiSalEnvironment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return ThreadPoolTaskExecutorFactory.getNewThreadPoolTaskExecutor();
    }
    
    @Bean
    public SecurityPolicyUtils securityPolicyUtils() {
        return new SecurityPolicyUtils();
    }

    @Bean
    public SecurityPolicy securityPolicy() {
        String securePath = securityPolicyUtils().generateSecurePath();
        return new SecurityPolicy(securePath, apiSalPassword, apiSalEnvironment);
    }

    @Bean
    public KeyStore keyStore() throws EHRestClientException {
        SecurityPolicy securityPolicy = securityPolicy();
        return EHKeyStore.getKeyStore(securityPolicy);
    }

    @Bean
    public RestTemplateClient salRestClient() throws EHRestClientException {
        RestTemplate restTemplate = restTemplate();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = threadPoolTaskExecutor();
        SecurityPolicy securityPolicy = securityPolicy();
        KeyStore keyStore = keyStore();
        
        return new EHRestClient(restTemplate, threadPoolTaskExecutor, securityPolicy, keyStore);
    }
}
