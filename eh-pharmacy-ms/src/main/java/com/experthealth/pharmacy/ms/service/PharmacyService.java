package com.experthealth.pharmacy.ms.service;

import com.experthealth.pharmacy.ms.exception.MalformedJsonException;
import com.experthealth.pharmacy.ms.model.Pharmacy;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PharmacyService {

    private static Logger log = LoggerFactory.getLogger(PharmacyService.class);

    @Autowired
    private PharmacyDAO pharmacyDAO;

    @Autowired
    private PharmacyMapper pharmacyMapper;

    @Cacheable("pharmacies")
    public List<Pharmacy> getStores() throws MalformedJsonException {
        return fetchStores();
    }

    @CachePut("pharmacies")
    public List<Pharmacy> getStoresAndUpdateCache() throws MalformedJsonException {
        return fetchStores();
    }


    public List<Pharmacy> fetchStores() throws MalformedJsonException {

        JSONArray pharmaciesArray = fetchPharmaciesArray();
        return convertPharmaciesToStoreDetails(pharmaciesArray);
    }

    private JSONArray fetchPharmaciesArray() throws MalformedJsonException {

        JSONObject stores = pharmacyDAO.getStoresWithRestService();
        try {
            return stores.getJSONArray("pharmacies");
        } catch (JSONException ex) {
            String message = "Unable to retrieve \"pharmacies\" element from DAO response";
            log.error(message, ex);
            throw new MalformedJsonException(message, ex);
        }
    }

    private List<Pharmacy> convertPharmaciesToStoreDetails(JSONArray pharmaciesArray) throws MalformedJsonException {

        List<Pharmacy> result = new ArrayList<>(pharmaciesArray.length());
        for (int i = 0; i < pharmaciesArray.length(); i++) {
            Pharmacy pharmacy = pharmacyMapper.mapToStoreDetails(getPharmacyAtIndex(i, pharmaciesArray));

            if (isStoreValid(pharmacy)) {
                result.add(pharmacy);
            }
        }
        return result;
    }

    private JSONObject getPharmacyAtIndex(int index, JSONArray pharmaciesArray) throws MalformedJsonException {
        try {
            return pharmaciesArray.getJSONObject(index);
        } catch (JSONException ex) {
            String message = "Unable to retrieve pharmacy at index " + index;
            log.error(message, ex);
            throw new MalformedJsonException(message, ex);
        }
    }

    private boolean isStoreValid(Pharmacy store) {
        return store.getName() != null && store.isActive();
    }

}
