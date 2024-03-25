package org.example.lectureapplication.controller;

import org.example.lectureapplication.domain.ApplyLecture;
import org.example.lectureapplication.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("applications")
    public ApplyLecture applyLectureController(@RequestBody ApplyLecture applyLecture) {
        return lectureService.applyLecture(applyLecture.getUserId());
    }

    @GetMapping("applications/{userId}")
    public String point(@PathVariable String userId) {
        return lectureService.getApplyList(userId);
    }
}
