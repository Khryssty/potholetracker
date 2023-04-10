package com.techelevator.service;

import com.techelevator.model.PotholeDto;

import java.security.Principal;
import java.util.List;

public interface PotholeService {

   List<PotholeDto> getAllPotholes(Principal principal);
   PotholeDto createPothole(PotholeDto pothole);
   PotholeDto updatePothole(PotholeDto potholeToUpdate);
   void deletePothole(int id);

}
