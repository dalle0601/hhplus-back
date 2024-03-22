package io.hhplus.tdd.database;

import io.hhplus.tdd.point.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserPointTableTest {
    @Mock
    UserPointTable userPointTable;

    long TEST_USER = 1;

    /*
        selectById > UserPoint
        insertOrUpdate > UserPoint
     */

    @DisplayName("포인트 조회 > selectById")
    @Test
    void selectByIdTest () {
        UserPoint userPoint = new UserPoint(TEST_USER, 1000, System.currentTimeMillis());
        when(userPointTable.selectById(TEST_USER)).thenReturn(userPoint);   // mock 객체의 동작을 정의

        UserPoint result = userPointTable.selectById(TEST_USER);

        assertAll(
                ()->assertEquals(userPoint.point(), result.point()),
                ()->assertEquals(userPoint.id(), result.id())
        );
    }

    @DisplayName("포인트 수정 > insertOrUpdate")
    @Test
    void insertOrUpdate() {
        long updatePoint = 1999;
        UserPoint userPoint = new UserPoint(TEST_USER, updatePoint, System.currentTimeMillis());
        when(userPointTable.insertOrUpdate(TEST_USER, updatePoint)).thenReturn(userPoint);

        UserPoint result = userPointTable.insertOrUpdate(TEST_USER, updatePoint);

        assertEquals(updatePoint, result.point());

    }

}
