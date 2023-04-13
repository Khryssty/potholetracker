package com.techelevator.service;

import com.techelevator.dao.LocationDao;
import com.techelevator.dao.MapquestLocationDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Location;
import com.techelevator.model.PotholeDto;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.KeyPair;
import java.security.Principal;
import java.util.*;

@Service
public class PotholeServiceImp implements PotholeService {

   private PotholeDao dao;
   private LocationDao locationDao;
   private UserDao userDao;

   public PotholeServiceImp(PotholeDao potholeDao, LocationDao locationDao, UserDao userDao) {
      this.dao = potholeDao;
      this.locationDao = locationDao;
      this.userDao = userDao;
   }

   @Override
   public List<PotholeDto> getAllPotholes(Principal principal) {
      return dao.findAll();
   }

   @Override
   public PotholeDto getPotholeById(int id) {
      return dao.findPothole(id);
   }

   /**
    * Creates a new pothole with the given information
    *
    * @param pothole The pothole to be created
    * @return The created pothole
    */
   @Override
   public PotholeDto createPothole(PotholeDto pothole, Principal principal) {
      PotholeDto createdPothole = null;
      if (pothole.getLocation().isValid()) {
         Location fullPotholeLocation = locationDao.transformToStreetAddress(pothole);
         pothole.setLocation(fullPotholeLocation);
         createdPothole = dao.createPothole(pothole, userDao.findIdByUsername(principal.getName()));
      }
      return createdPothole;
   }

   /**
    * Makes an update to an existing pothole based on the passed in pothole
    *
    * @param potholeToUpdate The updated pothole values
    * @return The pothole after updating
    */
   @Override
   public PotholeDto updatePothole(PotholeDto potholeToUpdate, Principal principal) {
      PotholeDto potholeAfterUpdate = null;
      if (potholeExists(potholeToUpdate)) {
         PotholeDto oldPothole = dao.findPothole(potholeToUpdate.getPotholeId());
         potholeAfterUpdate = dao.updatePothole(oldPothole, potholeToUpdate, userDao.findIdByUsername(principal.getName()));
      }
      return potholeAfterUpdate;
   }

   /**
    * Delete the given pothole
    *
    * @param id to be deleted
    */
   @Override
   public void deletePothole(int id) {
//      if(potholeExists(pothole)) {
      dao.deletePothole(id);
//      }
   }

   private boolean locationIsValid(PotholeDto pothole) {
      return true;
   }

   private boolean potholeExists(PotholeDto potholeDto) {
      List<PotholeDto> potholes = dao.findAll();
      for (PotholeDto ph : potholes) {
         if (ph.getPotholeId() == potholeDto.getPotholeId()) {
            return true;
         }
      }
      return false;
   }


   }



