package com.experthealth.pharmacy.ms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class SecurityPolicyUtils {

    @Value("${api.sal.env}")
    private String salEnvironment;
    
    @Value("${env.production}")
    private String envProduction;
    
    @Value("${env.preproduction}")
    private String envPreproduction;
    
    @Value("${env.green}")
    private String envGreen;
    
    @Value("${env.black}")
    private String envBlack;
    
    public String generateSecurePath() {
        switch(salEnvironment){
        case "MOHC_GREEN":
            return envGreen;
        case "MOHC_BLACK":
            return envBlack;
        case "MOHC_PREPRODUCTION":
            return envPreproduction;
        case "MOHC_PRODUCTION":
            return envProduction;
        default:
            return envGreen;
        }
    }
}
