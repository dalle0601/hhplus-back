package org.example.lectureapplication.domain;

import java.time.LocalDateTime;

public record Lecture(
        long id,
        long lectureId,
        String userId,
        LocalDateTime applyTime,
        long maxPeopleNum,
        String infoText
) {

}
