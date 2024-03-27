package org.example.architecture.service;

import org.example.architecture.domain.LectureApply;
import org.example.architecture.dto.request.LectureApplyRequestDTO;
import org.example.architecture.dto.response.LectureApplyResponseDTO;
import org.example.architecture.dto.response.LectureApplyStatusResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface LectureApplyService {
    LectureApplyResponseDTO applyLecture(LectureApplyRequestDTO requestDTO);

    LectureApplyStatusResponseDTO getApplyLectureStatusByUserId(String userId);
}
