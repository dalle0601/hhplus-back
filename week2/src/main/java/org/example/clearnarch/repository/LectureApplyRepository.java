package org.example.clearnarch.repository;

import org.example.clearnarch.domain.LectureApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureApplyRepository extends JpaRepository<LectureApply, Long> {
    boolean existsByUserIdAndLectureId(String userId, Long lectureId);

}
