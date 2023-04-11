package com.techelevator.model;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class PotholeDto {

    private int potholeId;
    private int locationId;
    private String photo;
    private String severity;
    private String status;
    private Date statusDate;
    private String assignedTo;
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
    private String username;

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public int getPotholeId() {
        return potholeId;
    }

    public void setPotholeId(int potholeId) {
        this.potholeId = potholeId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PotholeDto)) return false;
        PotholeDto that = (PotholeDto) o;
        return potholeId == that.potholeId && locationId == that.locationId && Objects.equals(photo, that.photo) && Objects.equals(severity, that.severity) && Objects.equals(status, that.status) && Objects.equals(statusDate, that.statusDate) && Objects.equals(assignedTo, that.assignedTo) && Objects.equals(address, that.address) && Objects.equals(lat, that.lat) && Objects.equals(lng, that.lng) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potholeId, locationId, photo, severity, status, statusDate, assignedTo, address, lat, lng, username);
    }

    @Override
    public String toString() {
        return "PotholeDto{" +
                "potholeId=" + potholeId +
                ", locationId=" + locationId +
                ", photo='" + photo + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                ", statusDate=" + statusDate +
                ", assignedTo='" + assignedTo + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", username='" + username + '\'' +
                '}';
    }
}
