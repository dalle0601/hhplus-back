package org.example.lectureapplication.service;

import org.example.lectureapplication.domain.ApplyLecture;
import org.example.lectureapplication.repository.JdbcLectureRepository;
import org.example.lectureapplication.repository.LectureRepository;
import org.example.lectureapplication.repository.MemoryLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
//    private final LectureRepository lectureRepository= new MemoryLectureRepository();
private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    /*
        특강신청
     */
    public ApplyLecture applyLecture(String userId) {
        // 모집 인원을 초과하는지 확인
        List<ApplyLecture> totalList = lectureRepository.findApplyListAll();
        if (totalList.size() >= 3) {
            throw new IllegalStateException("모집 인원을 초과했습니다.");
        }

        // 특정 사용자의 신청 목록을 가져옴
        Optional<ApplyLecture> findBeforeApply = lectureRepository.findApplyList(userId);
        if (findBeforeApply.isPresent()) {
            // 이미 신청한 경우 예외 발생
            throw new IllegalStateException("이미 특강에 신청되어 있습니다.");
        }

        // 사용자가 신청하지 않은 경우, 특강에 신청하고 저장
        return lectureRepository.save(userId);
    }

    /*
        특강 내역 조회
     */
    public String getApplyList(String userId) {
        if (lectureRepository.findApplyList(userId).isEmpty()) {
            // 신청 목록이 비어있는 경우
            return "신청실패";
        } else {
            // 신청 목록이 비어있지 않은 경우
            return "신청성공";
        }
    }
}
