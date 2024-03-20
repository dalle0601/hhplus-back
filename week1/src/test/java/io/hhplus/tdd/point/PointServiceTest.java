package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.service.PointService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PointServiceTest {

    /*
    - PATCH  `/point/{id}/charge` : 포인트를 충전한다.
    - PATCH `/point/{id}/use` : 포인트를 사용한다.
    - GET `/point/{id}` : 포인트를 조회한다.
    - GET `/point/{id}/histories` : 포인트 내역을 조회한다.
    - 잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.
    - 동시에 여러 건의 포인트 충전, 이용 요청이 들어올 경우 순차적으로 처리되어야 합니다.
     */

    @Autowired
    private PointService pointService;
    @Autowired
    UserPointTable userPointTable;
    @Autowired
    PointHistoryTable pointHistoryTable;
    private static final long TEST_USER = 1;

    // 동시성 테스트에 사용할 변수
    Long totalAmounts = 0L;

    @BeforeEach
    void beforeEach() {
        // 사용할 테이블 초기화
//        userPointTable = new UserPointTable();
//        pointHistoryTable = new PointHistoryTable();
//
//        // PointService 생성 및 초기 데이터 설정
//        pointService = new PointService(userPointTable, pointHistoryTable);
        pointService.chargePoint(TEST_USER, 1000L);
    }

    @AfterEach
    void afterEach() {
        userPointTable.clearStore();
        pointHistoryTable.clearStore();
    }

    @DisplayName("0 또는 음수의 포인트는 충전할 수 없습니다.")
    @Test
    void chargePointIllegalArgumentTest() {
        Long testAmount = -400L;

        assertThatThrownBy(() -> pointService.chargePoint(TEST_USER, testAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 혹은 음수의 포인트 입력");
    }

    @DisplayName("포인트를 충전한다.")
    @Test
    void chargePointTest() {
        // 충전할 포인트 양 설정 및 충전 후 결과 확인
        Long testAmount = 400L;

        UserPoint result = pointService.chargePoint(TEST_USER, testAmount);
        assertThat(result.point()).isEqualTo(1400L);
    }

    @DisplayName("포인트를 사용한다.")
    @Test
    void usePointTest() {
        // 사용할 포인트 양 설정 및 사용 후 결과 확인
        Long testUseAmount = 700L;

        UserPoint result = pointService.usePoint(TEST_USER, testUseAmount);
        assertThat(result.point()).isEqualTo(300L);
    }

    @DisplayName("0 또는 음수의 포인트는 사용할 수 없습니다.")
    @Test
    void usePointIllegalArgumentTest() {
        Long testAmount = -400L;

        assertThatThrownBy(() -> pointService.usePoint(TEST_USER, testAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 혹은 음수의 포인트 입력");
    }

    @DisplayName("포인트를 조회한다.")
    @Test
    void getUserPointTest() {
        UserPoint result = pointService.getUserPoint(TEST_USER);

        assertThat(result.point()).isEqualTo(1000L);
    }

    @DisplayName("포인트 내역을 조회한다.")
    @Test
    void getHistoryTest() {
        // 포인트 내역을 조회하기 전 포인트를 사용
        pointService.usePoint(TEST_USER, 100L);

        List<PointHistory> result = pointService.getHistory(TEST_USER);
        // setup 에서의 포인트 충전이 1회 동작함으로
        // 사용한 포인트 내역은 result의 1번 인덱스로 조회
        // result 0 > charge (setup)
        // result 1 > use
        assertThat(result.get(1).amount()).isEqualTo(100);
        assertThat(result.get(1).type()).isEqualTo(TransactionType.USE);
    }

    @DisplayName("잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.")
    @Test
    void usePointTest_balance() {
        // setup에서의 초기 잔고 1000 이상의 포인트를 사용
        Long testUseAmount = 1700L;

        assertThatThrownBy(() -> pointService.usePoint(TEST_USER, testUseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.");
    }

    @DisplayName("동시에 여러 건의 포인트 충전 요청이 들어올 경우 순차적으로 처리되어야 합니다.")
    @Test
    void testConcurrentCharge() throws InterruptedException {
        final int numThreads = 5;

        Object lockArea = new Object();
        Thread[] threads = new Thread[numThreads];

        // synchronized 를 통해 해당 블록을 동기화 하고
        // lockArea 객체를 통해 락을 획득한다
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                synchronized (lockArea) {
                    long amount = 100L;
                    pointService.chargePoint(1L, amount);
                    totalAmounts += amount;
                }
            });
            // 동기화된 블록 내의 코드가 실행
            threads[i].start();
        }
        //스레드가 종료될 때까지 기다림
        for (Thread thread : threads) {
            thread.join();
        }
        // 모든 요청이 처리된 후에 사용자의 최종 포인트를 확인하여 순차적으로 처리되었는지 검증
        UserPoint finalUserPoint = pointService.getUserPoint(1L);
        assertThat(finalUserPoint.point()).isEqualTo(1000L + totalAmounts);
    }

    @DisplayName("동시에 여러 건의 포인트 사용 요청이 들어올 경우 순차적으로 처리되어야 합니다.")
    @Test
    void testConcurrentUse() throws InterruptedException {
        final int numThreads = 5;

        Object lockArea = new Object();
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                synchronized (lockArea) {
                    long amount = 100L;
                    pointService.usePoint(1L, amount);
                    totalAmounts += amount;
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        UserPoint finalUserPoint = pointService.getUserPoint(1L);
        assertThat(finalUserPoint.point()).isEqualTo(1000L - totalAmounts);
    }
}
