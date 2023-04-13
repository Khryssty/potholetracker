package com.techelevator.dao;

import com.techelevator.model.Location;
import com.techelevator.model.PotholeDto;
import org.apache.tomcat.jni.Local;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
      String sql = "SELECT p.pothole_id, l.location_id, p.photo, sev.severity, stat.status, p.date_modified, p.modified_by, l.street, l.city, l.state, l.postalCode, l.lat, l.long, u.username " +
              "FROM pothole p " +
              "JOIN location l ON p.location_id = l.location_id " +
              "JOIN severity sev ON p.severity_id = sev.severity_id " +
              "JOIN status stat ON p.status_id = stat.status_id " +
              "JOIN users u ON p.modified_by = u.user_id " +
              "WHERE stat.status <> 'deleted' OR stat.status <> 'repaired';";

      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
         potholes.add(mapRowToPotholeDto(results));
      }
      return potholes;
   }

   @Override
   public PotholeDto findPothole(int id) {
      PotholeDto potholeDto = new PotholeDto();
      String sql = "SELECT p.pothole_id, l.location_id, p.photo, sev.severity, stat.status, p.date_modified, p.modified_by, l.street, l.city, l.state, l.postalCode, l.lat, l.long, u.username " +
              "FROM pothole p " +
              "JOIN location l ON p.location_id = l.location_id " +
              "JOIN severity sev ON p.severity_id = sev.severity_id " +
              "JOIN status stat ON p.status_id = stat.status_id " +
              "JOIN users u on p.modified_by = u.user_id " +
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
      sql = "INSERT INTO pothole(location_id, photo, severity_id, status_id, date_modified, modified_by) " +
              "VALUES (?, ?, ?, ?, ?, ?) RETURNING pothole_id;";
      Integer potholeId = jdbcTemplate.queryForObject(sql, Integer.class, locationId, potholeDto.getPhoto(), 1, 1, LocalDateTime.now(), createdByUserId);

      // Returned pothole id will be used to insert to log table.
      sql = "INSERT INTO log (pothole_id, modified_by, date_modified, value_before_mod, value_after_mod, field_modified) " +
              "VALUES (?, ?, ?, ?, ?, ?);";
      jdbcTemplate.update(sql, potholeId, createdByUserId, LocalDateTime.now(), 1, 1, "status_id");
      return findPothole(potholeId);
   }

   @Override
   public PotholeDto updatePothole(PotholeDto oldPothole, PotholeDto newPothole, int userId) {
         String sql = "";

      if (!oldPothole.getStatus().equals(newPothole.getStatus())){   //Compare pothole status
         //Retrieve the old status id first
            sql = "SELECT status_id FROM pothole WHERE pothole_id = ?;";
            Integer oldStatusId = jdbcTemplate.queryForObject(sql, Integer.class, newPothole.getPotholeId());

         //Update pothole table and retrieve the new status_id
            sql = "UPDATE pothole SET status_id = (SELECT status_id from status WHERE status = ?) " +
                    "date_modified = ?, modified_by = ? " +
                    "WHERE pothole_id = ? RETURNING status_id;";
            Integer newStatusId = jdbcTemplate.queryForObject(sql, Integer.class, newPothole.getStatus(),  LocalDateTime.now(), userId, newPothole.getPotholeId());

        //Insert a new record in the log table with the status old and new values
            sql = "INSERT INTO log(pothole_id, modified_by, date_modified, value_before_mod, value_after_mod, field_modified) " +
               "VALUES (?, ?, ?, ?, ?, ?);";
            jdbcTemplate.update(sql, newPothole.getPotholeId(), userId, LocalDateTime.now(), oldStatusId, newStatusId, "status_id");

      }

      if (!oldPothole.getSeverity().equals(newPothole.getSeverity())){ //compare Severity
         //Retrieve the old severity id
         sql = "SELECT severity_id from pothole WHERE pothole_id = ?;";
         Integer oldSeverityId = jdbcTemplate.queryForObject(sql, Integer.class, newPothole.getPotholeId());

         //Update the pothole table and retrieve the new severity_id
         sql = "UPDATE pothole SET severity_id = (SELECT severity_id from severity WHERE severity = ?) " +
                 "date_modified = ?, modified_by = ? " +
                 "WHERE pothole_id = ? RETURNING severity_id;";
         Integer newSeverityId = jdbcTemplate.queryForObject(sql, Integer.class, newPothole.getSeverity(), LocalDateTime.now(), userId, newPothole.getPotholeId());

         //Insert a new record in the log table with the severity old and new values
         sql = "INSERT INTO log(pothole_id, modified_by, date_modified, value_before_mod, value_after_mod, field_modified) " +
                 "VALUES (?, ?, ?, ?, ?, ?);";
         jdbcTemplate.update(sql, newPothole.getPotholeId(), userId, LocalDateTime.now(), oldSeverityId, newSeverityId, "severity_id");
      }
      return findPothole(newPothole.getPotholeId());
   }

   @Override
   public void deletePothole(int id) {
       String sql = "";
       //Query the old status id from pothole table
       sql  = "SELECT status_id from pothole WHERE pothole_id = ?;";
       Integer oldStatusId = jdbcTemplate.queryForObject(sql, Integer.class, id);

      // Query the potholeDto making sure it's tagged as deleted
      PotholeDto potholeDto = findPothole(id);


      if (!potholeDto.getStatus().equalsIgnoreCase("deleted")) {

         // The pothole record is not deleted from the database but tagged as DELETED status for audit trail purposes.
          sql = "UPDATE pothole SET status_id = 5 WHERE pothole_id = ?;";
         jdbcTemplate.update(sql, id);

         // TODO: Insert a record in log table for deleted status.

         sql = "INSERT INTO log (pothole_id, modified_by, date_modified, value_before_mod, value_after_mod, field_modified) " +
                 "VALUES ( ?, ?, ?, ?, ?, ?);";
         //jdbcTemplate.update(sql, id, LocalDateTime.now(), potholeDto.)
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
