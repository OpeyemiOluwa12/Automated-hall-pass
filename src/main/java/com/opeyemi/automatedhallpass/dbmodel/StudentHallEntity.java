package com.opeyemi.automatedhallpass.dbmodel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_hall", schema = "autopassdb", catalog = "")
public class StudentHallEntity {
    private int id;
    private int studentId;
    private Integer assignedBedId;
    private StudentdetailsEntity studentdetailsByStudentId;
    private BedspaceEntity bedspaceByAssignedBedId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "assigned_bed_id", nullable = true)
    public Integer getAssignedBedId() {
        return assignedBedId;
    }

    public void setAssignedBedId(Integer assignedBedId) {
        this.assignedBedId = assignedBedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentHallEntity that = (StudentHallEntity) o;
        return id == that.id &&
                studentId == that.studentId &&
                Objects.equals(assignedBedId, that.assignedBedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, assignedBedId);
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public StudentdetailsEntity getStudentdetailsByStudentId() {
        return studentdetailsByStudentId;
    }

    public void setStudentdetailsByStudentId(StudentdetailsEntity studentdetailsByStudentId) {
        this.studentdetailsByStudentId = studentdetailsByStudentId;
    }

    @ManyToOne
    @JoinColumn(name = "assigned_bed_id", referencedColumnName = "id", insertable = false, updatable = false)
    public BedspaceEntity getBedspaceByAssignedBedId() {
        return bedspaceByAssignedBedId;
    }

    public void setBedspaceByAssignedBedId(BedspaceEntity bedspaceByAssignedBedId) {
        this.bedspaceByAssignedBedId = bedspaceByAssignedBedId;
    }
}
