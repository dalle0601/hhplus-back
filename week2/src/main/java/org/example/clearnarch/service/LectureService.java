package org.example.clearnarch.service;

import org.example.clearnarch.domain.Lecture;
import org.example.clearnarch.dto.LectureDTO;
import org.example.clearnarch.repository.LectureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void saveLecture(LectureDTO lectureDTO) {
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(lectureDTO, lecture);
        lectureRepository.save(lecture);
    }

    public LectureDTO findLectureById(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElse(null);
        if (lecture != null) {
            LectureDTO lectureDTO = new LectureDTO(lecture.getLectureName(), lecture.getLectureStartTime(), lecture.getMaxApplyNumber(), lecture.getCurrentApplyNumber());
            BeanUtils.copyProperties(lecture, lectureDTO);
            return lectureDTO;
        }
        return null;
    }
}
