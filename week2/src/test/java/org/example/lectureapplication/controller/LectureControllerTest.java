package org.example.lectureapplication.controller;

import org.example.lectureapplication.domain.Lecture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LectureControllerTest {
    private MockMvc mockMvc;

    @Mock
//    LectureService lectureService;

    String id = "test";
    @DisplayName("특강 신청 API")
    @Test
    void applyLectureTest () throws Exception {
//        String requestBody = "{\"userId\": \"test\", \"lectureId\": 1}";
//        // 신청된 특강에 대한 정보를 담은 객체
//        when(lectureService.insertLecture(anyString(), anyInt())).thenReturn(new Lecture(1, "test", LocalDateTime.now(), new LectureInfo("Spring Boot 기초", LocalDate.of(2024, 4, 1), LocalTime.of(14, 0), "온라인"), "신청 완료"));
//        // POST 요청 보내기
//        mockMvc.perform(post("/apply-lecture")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isOk());
    }

    @DisplayName("특강 신청 여부 조회 API")
    @Test
    void getApplyLectureListTest () {

    }
}
