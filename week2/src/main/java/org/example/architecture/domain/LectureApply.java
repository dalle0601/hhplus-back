package org.example.architecture.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
@Table (name="LECTURE_APPLY")
public class LectureApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Long lectureId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyDate = new Date();;

    public LectureApply() {}
    public LectureApply(String userId, Long lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }
}
