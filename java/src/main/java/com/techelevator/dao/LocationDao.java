package com.techelevator.dao;

import com.techelevator.model.Location;
import com.techelevator.model.PotholeDto;

public interface LocationDao {
   Location transformToStreetAddress(PotholeDto pothole);
}
