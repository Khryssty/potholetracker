package com.techelevator.model;

import java.awt.*;
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
    private double latitude;
    private double longitude;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PotholeDto)) return false;
        PotholeDto that = (PotholeDto) o;
        return potholeId == that.potholeId && locationId == that.locationId && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(photo, that.photo) && Objects.equals(severity, that.severity) && Objects.equals(status, that.status) && Objects.equals(statusDate, that.statusDate) && Objects.equals(assignedTo, that.assignedTo) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potholeId, locationId, photo, severity, status, statusDate, assignedTo, address, latitude, longitude);
    }
}
