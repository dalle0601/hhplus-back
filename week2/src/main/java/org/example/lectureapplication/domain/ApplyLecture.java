package org.example.lectureapplication.domain;

import java.time.LocalDateTime;

public class ApplyLecture {
    private long id;
    private String userId;
    private LocalDateTime applyLectureTime;
    private String applyLectureStatus;

    public ApplyLecture(long id, String userId, LocalDateTime applyLectureTime, String applyLectureStatus) {
        this.id = id;
        this.userId = userId;
        this.applyLectureTime = applyLectureTime;
        this.applyLectureStatus = applyLectureStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getApplyLectureTime() {
        return applyLectureTime;
    }

    public void setApplyLectureTime(LocalDateTime applyLectureTime) {
        this.applyLectureTime = applyLectureTime;
    }

    public String getApplyLectureStatus() {
        return applyLectureStatus;
    }

    public void setApplyLectureStatus(String applyLectureStatus) {
        this.applyLectureStatus = applyLectureStatus;
    }
}
