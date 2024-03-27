package org.example.architecture.repository;

import org.example.architecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByLectureId(Long lectureId);
}
