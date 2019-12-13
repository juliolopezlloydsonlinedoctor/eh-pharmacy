package com.experthealth.pharmacy.ms.model;

import java.io.Serializable;
import java.util.Map;

public class Pharmacy implements Serializable {

    /**
     * Name of the store.
     */
    private String name;
    /**
     * Unique ClientId of the store.
     */
    private int clientID;
    /**
     * The address of the store.
     */
    private Address address;
    /**
     * The store phone number.
     */
    private String phone;
    /**
     * Opening hours per day.
     */
    private Map<String, OpenClose> hours;
    /**
     * Services available in the store.
     */
    private Map<String, Boolean> services;
    /**
     * Location of the store.
     */
    private Location location;
    /**
     * stores active flag
     */
    private boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHours(Map hours) {
        this.hours = hours;
    }

    public void setServices(Map<String, Boolean> services) {
        this.services = services;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<String, Boolean> getServices() {
        return services;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append(clientID);
        sb.append(']');
        return sb.toString();
    }
}