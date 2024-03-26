package org.example.clearnarch.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record LectureDTO(
        String lectureName,
        Date lectureStartTime,
        int maxUserNumber,
        int currentUserNumber
) {

}
