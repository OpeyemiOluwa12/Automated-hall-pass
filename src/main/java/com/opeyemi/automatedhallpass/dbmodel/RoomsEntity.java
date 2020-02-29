package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "rooms", schema = "autopassdb", catalog = "")
public class RoomsEntity {
    private int id;
    private int roomNo;
    private String roomDescription;
    private int noOfBedSpace;
    private Integer hallId;
    private Collection<BedspaceEntity> bedspacesById;
    private HallEntity hallByHallId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "room_no", nullable = false)
    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    @Basic
    @Column(name = "room_description", nullable = true, length = 255)
    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @Basic
    @Column(name = "no_of_bed_space", nullable = false)
    public int getNoOfBedSpace() {
        return noOfBedSpace;
    }

    public void setNoOfBedSpace(int noOfBedSpace) {
        this.noOfBedSpace = noOfBedSpace;
    }

    @Basic
    @Column(name = "hall_id", nullable = true)
    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomsEntity that = (RoomsEntity) o;
        return id == that.id &&
                roomNo == that.roomNo &&
                noOfBedSpace == that.noOfBedSpace &&
                Objects.equals(roomDescription, that.roomDescription) &&
                Objects.equals(hallId, that.hallId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNo, roomDescription, noOfBedSpace, hallId);
    }

    @OneToMany(mappedBy = "roomsByRoomId")
    public Collection<BedspaceEntity> getBedspacesById() {
        return bedspacesById;
    }

    public void setBedspacesById(Collection<BedspaceEntity> bedspacesById) {
        this.bedspacesById = bedspacesById;
    }

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    public HallEntity getHallByHallId() {
        return hallByHallId;
    }

    public void setHallByHallId(HallEntity hallByHallId) {
        this.hallByHallId = hallByHallId;
    }
}
