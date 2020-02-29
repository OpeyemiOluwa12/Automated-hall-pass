package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "admindetails", schema = "autopassdb", catalog = "")
public class AdmindetailsEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String emaillAddress;
    private String password;
    private Collection<HallpassEntity> hallpassesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "emaill_address", nullable = false, length = 255)
    public String getEmaillAddress() {
        return emaillAddress;
    }

    public void setEmaillAddress(String emaillAddress) {
        this.emaillAddress = emaillAddress;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmindetailsEntity that = (AdmindetailsEntity) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(username, that.username) &&
                Objects.equals(emaillAddress, that.emaillAddress) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, emaillAddress, password);
    }

    @OneToMany(mappedBy = "admindetailsByHallAdminId")
    public Collection<HallpassEntity> getHallpassesById() {
        return hallpassesById;
    }

    public void setHallpassesById(Collection<HallpassEntity> hallpassesById) {
        this.hallpassesById = hallpassesById;
    }
}
