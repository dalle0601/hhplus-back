package org.example.lectureapplication.repository;

import org.example.lectureapplication.domain.ApplyLecture;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface LectureRepository {
    ApplyLecture save(String userId);
    Optional<ApplyLecture> findApplyList(String userId);
    List<ApplyLecture> findApplyListAll();
}
