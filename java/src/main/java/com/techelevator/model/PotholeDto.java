package com.techelevator.model;

import java.util.Date;
import java.util.Objects;

public class PotholeDto {

    private int potholeId;
    private Location location;
    private String photo;
    private String severity;
    private String status;
    private Date statusDate;
    private String assignedTo;
    private String username;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPotholeId() {
        return potholeId;
    }

    public void setPotholeId(int potholeId) {
        this.potholeId = potholeId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PotholeDto{" +
                "potholeId=" + potholeId +
                ", location=" + location +
                ", photo='" + photo + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                ", statusDate=" + statusDate +
                ", assignedTo='" + assignedTo + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotholeDto that = (PotholeDto) o;
        return potholeId == that.potholeId && Objects.equals(location, that.location) && Objects.equals(photo, that.photo) && Objects.equals(severity, that.severity) && Objects.equals(status, that.status) && Objects.equals(statusDate, that.statusDate) && Objects.equals(assignedTo, that.assignedTo) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potholeId, location, photo, severity, status, statusDate, assignedTo, username);
    }
}
