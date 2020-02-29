package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bedspace", schema = "autopassdb", catalog = "")
public class BedspaceEntity {
    private int id;
    private int roomId;
    private String bedSpace;
    private RoomsEntity roomsByRoomId;
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
    @Column(name = "room_id", nullable = false)
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "bed_space", nullable = true, length = 255)
    public String getBedSpace() {
        return bedSpace;
    }

    public void setBedSpace(String bedSpace) {
        this.bedSpace = bedSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedspaceEntity that = (BedspaceEntity) o;
        return id == that.id &&
                roomId == that.roomId &&
                Objects.equals(bedSpace, that.bedSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, bedSpace);
    }

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    public RoomsEntity getRoomsByRoomId() {
        return roomsByRoomId;
    }

    public void setRoomsByRoomId(RoomsEntity roomsByRoomId) {
        this.roomsByRoomId = roomsByRoomId;
    }

    @OneToMany(mappedBy = "bedspaceByAssignedBedId")
    public Collection<StudentHallEntity> getStudentHallsById() {
        return studentHallsById;
    }

    public void setStudentHallsById(Collection<StudentHallEntity> studentHallsById) {
        this.studentHallsById = studentHallsById;
    }
}
