package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.PotholeDto;
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

    //TODO:uncomment this once service is in place
//   private PotholeService service;
    private UserDao userDao;

    public PotholeController(UserDao userDao) {
        //TODO:uncomment this once service is in place. Add service in line
//        this.service = service;
        this.userDao = userDao;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<PotholeDto> findAll(Principal principal){
        System.out.println(principal);
        return null;

    }
}
