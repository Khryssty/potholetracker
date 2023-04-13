package com.techelevator.controller;

import com.techelevator.model.PotholeDto;
import com.techelevator.service.PotholeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/potholes")
public class PotholeController {

   private final PotholeService service;

   public PotholeController(PotholeService service) {
      this.service = service;
   }

   @PreAuthorize("permitAll")
   @GetMapping
   public List<PotholeDto> findAll(Principal principal) {
      return service.getAllPotholes(principal);
   }

   @GetMapping("/{id}")
   public PotholeDto getPotholeById(@PathVariable int id) {
      return service.getPotholeById(id);
   }

   /**
    * expects a request body like potholeDto
    *
    * @param potholeDto: {
    *                    "location": {
    *                    "lat": 30.333472,
    *                    "lng": -81.470448
    *                    },
    *                    "photo": "default"
    *                    }
    * @param principal
    * @return
    */
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public PotholeDto createPothole(@RequestBody PotholeDto potholeDto, Principal principal) {
       PotholeDto createdPothole = service.createPothole(potholeDto, principal);
       if(createdPothole == null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location invalid");
       }
      return createdPothole;
   }

   @PutMapping
   public PotholeDto updatePothole(@RequestBody PotholeDto potholeDto, Principal principal) {
      return service.updatePothole(potholeDto, principal);
   }

   @DeleteMapping("/{id}")
   public void deletePothole(@PathVariable int id) {
      service.deletePothole(id);
   }
}

