package org.example.architecture.repository.Lecture;

import org.example.architecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
    Lecture findByLectureId(Long lectureId);
}
