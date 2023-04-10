package com.techelevator.service;

import com.techelevator.model.PotholeDto;

import java.util.List;

public interface PotholeService {

   List<PotholeDto> getAllPotholes();
   PotholeDto createPothole(PotholeDto pothole);
   PotholeDto updatePothole(PotholeDto potholeToUpdate);
   void deletePothole(PotholeDto pothole);

}
