package org.example.clearnarch.controller;

import org.example.clearnarch.dto.LectureDTO;
import org.example.clearnarch.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/{id}")
    public LectureDTO findLectureById(@PathVariable Long id) {
        return lectureService.findLectureById(id);
    }
}