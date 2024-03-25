package org.example.lectureapplication.repository;

import org.example.lectureapplication.domain.ApplyLecture;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class MemoryLectureRepository implements LectureRepository{
    private static final Map<Long, ApplyLecture> store = new ConcurrentHashMap<>();
    private static long sequence = 0;

    @Override
    public ApplyLecture save(String userId) {
        ApplyLecture applyLecture = new ApplyLecture(++sequence, userId, LocalDateTime.now(), "신청완료");
        store.put(applyLecture.getId(), applyLecture);
        return applyLecture;
    }

    @Override
    public Optional<ApplyLecture> findApplyList(String userId) {
        return store.values().stream().filter(list -> list.getUserId().equals(userId)).findFirst();
    }

    @Override
    public List<ApplyLecture> findApplyListAll() {
        return new ArrayList<>(store.values());
    }
}
