package org.example.lectureapplication.repository;

import jakarta.persistence.EntityManager;
import org.example.lectureapplication.domain.ApplyLecture;

import java.util.List;
import java.util.Optional;

public class JpaLectureRepository implements LectureRepository{
    private final EntityManager em;

    public JpaLectureRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ApplyLecture save(String userId) {
        return null;
    }

    @Override
    public Optional<ApplyLecture> findApplyList(String userId) {
        return Optional.empty();
    }

    @Override
    public List<ApplyLecture> findApplyListAll() {
        return null;
    }
}
