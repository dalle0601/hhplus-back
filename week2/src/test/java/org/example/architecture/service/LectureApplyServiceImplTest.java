package org.example.architecture.service;

import org.example.architecture.domain.Lecture;
import org.example.architecture.domain.LectureApply;
import org.example.architecture.dto.request.LectureApplyRequestDTO;
import org.example.architecture.dto.response.LectureApplyResponseDTO;
import org.example.architecture.dto.response.LectureApplyStatusResponseDTO;
import org.example.architecture.repository.LectureApplyRepository;
import org.example.architecture.repository.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureApplyServiceImplTest {
    @Mock
    private LectureApplyRepository lectureApplyRepository;

    @Mock
    private LectureRepository lectureRepository;

    private LectureApplyService lectureApplyService;

    private final Long lectureId = 1L;
    private final String userId = "test";
    private final Long maxTest = 5L;


    @BeforeEach
    void beforeEach () {
        lectureApplyService = new LectureApplyServiceImpl(lectureApplyRepository, lectureRepository);
    }

    @Test
    @DisplayName("UserId를 이용한 하나밖에 없는 특강 신청 성공 테스트")
    void LectureApplyUserIdTest () {
        Lecture mockLecture = new Lecture(lectureId, "testLecture", maxTest);
        when(lectureRepository.findByLectureId(lectureId)).thenReturn(mockLecture);

        LectureApply mockLectureApply = new LectureApply(userId, lectureId);
        when(lectureApplyRepository.save(any(LectureApply.class))).thenReturn(mockLectureApply);

        LectureApplyRequestDTO requestDTO = new LectureApplyRequestDTO(userId, lectureId);
        LectureApplyResponseDTO result = lectureApplyService.applyLecture(requestDTO);
        System.out.println(result);
        assertAll(
                () -> assertEquals(result.userId(), userId),
                () -> assertEquals(result.lectureId(), lectureId)
        );
    }

    @Test
    @DisplayName("이미 신청한 UserId가 다시 신청을 시도했을때 실패하는지 확인")
    void LectureApplyDuplicationUserIdTest () {
        Lecture mockLecture = new Lecture(lectureId, "testLecture", maxTest);
        when(lectureRepository.findByLectureId(lectureId)).thenReturn(mockLecture);

        LectureApply mockLectureApply = new LectureApply(userId, lectureId);
        when(lectureApplyRepository.findByUserId(userId)).thenReturn(Optional.of(List.of(mockLectureApply)));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            LectureApplyRequestDTO requestDTO = new LectureApplyRequestDTO(userId, lectureId);
            lectureApplyService.applyLecture(requestDTO); // 이미 신청한 경우 다시 신청을 시도
        });

        assertEquals("이미 해당 특강을 신청하셨습니다.", exception.getMessage()); // 예외가 발생하는지 확인
    }

    @Test
    @DisplayName("수강 신청 인원이 가득찼을때 UserId로 신청했을때 실패하는지 확인")
    void FullLectureApplyUserIdTest () {
        Lecture mockLecture = new Lecture(lectureId, "testLecture", maxTest);
        when(lectureRepository.findByLectureId(lectureId)).thenReturn(mockLecture);

        List<LectureApply> lectureApplyList = new ArrayList<>();
        for ( int i = 0; i < maxTest; i++){
            lectureApplyList.add(new LectureApply("test"+i, 1L));
        }
        when(lectureApplyRepository.findByLectureId(lectureId)).thenReturn(lectureApplyList);

        assertThrows(IllegalArgumentException.class, () -> {
            LectureApplyRequestDTO requestDTO = new LectureApplyRequestDTO(userId, lectureId);
            lectureApplyService.applyLecture(requestDTO); // 가득찬 상태에서 수강 신청을 시도
        });
    }

    @Test
    @DisplayName("UserId를 이용해 수강 신청이 완료되었는지 확인")
    void getApplyLectureStatusByUserIdTest () {
        List<LectureApply> lectureApplyList = new ArrayList<>();
        lectureApplyList.add(new LectureApply(userId, 1L));
        when(lectureApplyRepository.findByUserId(userId)).thenReturn(Optional.of(lectureApplyList));

        LectureApplyStatusResponseDTO result = lectureApplyService.getApplyLectureStatusByUserId(userId);

        assertEquals(200, result.code());
        assertEquals("신청성공", result.status());
    }

//    @Test
//    @DisplayName("동시성 문제 발생")
//    void LectureApplySameTimeUserIdTest () {
//
//    }

}
