package com.techelevator.service;

import com.techelevator.dao.PotholeDao;
import com.techelevator.model.PotholeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PotholeServiceImp implements PotholeService{

   private PotholeDao dao;

   public PotholeServiceImp(PotholeDao potholeDao) {
      this.dao = potholeDao;
   }
   @Override
   public List<PotholeDto> getAllPotholes() {
      return dao.findAll();
   }

   /**
    * Creates a new pothole with the given information
    * @param pothole The pothole to be created
    * @return The created pothole
    */
   @Override
   public PotholeDto createPothole(PotholeDto pothole) {
      PotholeDto createdPothole = null;
      if(locationIsValid(pothole)) {
         createdPothole = dao.createPothole(pothole);
      }
      return createdPothole;
   }

   /**
    * Makes an update to an existing pothole based on the passed in pothole
    * @param potholeToUpdate The updated pothole values
    * @return The pothole after updating
    */
   @Override
   public PotholeDto updatePothole(PotholeDto potholeToUpdate) {
      PotholeDto potholeAfterUpdate = null;
      if(potholeExists(potholeToUpdate)) {
         potholeAfterUpdate = dao.updatePothole(potholeToUpdate);
      }
      return potholeAfterUpdate;
   }

   /**
    * Delete the given pothole
    * @param pothole pothole to be deleted
    */
   @Override
   public void deletePothole(PotholeDto pothole) {
      if(potholeExists(pothole)) {
         dao.deletePothole(pothole.getPotholeId());
      }
   }

   private boolean locationIsValid(PotholeDto pothole) {
      return true;
   }

   private boolean potholeExists(PotholeDto potholeDto) {
      List<PotholeDto> potholes = dao.findAll();
      for(PotholeDto ph: potholes) {
         if(ph.getPotholeId() == potholeDto.getPotholeId()) {
            return true;
         }
      }
      return false;
   }
}
