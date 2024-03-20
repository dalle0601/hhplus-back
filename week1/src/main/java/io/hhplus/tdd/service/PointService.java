package io.hhplus.tdd.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {
    private final UserPointTable userPointTable;
    private final PointHistoryTable pointHistoryTable;

    public PointService(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {
        this.userPointTable = userPointTable;
        this.pointHistoryTable = pointHistoryTable;
    }

    public UserPoint chargePoint(Long userId, Long amount)  {
        validataionAmount(amount);
        UserPoint originUserPoint = userPointTable.selectById(userId);
        UserPoint userPoint = userPointTable.insertOrUpdate(userId, originUserPoint.point() + amount);
        pointHistoryTable.insert(userId, amount, TransactionType.CHARGE, System.currentTimeMillis());
        return userPoint;
    }

    public UserPoint usePoint(Long userId, Long amount) {
        validataionAmount(amount);
        UserPoint currentUserPoint = userPointTable.selectById(userId);
        if (currentUserPoint.point() < amount) {
            throw new IllegalArgumentException("잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.");
        }
        UserPoint userPoint = userPointTable.insertOrUpdate(userId, currentUserPoint.point() - amount);
        pointHistoryTable.insert(userId, amount, TransactionType.USE, System.currentTimeMillis());
        return userPoint;
    }

    public UserPoint getUserPoint(Long id)  {
        return userPointTable.selectById(id);
    }

    public List<PointHistory> getHistory(Long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }

    public void validataionAmount(Long amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("0 혹은 음수의 포인트 입력");
        }
    }

}
