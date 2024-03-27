package org.example.architecture.dto.response;

import java.util.Date;

public record LectureApplyResponseDTO (
        String userId,
        Long lectureId,
        Date applyDate
){
}
