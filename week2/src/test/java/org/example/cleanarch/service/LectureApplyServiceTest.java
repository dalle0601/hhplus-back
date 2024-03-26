package org.example.cleanarch.service;
import org.example.clearnarch.domain.Lecture;
import org.example.clearnarch.dto.LectureDTO;
import org.example.clearnarch.repository.LectureRepository;
import org.example.clearnarch.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureApplyServiceTest {

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private LectureService lectureService;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("특강 조회 서비스 테스트")
    @Test
    void testFindLectureById() {
        Long lectureId = 1L;
        Lecture lecture = new Lecture(lectureId, "Test", new Date(), 30, 10);

        when(lectureRepository.findById(lectureId)).thenReturn(Optional.of(lecture));

        LectureDTO result = lectureService.findLectureById(lectureId);

//        assertEquals(lecture.getId(), result.id());
        assertEquals(lecture.getLectureName(), result.lectureName());
        assertEquals(lecture.getLectureStartTime(), result.lectureStartTime());
        assertEquals(lecture.getMaxApplyNumber(), result.maxUserNumber());
        assertEquals(lecture.getCurrentApplyNumber(), result.currentUserNumber());
    }
}