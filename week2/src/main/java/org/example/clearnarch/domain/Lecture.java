package org.example.clearnarch.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
@Table(name="LECTURE")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lectureName;
    private Date lectureStartTime;
    private int maxApplyNumber;
    private int currentApplyNumber;

    public Lecture(Long id, String lectureName, Date lectureStartTime, int maxApplyNumber, int currentApplyNumber) {
        this.id = id;
        this.lectureName = lectureName;
        this.lectureStartTime = lectureStartTime;
        this.maxApplyNumber = maxApplyNumber;
        this.currentApplyNumber = currentApplyNumber;
    }

    public Lecture() {

    }
}
