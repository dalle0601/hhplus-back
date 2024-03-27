package org.example.architecture.service;

import org.example.architecture.domain.Lecture;
import org.example.architecture.domain.LectureApply;
import org.example.architecture.dto.request.LectureApplyRequestDTO;
import org.example.architecture.dto.response.LectureApplyResponseDTO;
import org.example.architecture.dto.response.LectureApplyStatusResponseDTO;
import org.example.architecture.repository.LectureApplyRepository;
import org.example.architecture.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureApplyServiceImpl implements LectureApplyService {
    private final LectureApplyRepository lectureApplyRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureApplyServiceImpl(LectureApplyRepository lectureApplyRepository, LectureRepository lectureRepository) {
        this.lectureApplyRepository = lectureApplyRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public LectureApplyResponseDTO applyLecture(LectureApplyRequestDTO requestDTO) {
        checkMaxNumberApplyLecture(requestDTO.lectureId());
        validateDupicationApply(requestDTO.userId(), requestDTO.lectureId());
        LectureApply insertLectureApply = new LectureApply(requestDTO.userId(), requestDTO.lectureId());
        lectureApplyRepository.save(insertLectureApply);
        return new LectureApplyResponseDTO(insertLectureApply.getUserId(), insertLectureApply.getLectureId(), insertLectureApply.getApplyDate());
    }

    @Override
    public LectureApplyStatusResponseDTO getApplyLectureStatusByUserId(String userId) {
        Optional<List<LectureApply>> optionalLectureApplyList = lectureApplyRepository.findByUserId(userId);

        if (!optionalLectureApplyList.get().isEmpty()) {
//            List<LectureApply> lectureApplyList = optionalLectureApplyList.get();
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                return objectMapper.writeValueAsString(lectureApplyList);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
            return new LectureApplyStatusResponseDTO(200, "신청성공");
        }
        return new LectureApplyStatusResponseDTO(400, "신청실패");
    }
    /*
        중복으로 강의 신청시 예외처리
     */
    public void validateDupicationApply (String userId, Long lectureId) {
        Optional<List<LectureApply>> optionalLectureApplyList = lectureApplyRepository.findByUserId(userId);
        if( optionalLectureApplyList.isPresent()) {
            List<LectureApply> lectureApplyList = optionalLectureApplyList.get();
            // 중복 여부 확인
            boolean isDuplicated = lectureApplyList.stream()
                    .anyMatch(apply -> apply.getLectureId().equals(lectureId));

            // 중복되는 신청이 있을 경우 에러 처리
            if (isDuplicated) {
                throw new IllegalArgumentException("이미 해당 특강을 신청하셨습니다.");
            }
        }
    }
    /*
        수강인원 가득 찼을때 신청할경우 예외처리
     */
    public void checkMaxNumberApplyLecture (Long lectureId) {
        // 해당 강의가 총 몇건 신청했는지 가져오기
        Optional<List<LectureApply>> lectureApplyList = Optional.ofNullable(lectureApplyRepository.findByLectureId(lectureId));
        // 해당 강의의 최대 수강인원 가져오기
        Lecture lectureApplyMaxCount = lectureRepository.findByLectureId(lectureId);

        // 최대 수강인원 = lectureApplyMaxCount.getMaxNumber()
        if(lectureApplyList.isPresent()) {
            // 값이 존재하는 경우
            long numberApplyLecture = (long) lectureApplyList.get().size();
            if(numberApplyLecture >= lectureApplyMaxCount.getMaxNumber()){
                throw new IllegalArgumentException("모집 인원을 초과했습니다.");
            }
        }
    }
}
