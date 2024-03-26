package org.example.clearnarch.controller;

import org.example.clearnarch.dto.LectureApplyDTO;
import org.example.clearnarch.service.LectureApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture/apply")
public class LectureApplyController {
    private final LectureApplyService lectureApplyService;

    @Autowired
    public LectureApplyController(LectureApplyService lectureApplyService) {
        this.lectureApplyService = lectureApplyService;
    }

    @PostMapping
    public boolean applyLecture(@RequestBody LectureApplyDTO lectureApplyDTO) {
        return lectureApplyService.applyLecture(lectureApplyDTO);
    }

    @GetMapping("/check")
    public boolean isAlreadyApplied(@RequestParam String userId, @RequestParam Long lectureId) {
        return lectureApplyService.isAlreadyApplied(userId, lectureId);
    }
}