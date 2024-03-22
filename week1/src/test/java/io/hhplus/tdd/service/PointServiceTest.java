package io.hhplus.tdd.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {
    /*
    - PATCH  `/point/{id}/charge` : 포인트를 충전한다.
    - PATCH `/point/{id}/use` : 포인트를 사용한다.
    - GET `/point/{id}` : 포인트를 조회한다.
    - GET `/point/{id}/histories` : 포인트 내역을 조회한다.
    - 잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.
    - 동시에 여러 건의 포인트 충전, 이용 요청이 들어올 경우 순차적으로 처리되어야 합니다.
     */
    @Mock
    UserPointTable userPointTable;
    @Mock
    PointHistoryTable pointHistoryTable;
    @InjectMocks
    PointService pointService;
    private static final long TEST_USER = 1;
    private static final long TEST_AMOUNT = 1000;

    @DisplayName("0 또는 음수의 포인트는 충전 불가")
    @Test
    void chargePointIllegalArgumentTest() {
        Long testAmount = -400L;
        assertThrows(IllegalArgumentException.class, () -> pointService.chargePoint(TEST_USER, testAmount));
    }

    @DisplayName("포인트 충전")
    @Test
    void chargePointTest() {
        // 충전할 포인트 양 설정 및 충전 후 결과 확인
        UserPoint userPointSelect = new UserPoint(TEST_USER, 1000, System.currentTimeMillis());
        UserPoint userPointCharge = new UserPoint(TEST_USER, 2000, System.currentTimeMillis());
        when(userPointTable.selectById(anyLong())).thenReturn(userPointSelect);
        when(userPointTable.insertOrUpdate(anyLong(), anyLong())).thenReturn(userPointCharge);

        UserPoint result = pointService.chargePoint(TEST_USER, 2000L);

        assertEquals(2000L, result.point());
    }

    @DisplayName("포인트를 사용한다.")
    @Test
    void usePointTest() {
        // 사용할 포인트 양 설정 및 사용 후 결과 확인
        Long testUseAmount = 700L;
        UserPoint userPointCharge = new UserPoint(TEST_USER, 2000, System.currentTimeMillis());
        UserPoint userPointUse = new UserPoint(TEST_USER, 1300, System.currentTimeMillis());
        when(userPointTable.selectById(anyLong())).thenReturn(userPointCharge);
        when(userPointTable.insertOrUpdate(anyLong(), anyLong())).thenReturn(userPointUse);

        UserPoint result = pointService.usePoint(TEST_USER, testUseAmount);
        assertThat(result.point()).isEqualTo(1300L);
    }

    @DisplayName("0 또는 음수의 포인트는 사용할 수 없습니다.")
    @Test
    void usePointIllegalArgumentTest() {
        Long testAmount = -400L;
        assertThrows(IllegalArgumentException.class, () -> pointService.usePoint(TEST_USER, testAmount));
    }

    @DisplayName("포인트를 조회한다.")
    @Test
    void getUserPointTest() {
        UserPoint userPoint = new UserPoint(TEST_USER, 1000, System.currentTimeMillis());
        when(userPointTable.selectById(anyLong())).thenReturn(userPoint);

        UserPoint result = pointService.getUserPoint(TEST_USER);

        assertThat(result.point()).isEqualTo(1000L);
    }

    @DisplayName("포인트 내역을 조회한다.")
    @Test
    void getHistoryTest() {
        List<PointHistory> pointHistoryList = new ArrayList<>();
        pointHistoryList.add(new PointHistory(1, TEST_USER, 1000, TransactionType.CHARGE, System.currentTimeMillis()));
        pointHistoryList.add(new PointHistory(2, TEST_USER, 100, TransactionType.USE, System.currentTimeMillis()));
        when(pointService.getHistory(anyLong())).thenReturn(pointHistoryList);

        List<PointHistory> result = pointService.getHistory(TEST_USER);

        assertThat(pointHistoryList).isEqualTo(result);
    }

    @DisplayName("잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.")
    @Test
    void usePointTest_balance() {
        UserPoint currentUserPoint = new UserPoint(TEST_USER, 100, System.currentTimeMillis());
        when(userPointTable.selectById(anyLong())).thenReturn(currentUserPoint);

        assertThrows(IllegalArgumentException.class, () -> pointService.usePoint(TEST_USER, 600L));
    }
}
