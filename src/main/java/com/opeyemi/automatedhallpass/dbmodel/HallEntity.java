package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "hall", schema = "autopassdb", catalog = "")
public class HallEntity implements Serializable {
    private int id;
    private String hallId;
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
    @Column(name = "hall_Id", nullable = false, length = 25)
    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
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
                Objects.equals(hallId, that.hallId) &&
                Objects.equals(hallDetails, that.hallDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hallId, hallDetails);
    }

    @OneToMany(mappedBy = "hallByHallId")
    public Collection<RoomsEntity> getRoomsById() {
        return roomsById;
    }

    public void setRoomsById(Collection<RoomsEntity> roomsById) {
        this.roomsById = roomsById;
    }
}
