package com.techelevator.dao;

import com.techelevator.model.PotholeDto;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcPotholeDao implements PotholeDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PotholeDto> findAll() {
        List<PotholeDao> potholes = new ArrayList<>();



        return null;
    }

    @Override
    public PotholeDto findPothole(int id) {
        String sql = "SELECT p.pothole_id, l.location_id, sev.severity, stat.status, log.date_modified, log.modified_by, l.street_address, l.lat_long " +
                "FROM pothole p " +
                "JOIN location l ON p.location_id = l.location_id " +
                "JOIN severity sev ON p.severity_id = sev.severity_id " +
                "JOIN status stat ON p.status_id = stat.status_id " +
                "JOIN log on p.pothole_id = log.pothole_id;";

        return null;
    }

    @Override
    public PotholeDto createPothole(PotholeDto potholeDto) {
        // Insert to location table first to retrieve a location id.
    String sql = "INSERT INTO location (street_address, lat_long) " +
                 "VALUES (?, ?) RETURNING location_id;";
    Integer id =  jdbcTemplate.queryForObject(sql, Integer.class, potholeDto.getAddress(), potholeDto.getCoordinates());

    //Use the location id that was returned to insert into pothole table.
        sql = "INSERT INTO pothole(location_id, severity_id, status_id) " +
            "VALUES (?, ?, ?);";
        id = jdbcTemplate.queryForObject(sql, Integer.class, id, 1 ,1);

    // Returned pothole id will be used to insert to log table.
        sql = "INSERT INTO log (pothole_id, modified_by, date_modified, status_after_mod) " +
            "VALUES (?, ?, ?, ?);";
        id = jdbcTemplate.queryForObject(sql, Integer.class, id, LocalDate.now(), 1);
    //TODO:
    return null;
    }

    @Override
    public PotholeDto updatePothole(PotholeDto potholeDto) {
        return null;
    }

    @Override
    public PotholeDto deletePothole(int id) {
        return null;
    }

private PotholeDto mapRowToPotholeDto(SqlRowSet rowSet){
   PotholeDto potholeDto = new PotholeDto();
   potholeDto.setPotholeId(rowSet.getInt("pothole_id"));
   potholeDto.setLocationId(rowSet.getInt("location_id"));
   potholeDto.setPhoto(rowSet.getString("photo"));
   potholeDto.setSeverity(rowSet.getString("severity"));
   potholeDto.setStatus(rowSet.getString("status"));
   potholeDto.setStatusDate(rowSet.getDate("date_modified"));
   potholeDto.setAssignedTo(rowSet.getString("user"));
   potholeDto.setAddress(rowSet.getString("street_addres"));
   //potholeDto.setCoordinates(rowset.getPoint("lat_lang"));
   return potholeDto;
}
}
