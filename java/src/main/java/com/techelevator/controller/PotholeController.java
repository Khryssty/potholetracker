package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.PotholeDto;
import com.techelevator.service.PotholeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="/potholes")
public class PotholeController {


    private PotholeService service;
    private UserDao userDao;

    public PotholeController(UserDao userDao, PotholeService service) {
        this.service = service;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(method = RequestMethod.GET)
    public List<PotholeDto> findAll(Principal principal){
        return service.getAllPotholes(principal);
        }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PotholeDto getPotholeById(@PathVariable int id){
        return service.getPotholeById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public PotholeDto createPothole(@RequestBody PotholeDto potholeDto, Principal principal){
        return service.createPothole(potholeDto, principal);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public PotholeDto updatePothole(@RequestBody PotholeDto potholeDto){
        return service.updatePothole(potholeDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deletePothole(@PathVariable int id){
        service.deletePothole(id);
    }
}

