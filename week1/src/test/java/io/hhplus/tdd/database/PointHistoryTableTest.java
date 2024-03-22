package io.hhplus.tdd.database;

import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PointHistoryTableTest {
    @Mock
    PointHistoryTable pointHistoryTable;
    long TEST_USER = 1;

    /*
        insert > PointHistory
        selectAllByUserId > List<PointHistory>
     */
    @DisplayName("포인트 내역 추가 > insert")
    @Test
    void insertTest() {
        PointHistory pointHistory = new PointHistory(1, TEST_USER, 1000, TransactionType.CHARGE, System.currentTimeMillis());
        when(pointHistoryTable.insert(anyLong(), anyLong(), (TransactionType)any(), anyLong())).thenReturn(pointHistory);

        PointHistory result = pointHistoryTable.insert( TEST_USER, 1000, TransactionType.CHARGE, System.currentTimeMillis());

        assertAll(
                ()-> assertEquals(pointHistory.id(), result.id()),
                ()-> assertEquals(pointHistory.amount(), result.amount()),
                () -> assertEquals(pointHistory.type(), result.type())
        );
    }

    @DisplayName("포인트 내역 조회 > selectAllByUserId")
    @Test
    void selectAllByUserIdTest() {
        List<PointHistory> pointHistoryList = new ArrayList<>();
        pointHistoryList.add(new PointHistory(1, TEST_USER, 333, TransactionType.CHARGE, System.currentTimeMillis() ));
        pointHistoryList.add(new PointHistory(2, TEST_USER, 222, TransactionType.USE, System.currentTimeMillis() ));
        when(pointHistoryTable.selectAllByUserId(anyLong())).thenReturn(pointHistoryList);

        List<PointHistory> result = pointHistoryTable.selectAllByUserId(TEST_USER);

        assertAll(
                ()-> assertEquals(result.size(), 2),
                ()-> assertEquals(result.get(0).type(), TransactionType.CHARGE),
                () -> assertEquals(result.get(1).type(), TransactionType.USE)
        );
    }
}
