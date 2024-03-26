package org.example.clearnarch.dto;

import java.util.Date;

public record LectureApplyDTO(
        String userId,
        Long lectureId,
        Date applyTime
) {

}
