package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "hall", schema = "autopassdb", catalog = "")
public class HallEntity {
    private int id;
    private int hallNo;
    private String hallDetails;
    private Collection<RoomsEntity> roomsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hall_no", nullable = false)
    public int getHallNo() {
        return hallNo;
    }

    public void setHallNo(int hallNo) {
        this.hallNo = hallNo;
    }

    @Basic
    @Column(name = "hall_details", nullable = true, length = 255)
    public String getHallDetails() {
        return hallDetails;
    }

    public void setHallDetails(String hallDetails) {
        this.hallDetails = hallDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HallEntity that = (HallEntity) o;
        return id == that.id &&
                hallNo == that.hallNo &&
                Objects.equals(hallDetails, that.hallDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hallNo, hallDetails);
    }

    @OneToMany(mappedBy = "hallByHallId")
    public Collection<RoomsEntity> getRoomsById() {
        return roomsById;
    }

    public void setRoomsById(Collection<RoomsEntity> roomsById) {
        this.roomsById = roomsById;
    }
}
