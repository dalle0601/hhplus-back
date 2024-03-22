package io.hhplus.tdd.point;


import io.hhplus.tdd.service.PointService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PointControllerTest {

    @Mock
    PointService pointService;
    @InjectMocks
    PointController pointController;
    MockMvc mockMvc;

    long TEST_USER = 1;

    @BeforeEach
    public void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(pointController).build();
    }

    @DisplayName("특정 유저의 포인트를 조회 / GET > {id}")
    @Test
    void testGetUserPoint() throws Exception {
        Long userId = 1L;
        UserPoint userPoint = new UserPoint(userId, 1000, System.currentTimeMillis());

        when(pointService.getUserPoint(anyLong())).thenReturn(userPoint);

        mockMvc.perform(get("/point/{id}", userId))
                .andExpect(status().isOk()) // HTTP 상태 코드가 200 OK인지 확인
                .andExpect(jsonPath("$.id").value(userId)) // 응답 본문의 id 필드가 userId와 일치하는지 확인합니다.
                .andExpect(jsonPath("$.point").value(userPoint.point()));
    }

    @DisplayName("특정 유저의 포인트 충전, 사용 내역 조회 / GET > {id}/histories")
    @Test
    void testGetListPointHistory() throws Exception {
        List<PointHistory> pointHistoryList = new ArrayList<>();

        pointHistoryList.add(new PointHistory(1, TEST_USER, 333, TransactionType.CHARGE, System.currentTimeMillis()));
        when(pointService.getHistory(anyLong())).thenReturn(pointHistoryList);

        mockMvc.perform(get("/point/{id}/histories", TEST_USER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1)) // 첫 번째 항목의 값을 비교
                .andExpect(jsonPath("$[0].userId").value(TEST_USER))
                .andExpect(jsonPath("$[0].amount").value(333))
                .andExpect(jsonPath("$[0].type").value(TransactionType.CHARGE.name()));
    }

    @DisplayName("특정 유저의 포인트를 충전 / PATCH > {id}/charge")
    @Test
    void testChargePoint() throws Exception {
        UserPoint userPoint = new UserPoint(TEST_USER, 1000, System.currentTimeMillis());

        when(pointService.chargePoint(anyLong(), anyLong())).thenReturn(userPoint);

        String amount = "1000";
        mockMvc.perform(patch("/point/{id}/charge", TEST_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(amount))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.point").value(1000));

    }

    @DisplayName("특정 유저의 포인트를 사용 / PATCH > {id}/ues")
    @Test
    void testUsePointTest() throws Exception {
        UserPoint userPoint = new UserPoint(TEST_USER, 999, System.currentTimeMillis());

        when(pointService.usePoint(anyLong(), anyLong())).thenReturn(userPoint);

        String amount = "999";
        mockMvc.perform(patch("/point/{id}/use", TEST_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(amount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.point").value(999));

    }
}
