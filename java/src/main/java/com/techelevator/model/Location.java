package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Location {

   private int locationId;
   private String street;
   private String city;
   private String state;
   private String postalCode;
   private double lat;
   private double lng;

   public boolean isValid() {
      if(lat != 0.0 & lng != 0.0) {
         return true;
      } else if(street != null & city != null & state != null & postalCode != null) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "Location{" +
              "locationId=" + locationId +
              ", street='" + street + '\'' +
              ", city='" + city + '\'' +
              ", state='" + state + '\'' +
              ", postalCode='" + postalCode + '\'' +
              ", lat=" + lat +
              ", lng=" + lng +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Location location = (Location) o;
      return locationId == location.locationId && Double.compare(location.lat, lat) == 0 && Double.compare(location.lng, lng) == 0;
   }

   @Override
   public int hashCode() {
      return Objects.hash(locationId, lat, lng);
   }

   public int getLocationId() {
      return locationId;
   }

   public void setLocationId(int location_id) {
      this.locationId = location_id;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
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
