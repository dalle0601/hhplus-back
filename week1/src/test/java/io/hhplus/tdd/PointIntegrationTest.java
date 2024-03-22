package io.hhplus.tdd;

import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;
import io.hhplus.tdd.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PointIntegrationTest {
    @Autowired
    PointService pointService;

    /*
        1000 포인트 충전
        500 포인트 사용
        포인트 충전 / 사용 내역 조회
        700 포인트 사용 -> 남은 포인트보다 큰 포인트 사용, 예외처리
        포인트 조회
     */
    @DisplayName("시나리오 정의 및 테스트 구현")
    @Test
    void integrationTest() {
        pointService.chargePoint(1L, 1000L);

        pointService.usePoint(1L, 400L);

        List<PointHistory> pointHistoryList = pointService.getHistory(1L);
        assertAll(
                ()-> assertEquals(pointHistoryList.get(0).type(), TransactionType.CHARGE),
                ()-> assertEquals(pointHistoryList.get(0).amount(), 1000L),
                ()-> assertEquals(pointHistoryList.get(1).type(), TransactionType.USE),
                ()-> assertEquals(pointHistoryList.get(1).amount(), 400L)
        );

        assertThrows(IllegalArgumentException.class, () -> pointService.usePoint(1L, 700L));

        // 내 포인트 조회
        UserPoint finalUserPoint = pointService.getUserPoint(1L);
        assertEquals(600L, finalUserPoint.point());
    }
}
