package com.techelevator.service;

import com.techelevator.model.PotholeDto;

import java.security.Principal;
import java.util.List;

public interface PotholeService {

   List<PotholeDto> getAllPotholes(Principal principal);
   PotholeDto getPotholeById(int id);
   PotholeDto createPothole(PotholeDto pothole, Principal principal);
   PotholeDto updatePothole(PotholeDto potholeToUpdate, Principal principal);
   void deletePothole(int id);

}
