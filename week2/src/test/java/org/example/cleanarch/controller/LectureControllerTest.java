package org.example.cleanarch.controller;
import org.example.clearnarch.controller.LectureController;
import org.example.clearnarch.dto.LectureDTO;
import org.example.clearnarch.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class LectureControllerTest {
    @Mock
    private LectureService lectureService;

    @InjectMocks
    private LectureController lectureController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(lectureController).build();
    }

    @DisplayName("특강 조회 API 호출 테스트")
    @Test
    void testFindLectureById() throws Exception {
        Long lectureId = 1L;

        when(lectureService.findLectureById(lectureId)).thenReturn(new LectureDTO("Test Lecture", new Date(), 30, 10));

        mockMvc.perform(get("/lecture/{id}", lectureId))
                .andExpect(status().isOk());
    }
}
