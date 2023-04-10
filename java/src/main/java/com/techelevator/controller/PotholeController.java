package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.PotholeDto;
import com.techelevator.service.PotholeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    }

