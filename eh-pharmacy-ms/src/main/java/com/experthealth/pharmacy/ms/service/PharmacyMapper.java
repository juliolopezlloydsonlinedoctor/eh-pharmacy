package com.experthealth.pharmacy.ms.service;

import com.experthealth.pharmacy.ms.model.Location;
import com.experthealth.pharmacy.ms.model.OpenClose;
import com.experthealth.pharmacy.ms.model.Address;
import com.experthealth.pharmacy.ms.model.Pharmacy;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PharmacyMapper {

    private final static Logger LOGGER = LoggerFactory.getLogger(PharmacyMapper.class);
    private static final String UNABLE_TO_MAP_DATA = "Unable to Map data to Store Details POJO";

    public Pharmacy mapToStoreDetails(JSONObject storeDetailsJson) {
        try {
            return mapJsonToStoresDetails(storeDetailsJson);
        } catch (JSONException e) {
            LOGGER.warn(UNABLE_TO_MAP_DATA);
            return new Pharmacy();
        }
    }

    private Pharmacy mapJsonToStoresDetails(JSONObject storeDetailsJson) throws JSONException {
        JSONObject storeDetailsInfo = storeDetailsJson.getJSONObject("pharmacy");
        Pharmacy storeDetails = new Pharmacy();
        storeDetails.setName(storeDetailsInfo.getString("name"));
        storeDetails.setClientID(storeDetailsInfo.getInt("clientId"));
        storeDetails.setActive(storeDetailsInfo.getBoolean(("active")));
        Address address = createAddressForStoreDetails(storeDetailsInfo);
        storeDetails.setAddress(address);
        storeDetails.setPhone(storeDetailsInfo.getString("telephone"));
        Map<String, OpenClose> hours = createOpenCloseHoursCollection(storeDetailsInfo);
        storeDetails.setHours(hours);
        Map<String, Boolean> servicesList = createServicesListForStoreDetails(storeDetailsInfo);
        storeDetails.setServices(servicesList);
        Location location = createLocationForStoreDetails(storeDetailsInfo);
        storeDetails.setLocation(location);
        return storeDetails;
    }

    private Map<String, Boolean> createServicesListForStoreDetails(JSONObject storeDetailsInfo) throws JSONException {
        Map<String, Boolean> servicesMap = new HashMap<String, Boolean>();
        //Add all services you will like to map and filter future storeLocators by.
        servicesMap.put("diabetesService", storeDetailsInfo.getBoolean("diabetesService"));
        servicesMap.put("bptService", storeDetailsInfo.getBoolean("bptService"));
        servicesMap.put("prescriptionMotService", storeDetailsInfo.getBoolean("prescriptionMotService"));
        servicesMap.put("asthmaService", storeDetailsInfo.getBoolean("asthmaService"));
        servicesMap.put("stopSmokingService", storeDetailsInfo.getBoolean("stopSmokingService"));
        servicesMap.put("hhcService", storeDetailsInfo.getBoolean("hhcService"));
        servicesMap.put("beautyFragrance", storeDetailsInfo.getBoolean("beautyFragrance"));
        servicesMap.put("digitalPhotoKiosk", storeDetailsInfo.getBoolean("digitalPhotoKiosk"));
        servicesMap.put("antiMalarial", storeDetailsInfo.getBoolean("antiMalarial"));
        servicesMap.put("travelVaccine", storeDetailsInfo.getBoolean("travelVaccine"));
        servicesMap.put("travelVaccineExtra", storeDetailsInfo.getBoolean("travelVaccineExtra"));
        servicesMap.put("hpvVaccine", storeDetailsInfo.getBoolean("hpvVaccine"));
        servicesMap.put("hairLoss", storeDetailsInfo.getBoolean("hairLoss"));
        servicesMap.put("ed", storeDetailsInfo.getBoolean("ed"));
        servicesMap.put("chlamTest", storeDetailsInfo.getBoolean("chlamTest"));
        servicesMap.put("chlamGonn", storeDetailsInfo.getBoolean("chlamGonn"));
        servicesMap.put("chlamTreat1d", storeDetailsInfo.getBoolean("chlamTreat1d"));
        servicesMap.put("chlamTreat7d", storeDetailsInfo.getBoolean("chlamTreat7d"));
        servicesMap.put("contraPill", storeDetailsInfo.getBoolean("contraPill"));
        servicesMap.put("ehcSingle", storeDetailsInfo.getBoolean("ehcSingle"));
        servicesMap.put("ehcDouble", storeDetailsInfo.getBoolean("ehcDouble"));
        servicesMap.put("ehcTriple", storeDetailsInfo.getBoolean("ehcTriple"));
        servicesMap.put("fluVaccine", storeDetailsInfo.getBoolean("fluVaccine"));
        servicesMap.put("travelPack", storeDetailsInfo.getBoolean("travelPack"));

        return servicesMap;
    }

    private Location createLocationForStoreDetails(JSONObject storeDetailsInfo) throws JSONException {
        Location location = new Location();
        location.setLat(Double.parseDouble(storeDetailsInfo.getString("geoLat")));
        location.setLng(Double.parseDouble(storeDetailsInfo.getString("geoLon")));
        return location;
    }

    private Address createAddressForStoreDetails(JSONObject storeDetailsInfo) throws JSONException {
        Address address = new Address();
        address.setStreet(storeDetailsInfo.getString("street"));
        address.setLocality(storeDetailsInfo.getString("locality"));
        address.setTown(storeDetailsInfo.getString("town"));
        address.setCounty(storeDetailsInfo.getString("county"));
        address.setCountry(storeDetailsInfo.getString("country"));
        address.setPostcode(storeDetailsInfo.getString("pc"));
        return address;
    }

    /**
     * Creates a map that contains the opening hours for each day for a given storeDetails.
     *
     * @param storeDetailsJson Details of one store.
     * @return Map with the hours.
     */
    protected Map<String, OpenClose> createOpenCloseHoursCollection(JSONObject storeDetailsJson) throws JSONException {
        Map<String, OpenClose> hours = new HashMap<String, OpenClose>();
        OpenClose openClose = new OpenClose();
        openClose.setOpen(storeDetailsJson.getString("openMonday"));
        openClose.setClose(storeDetailsJson.getString("closeMonday"));
        hours.put("monday", openClose);
        OpenClose openClose1 = new OpenClose();
        openClose1.setOpen(storeDetailsJson.getString("openTuesday"));
        openClose1.setClose(storeDetailsJson.getString("closeTuesday"));
        hours.put("tuesday", openClose1);
        OpenClose openClose2 = new OpenClose();
        openClose2.setOpen(storeDetailsJson.getString("openWednesday"));
        openClose2.setClose(storeDetailsJson.getString("closeWednesday"));
        hours.put("wednesday", openClose2);
        OpenClose openClose3 = new OpenClose();
        openClose3.setOpen(storeDetailsJson.getString("openThursday"));
        openClose3.setClose(storeDetailsJson.getString("closeThursday"));
        hours.put("thursday", openClose3);
        OpenClose openClose4 = new OpenClose();
        openClose4.setOpen(storeDetailsJson.getString("openFriday"));
        openClose4.setClose(storeDetailsJson.getString("closeFriday"));
        hours.put("friday", openClose4);
        OpenClose openClose5 = new OpenClose();
        openClose5.setOpen(storeDetailsJson.getString("openSaturday"));
        openClose5.setClose(storeDetailsJson.getString("closeSaturday"));
        hours.put("saturday", openClose5);
        OpenClose openClose6 = new OpenClose();
        openClose6.setOpen(storeDetailsJson.getString("openSunday"));
        openClose6.setClose(storeDetailsJson.getString("closeSunday"));
        hours.put("sunday", openClose6);
        return hours;
    }
}
