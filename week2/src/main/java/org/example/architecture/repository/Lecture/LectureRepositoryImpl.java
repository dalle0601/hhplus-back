package org.example.architecture.repository.Lecture;

import lombok.RequiredArgsConstructor;
import org.example.architecture.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository{
    private final LectureJpaRepository lectureJpaRepository;
    @Override
    public Lecture getLectureInfo(Long lectureId) {
        return lectureJpaRepository.findByLectureId(lectureId);
    }
}
