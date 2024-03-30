package org.example.architecture.repository.Apply;

import lombok.RequiredArgsConstructor;
import org.example.architecture.domain.Lecture;
import org.example.architecture.domain.LectureApply;
import org.example.architecture.dto.request.LectureApplyRequestDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureApplyRepositoryImpl implements LectureApplyRepository{
    private final LectureApplyJpaRepository lectureApplyJpaRepository;

    @Override
    public Optional<List<LectureApply>> getApplyListByUserId(String userId) {
        return lectureApplyJpaRepository.findByUserId(userId);
    }

    @Override
    public List<LectureApply> getApplyListByLectureId(Long lectureId) {
        return lectureApplyJpaRepository.findByLectureId(lectureId);
    }

    @Override
    public LectureApply insertApplyLecture(LectureApply lectureApply) {
        return lectureApplyJpaRepository.save(lectureApply);
    }
}
