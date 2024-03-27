package org.example.architecture.repository;

import org.example.architecture.domain.LectureApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureApplyRepository extends JpaRepository<LectureApply, Long> {
    Optional<List<LectureApply>> findByUserId(String userId);
    List<LectureApply> findByLectureId(Long lectureId);
}
