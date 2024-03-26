package org.example.clearnarch.service;

import org.example.clearnarch.domain.LectureApply;
import org.example.clearnarch.dto.LectureApplyDTO;
import org.example.clearnarch.repository.LectureApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureApplyService {
    private final LectureApplyRepository lectureApplyRepository;

    @Autowired
    public LectureApplyService(LectureApplyRepository lectureApplyRepository) {
        this.lectureApplyRepository = lectureApplyRepository;
    }

    public boolean applyLecture(LectureApplyDTO lectureApplyDTO) {
        if (!lectureApplyRepository.existsByUserIdAndLectureId(lectureApplyDTO.userId(), lectureApplyDTO.lectureId())) {
            // Save the application
            lectureApplyRepository.save(new LectureApply(lectureApplyDTO.userId(), lectureApplyDTO.lectureId()));
            return true;
        }
        return false;
    }

    public boolean isAlreadyApplied(String userId, Long lectureId) {
        return lectureApplyRepository.existsByUserIdAndLectureId(userId, lectureId);
    }
}
