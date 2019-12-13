package com.experthealth.pharmacy.ms.model;

import java.io.Serializable;

public class Address implements Serializable {

  private String street;
  private String locality;
  private String town;
  private String county;
  private String country;
  private String postcode;

  public void setStreet(String street) {
    this.street = street;
  }


  public void setLocality(String locality) {
    this.locality = locality;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }
}
