package org.example.architecture.controller;

import org.example.architecture.dto.request.LectureApplyRequestDTO;
import org.example.architecture.dto.response.LectureApplyResponseDTO;
import org.example.architecture.dto.response.LectureApplyStatusResponseDTO;
import org.example.architecture.service.LectureApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LectureApplyController {

    private final LectureApplyService lectureApplyService;

    @Autowired
    public LectureApplyController(LectureApplyService lectureApplyService){
        this.lectureApplyService = lectureApplyService;
    }

    @PostMapping("/apply-lecture")
    public ResponseEntity<LectureApplyResponseDTO> applyLecture(@RequestBody LectureApplyRequestDTO requestDTO) {
        LectureApplyResponseDTO responseDTO = lectureApplyService.applyLecture(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/apply-lecture")
    public ResponseEntity<LectureApplyStatusResponseDTO> getApplyLectureStatusByUserId(@RequestParam String userId) {
        LectureApplyStatusResponseDTO responseDTO = lectureApplyService.getApplyLectureStatusByUserId(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }}
