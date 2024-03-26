package org.example.clearnarch.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
@Table(name="LECTURE_APPLY")
public class LectureApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Long lectureId;
    private Date applyTime;

    public LectureApply(String userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public LectureApply() {

    }
}
