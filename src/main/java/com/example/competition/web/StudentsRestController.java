package com.example.competition.web;

import com.example.competition.models.entity.enums.Iteration;
import com.example.competition.models.view.StudentViewModel;
import com.example.competition.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/results")
@RestController
@CrossOrigin(origins = "https://innotal-competition.herokuapp.com/")
public class StudentsRestController {

    private final ModelMapper modelMapper;
    private final StudentService studentService;

    public StudentsRestController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api-reg")
    public ResponseEntity<List<StudentViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(studentService.getAllByGrade(Iteration.Regular));
    }
    @GetMapping("/api-finals")
    public ResponseEntity<List<StudentViewModel>> findAllFinalists() {
        return ResponseEntity
                .ok()
                .body(studentService.getAllByGrade(Iteration.Finalist));
    }

}
