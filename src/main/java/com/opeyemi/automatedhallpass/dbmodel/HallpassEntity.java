package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "hallpass", schema = "autopassdb", catalog = "")
public class HallpassEntity {
    private int id;
    private String bookingDate;
    private String destination;
    private String purposeOfVisit;
    private String nameOfHost;
    private String addressOfHost;
    private String timeOut;
    private String timeOfArrival;
    private String signIn;
    private Integer hallAdminId;
    private String remarks;
    private Integer studentId;
    private String status;
    private AdmindetailsEntity admindetailsByHallAdminId;
    private StudentdetailsEntity studentdetailsByStudentId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "booking_date", nullable = true)
    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Basic
    @Column(name = "destination", nullable = true, length = 255)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "purpose_of_visit", nullable = true, length = 255)
    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    @Basic
    @Column(name = "name_of_host", nullable = true, length = 255)
    public String getNameOfHost() {
        return nameOfHost;
    }

    public void setNameOfHost(String nameOfHost) {
        this.nameOfHost = nameOfHost;
    }

    @Basic
    @Column(name = "address_of_host", nullable = true, length = 255)
    public String getAddressOfHost() {
        return addressOfHost;
    }

    public void setAddressOfHost(String addressOfHost) {
        this.addressOfHost = addressOfHost;
    }

    @Basic
    @Column(name = "time_out", nullable = true)
    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    @Basic
    @Column(name = "time_of_arrival", nullable = true)
    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    @Basic
    @Column(name = "sign_in", nullable = true, length = 255)
    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    @Basic
    @Column(name = "hall_admin_id", nullable = true)
    public Integer getHallAdminId() {
        return hallAdminId;
    }

    public void setHallAdminId(Integer hallAdminId) {
        this.hallAdminId = hallAdminId;
    }

    @Basic
    @Column(name = "remarks", nullable = true, length = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "student_id", nullable = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String studentId) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HallpassEntity that = (HallpassEntity) o;
        return id == that.id &&
                Objects.equals(bookingDate, that.bookingDate) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(purposeOfVisit, that.purposeOfVisit) &&
                Objects.equals(nameOfHost, that.nameOfHost) &&
                Objects.equals(addressOfHost, that.addressOfHost) &&
                Objects.equals(timeOut, that.timeOut) &&
                Objects.equals(timeOfArrival, that.timeOfArrival) &&
                Objects.equals(signIn, that.signIn) &&
                Objects.equals(hallAdminId, that.hallAdminId) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookingDate, destination, purposeOfVisit, nameOfHost, addressOfHost, timeOut, timeOfArrival, signIn, hallAdminId, remarks, studentId);
    }

    @ManyToOne
    @JoinColumn(name = "hall_admin_id", referencedColumnName = "id", insertable = false, updatable = false)
    public AdmindetailsEntity getAdmindetailsByHallAdminId() {
        return admindetailsByHallAdminId;
    }

    public void setAdmindetailsByHallAdminId(AdmindetailsEntity admindetailsByHallAdminId) {
        this.admindetailsByHallAdminId = admindetailsByHallAdminId;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    public StudentdetailsEntity getStudentdetailsByStudentId() {
        return studentdetailsByStudentId;
    }

    public void setStudentdetailsByStudentId(StudentdetailsEntity studentdetailsByStudentId) {
        this.studentdetailsByStudentId = studentdetailsByStudentId;
    }
}
