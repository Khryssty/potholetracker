package com.techelevator.dao;

import com.techelevator.model.PotholeDto;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;

@Repository
public interface PotholeDao {

    List<PotholeDto> findAll();
    PotholeDto findPothole(int id);
    PotholeDto createPothole(PotholeDto potholeDto, int createdByUserId);
    PotholeDto updatePothole(PotholeDto oldPothole, PotholeDto newPothole, int userId);
    void deletePothole(int id);
}
