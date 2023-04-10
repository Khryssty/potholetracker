package com.techelevator.dao;

import com.techelevator.model.PotholeDto;

import java.util.List;

public interface PotholeDao {

    List<PotholeDto> findAll();
    PotholeDto findPothole(int id);
    PotholeDto createPothole(PotholeDto potholeDto);
    PotholeDto updatePothole(PotholeDto potholeDto);
    void deletePothole(int id);
}
