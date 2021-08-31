package com.example.competition.services;

import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.entity.Student;
import com.example.competition.models.entity.enums.Iteration;
import com.example.competition.models.service.StudentServiceModel;
import com.example.competition.models.view.StudentViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface StudentService {
    StudentViewModel createStudent(StudentServiceModel studentServiceModel);

    LinkedList<StudentViewModel> getAll(Iteration phase);
    List<StudentViewModel> getAllByGrade(Iteration phase);
    StudentServiceModel extractStudentModel(String id);

    Student findById(String id);

    void deleteStudent(String id);

    StudentViewModel getStudentView(String id);

    void addEvaluationToStudent(Evaluation evaluation, String id);

    void removeEvaluationsWhenEditing(Set<Evaluation> toDelete, String id);

}
