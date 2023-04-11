package com.techelevator.dao;

import com.techelevator.model.Location;
import com.techelevator.model.MapquestLocation;
import com.techelevator.model.MapquestResponse;
import com.techelevator.model.PotholeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
@Component
public class MapquestLocationDao implements LocationDao{

   private String authKey = "0bemTWE1QE9DpoE2o86l1RsmHTSAtPdH";
   private String API_BASE_URL = "https://www.mapquestapi.com/geocoding/v1/reverse?key="
           + authKey + "&location=";
   private final RestTemplate restTemplate = new RestTemplate();

   public Location transformToStreetAddress(PotholeDto pothole) {
      Location location = pothole.getLocation();
      String url = API_BASE_URL + location.getLat() + "," + location.getLng();
      MapquestResponse response;
      try {
         response = restTemplate.getForObject(url, MapquestResponse.class);
         MapquestLocation mapquestLocation = response.getResults().get(0).getLocations().get(0);
         location.setStreet(mapquestLocation.getStreet());
         location.setCity(mapquestLocation.getAdminArea5());
         location.setState(mapquestLocation.getAdminArea3());
         location.setPostalCode(mapquestLocation.getPostalCode());
      } catch (RestClientResponseException e) {
         System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
      } catch (ResourceAccessException e) {
         System.out.println(e.getMessage());
      }
      return location;
   }


}
