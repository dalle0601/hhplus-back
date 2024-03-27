package org.example.architecture.domain;

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
    private Long lectureId;
    private String lectureName;
    private Long maxNumber;
    private Date startDate;

    public Lecture() {}

    public Lecture(Long lectureId) {
        this.lectureId = lectureId;
    }

    public Lecture(Long lectureId, String lectureName, Long maxNumber) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.maxNumber = maxNumber;
    }
}
