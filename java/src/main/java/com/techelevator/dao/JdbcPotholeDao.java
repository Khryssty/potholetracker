package com.techelevator.dao;

import com.techelevator.model.Location;
import com.techelevator.model.PotholeDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao {
   private JdbcTemplate jdbcTemplate;

   public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
   }

   @Override
   public List<PotholeDto> findAll() {
      List<PotholeDto> potholes = new ArrayList<>();
      String sql = "SELECT p.pothole_id, l.location_id, p.photo, sev.severity, stat.status, log.date_modified, log.modified_by, l.street, l.city, l.state, l.postalCode, l.lat, l.long, u.username " +
              "FROM pothole p " +
              "JOIN location l ON p.location_id = l.location_id " +
              "JOIN severity sev ON p.severity_id = sev.severity_id " +
              "JOIN status stat ON p.status_id = stat.status_id " +
              "JOIN log on p.pothole_id = log.pothole_id " +
              "JOIN users u ON log.modified_by = u.user_id;";

      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
         potholes.add(mapRowToPotholeDto(results));
      }
      return potholes;
   }

   @Override
   public PotholeDto findPothole(int id) {
      PotholeDto potholeDto = new PotholeDto();
      String sql = "SELECT p.pothole_id, l.location_id, p.photo, sev.severity, stat.status, log.date_modified, log.modified_by, l.street, l.city, l.state, l.postalCode, l.lat, l.long, u.username " +
              "FROM pothole p " +
              "JOIN location l ON p.location_id = l.location_id " +
              "JOIN severity sev ON p.severity_id = sev.severity_id " +
              "JOIN status stat ON p.status_id = stat.status_id " +
              "JOIN log on p.pothole_id = log.pothole_id " +
              "JOIN users u on log.modified_by = u.user_id " +
              "WHERE p.pothole_id = ?;";

      SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
      if (result.next()) {
         potholeDto = mapRowToPotholeDto(result);
      }

      return potholeDto;
   }

   @Override
   public PotholeDto createPothole(PotholeDto potholeDto, int createdByUserId) {
      // Insert to location table first to retrieve a location id.
      Location potholeLocation = potholeDto.getLocation();
      String sql = "INSERT INTO location (street, city, state, postalCode, lat, long) " +
              "VALUES (?, ?, ?, ?, ?, ?) RETURNING location_id;";
      Integer locationId = jdbcTemplate.queryForObject(sql, Integer.class, potholeLocation.getStreet(), potholeLocation.getCity(),
              potholeLocation.getState(), potholeLocation.getPostalCode(), potholeLocation.getLat(), potholeLocation.getLng());

      //Use the location id that was returned to insert into pothole table.
      sql = "INSERT INTO pothole(location_id, photo, severity_id, status_id) " +
              "VALUES (?, ?, ?, ?) RETURNING pothole_id;";
      Integer potholeId = jdbcTemplate.queryForObject(sql, Integer.class, locationId, potholeDto.getPhoto(), 1, 1);

      // Returned pothole id will be used to insert to log table.
      sql = "INSERT INTO log (pothole_id, modified_by, date_modified, status_before_mod, status_after_mod) " +
              "VALUES (?, ?, ?, ?, ?);";
      jdbcTemplate.update(sql, potholeId, createdByUserId, LocalDate.now(), 1, 1);
      return findPothole(potholeId);
   }

   @Override
   public PotholeDto updatePothole(PotholeDto potholeDto) {
//TODO: Update pothole
//        if (potholeDto.)
//
//        String sql = "UPDATE pothole " +
//                    "SET "

      return null;
   }

   @Override
   public void deletePothole(int id) {
      // Query the pothole
      PotholeDto potholeDto = findPothole(id);

      if (!potholeDto.getStatus().equalsIgnoreCase("deleted")) {

         // The pothole record is not deleted from the database but tagged as DELETED status for audit trail purposes.
         String sql = "UPDATE pothole SET status_id = 5 WHERE pothole_id = ?;";
         jdbcTemplate.update(sql, id);

         // TODO: Insert a record in log table for deleted status.

         sql = "INSERT INTO log (pothole_id, modified_by, date_modified, status_before_mod, status_after_mod) " +
                 "VALUES ( ?, ?, ?, ?, ?);";
         //        jdbcTemplate.update(sql, )
      }

   }

   private PotholeDto mapRowToPotholeDto(SqlRowSet rowSet) {
      Location location = new Location();
      location.setLocationId(rowSet.getInt("location_id"));
      location.setLat(rowSet.getDouble("lat"));
      location.setLng(rowSet.getDouble("long"));
      location.setStreet(rowSet.getString("street"));
      location.setCity(rowSet.getString("city"));
      location.setState(rowSet.getString("state"));
      location.setPostalCode(rowSet.getString("postalCode"));

      PotholeDto potholeDto = new PotholeDto();
      potholeDto.setPotholeId(rowSet.getInt("pothole_id"));
      potholeDto.setLocation(location);
      potholeDto.setPhoto(rowSet.getString("photo"));
      potholeDto.setSeverity(rowSet.getString("severity"));
      potholeDto.setStatus(rowSet.getString("status"));
      potholeDto.setStatusDate(rowSet.getDate("date_modified"));
      potholeDto.setUsername(rowSet.getString("username"));
      return potholeDto;
   }


}
