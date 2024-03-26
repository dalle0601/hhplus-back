package org.example.cleanarch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.clearnarch.controller.LectureApplyController;
import org.example.clearnarch.dto.LectureApplyDTO;
import org.example.clearnarch.service.LectureApplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LectureApplyControllerTest {
    @Mock
    private LectureApplyService lectureApplyService;

    @InjectMocks
    private LectureApplyController lectureApplyController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(lectureApplyController).build();
    }

    @DisplayName("특강 신청 API 호출 테스트")
    @Test
    void testApplyLecture() throws Exception {
        LectureApplyDTO lectureApplyDTO = new LectureApplyDTO( "user123", 1L, new Date());

        when(lectureApplyService.applyLecture(any(LectureApplyDTO.class))).thenReturn(true);

        mockMvc.perform(post("/lecture/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lectureApplyDTO))) // ObjectMapper를 사용하여 직렬화
                .andExpect(status().isOk());
    }

    @DisplayName("특강 신청 여부 조회 API 호출 테스트")
    @Test
    void testIsAlreadyApplied() throws Exception {
        String userId = "user123";
        Long lectureId = 1L;

        when(lectureApplyService.isAlreadyApplied(anyString(), anyLong())).thenReturn(true);

        mockMvc.perform(get("/lecture/apply/check")
                        .param("userId", userId)
                        .param("lectureId", String.valueOf(lectureId)))
                .andExpect(status().isOk());
    }
}
