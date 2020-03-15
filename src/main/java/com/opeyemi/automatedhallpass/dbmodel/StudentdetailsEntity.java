package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "studentdetails", schema = "autopassdb", catalog = "")
public class StudentdetailsEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String matricNo;
    private String faculty;
    private String course;
    private String level;
    private String gender;
    private String homeAddress;
    private String phoneNo;
    private String emailAddress;
    private Collection<HallpassEntity> hallpassesById;
    private Collection<StudentHallEntity> studentHallsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = -1)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = -1)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "matric_no", nullable = false, length = 255)
    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    @Basic
    @Column(name = "faculty", nullable = false, length = 255)
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Basic
    @Column(name = "course", nullable = false, length = 255)
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Basic
    @Column(name = "level", nullable = false, length = 255)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 255)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "home_address", nullable = false, length = 255)
    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Basic
    @Column(name = "phone_no", nullable = false)
    public String  getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Basic
    @Column(name = "email_address", nullable = false, length = 255)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentdetailsEntity that = (StudentdetailsEntity) o;
        return id == that.id &&
                phoneNo == that.phoneNo &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(matricNo, that.matricNo) &&
                Objects.equals(faculty, that.faculty) &&
                Objects.equals(course, that.course) &&
                Objects.equals(level, that.level) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(homeAddress, that.homeAddress) &&
                Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, matricNo, faculty, course, level, gender, homeAddress, phoneNo, emailAddress);
    }

    @OneToMany(mappedBy = "studentdetailsByStudentId")
    public Collection<HallpassEntity> getHallpassesById() {
        return hallpassesById;
    }

    public void setHallpassesById(Collection<HallpassEntity> hallpassesById) {
        this.hallpassesById = hallpassesById;
    }

    @OneToMany(mappedBy = "studentdetailsByStudentId")
    public Collection<StudentHallEntity> getStudentHallsById() {
        return studentHallsById;
    }

    public void setStudentHallsById(Collection<StudentHallEntity> studentHallsById) {
        this.studentHallsById = studentHallsById;
    }
}
