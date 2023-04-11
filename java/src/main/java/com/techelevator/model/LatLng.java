package com.techelevator.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class LatLng {

   private Double lat;
   private Double lng;
   private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

   public Double getLat() {
      return lat;
   }

   public void setLat(Double lat) {
      this.lat = lat;
   }

   public Double getLng() {
      return lng;
   }

   public void setLng(Double lng) {
      this.lng = lng;
   }

   public Map<String, Object> getAdditionalProperties() {
      return this.additionalProperties;
   }

   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

}
