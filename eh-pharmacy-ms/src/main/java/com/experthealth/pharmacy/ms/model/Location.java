package com.experthealth.pharmacy.ms.model;

import java.io.Serializable;

/**
 * Model representing a Store Details Location.
 *
 * Created by Moses & Julio & Reena & Ronan on 06/02/2015.
 */
public class Location implements Serializable {

	 private double lat;
	 private double lng;
	 private Double distanceToPoint;
	 

	 public Double getDistanceToPoint() {
		return distanceToPoint;
	}

        public void setDistanceToPoint(Double distanceToPoint) {
		this.distanceToPoint = distanceToPoint;
	}

	public double getLat() {
	  return lat;
	 }

	 public void setLat(double lat) {
	  this.lat = lat;
	 }

	 public double getLng() {
	  return lng;
	 }

	 public void setLng(double lng) {
	  this.lng = lng;
	 }
}
