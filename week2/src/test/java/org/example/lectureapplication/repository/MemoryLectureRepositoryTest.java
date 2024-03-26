package org.example.lectureapplication.repository;

import org.example.lectureapplication.domain.ApplyLecture;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MemoryLectureRepositoryTest {
    LectureRepository lectureRepository = new MemoryLectureRepository();

    @Test
    void save() {
        lectureRepository.save("test");
        Optional<ApplyLecture> result = lectureRepository.findApplyList("test");
    }

    @Test
    void findApplyList() {
        lectureRepository.save("test1");
        lectureRepository.save("test2");
        lectureRepository.save("test3");
        lectureRepository.save("test4");
        lectureRepository.save("test");
        Optional<ApplyLecture> result = lectureRepository.findApplyList("test2");
        System.out.println(">>>>>>.");
        System.out.println(result);
    }
}
