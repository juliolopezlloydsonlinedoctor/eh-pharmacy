package com.experthealth.pharmacy.ms.service;

import com.experthealth.client.EHRestClientException;
import com.experthealth.client.RestTemplateClient;

import com.experthealth.pharmacy.ms.exception.MalformedJsonException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class PharmacyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyDAO.class);

    private static final String ERROR_CREATING_JSON_FROM_SERVICE = "Error creating JSONObject from service response.";

    @Autowired
    private RestTemplateClient salRestService;

    @Value("${api.sal.pharmacies}")
    private String salPharmacyEndPoint;

    public JSONObject getStoresWithRestService() throws MalformedJsonException {
        LOGGER.info("Attempting to Get the Store from Restful API");
        String exceptionMessage;
        try {
            String response = getRestServiceResponse();
            validateResponse(response);
            LOGGER.info("Stores fetched From Rest call Successfully");
            return new JSONObject(response);
        } catch (EHRestClientException e) {
            exceptionMessage = "Error in restTemplate: " + e.getLocalizedMessage();
            LOGGER.error(exceptionMessage);
            throw new MalformedJsonException(exceptionMessage);
        } catch (JSONException e) {
            exceptionMessage = ERROR_CREATING_JSON_FROM_SERVICE + e.getLocalizedMessage();
            LOGGER.error(ERROR_CREATING_JSON_FROM_SERVICE);
            throw new MalformedJsonException(exceptionMessage);
        }
    }

    private void validateResponse(String response) throws MalformedJsonException {
        if(!response.contains("pharmacies")) {
            if (response.contains("Maintenance mode")){
                throw  new MalformedJsonException("MOHC SAL in maintenance mode");
            }
            throw new MalformedJsonException("JSON from service does not contains expected information");
        }
    }

    protected String getRestServiceResponse() throws EHRestClientException {
        String url = salPharmacyEndPoint + "?" + "site=lp";
        ResponseEntity<String> responseEntity = salRestService.makeRequest(url, Collections.<String,String>emptyMap(), HttpMethod.GET, getHttpEntity());
        return responseEntity.getBody();
    }

    public HttpEntity<Object> getHttpEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json;charset=UTF-8");
        requestHeaders.add("Accept", "application/vnd.sal.v2+json");
        HttpEntity<Object> httpEntity = new HttpEntity(null,requestHeaders);
        return httpEntity;
    }

}
