package org.example.architecture.repository.Apply;

import org.example.architecture.domain.Lecture;
import org.example.architecture.domain.LectureApply;
import org.example.architecture.dto.request.LectureApplyRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureApplyJpaRepository extends JpaRepository<LectureApply, Long> {
    Optional<List<LectureApply>> findByUserId(String userId);
    List<LectureApply> findByLectureId(Long lectureId);
    LectureApply save(LectureApply lectureApply);
}
