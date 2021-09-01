package com.example.competition.services.impl;

import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.entity.Student;
import com.example.competition.models.entity.enums.Iteration;
import com.example.competition.models.service.StudentServiceModel;
import com.example.competition.models.view.StudentViewModel;
import com.example.competition.repositories.EvaluationRepository;
import com.example.competition.repositories.StudentRepository;
import com.example.competition.services.StudentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final EvaluationRepository evaluationRepository;


    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper, EvaluationRepository evaluationRepository) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public StudentViewModel createStudent(StudentServiceModel studentServiceModel) {
        Student student = modelMapper.map(studentServiceModel, Student.class);
        student.setAverageGrade(BigDecimal.valueOf(0.0));
        studentRepository.save(student);

        return modelMapper.map(student, StudentViewModel.class);
    }

    @Override
    @Transactional
    public LinkedList<StudentViewModel> getAll(Iteration phase) {
        LinkedList <StudentViewModel> all =  studentRepository
                .findAllByOrderByPhase(phase)
                .stream()
                .map(s -> mapStudent(s))
                .collect(Collectors.toCollection(LinkedList::new));
        return all;
    }

    @Override
    @Transactional
    public List<StudentViewModel> getAllByGrade(Iteration phase) {
        List <StudentViewModel> all =  studentRepository
                .findAllByPhaseByTotalGradeDesc(phase)
                .stream()
                .map(s -> mapStudent(s))
                .collect(Collectors.toList());
        return all;
    }

    @Override
    public StudentServiceModel extractStudentModel(String id) {
        Student student = studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(student, StudentServiceModel.class);
    }

    @Override
    public Student findById(String id) {
        return studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteStudent(String id) {
        Student student = studentRepository.getById(id);
        Set<Evaluation> evaluations = student.getEvaluations();
        evaluations.forEach(e -> evaluationRepository.delete(e));
        studentRepository.deleteById(id);
    }

    @Override
    public StudentViewModel getStudentView(String id) {
        Student student = studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return mapStudent(student);
    }

    @Override
    public void addEvaluationToStudent(Evaluation evaluation, String id) {
        Student student = this.studentRepository.getById(id);
        Set<Evaluation> current = student.getEvaluations();
        current.add(evaluation);
        calculateAverage(student);
        this.studentRepository.save(student);

    }

    @Override
    public void removeEvaluationsWhenEditing(Set<Evaluation> toDelete, String id) {
        Student student = this.studentRepository.getById(id);
        Set<Evaluation> current = student.getEvaluations();
        toDelete.forEach(e -> current.remove(e));
        student.setEvaluations(current);
        calculateAverage(student);
        this.studentRepository.save(student);

    }
    private void calculateAverage(Student student) {
        if (student.getEvaluations().size() != 0) {
            double averageTotal = student.getEvaluations()
                    .stream()
                    .mapToDouble(e -> e.getAverageSubGrade().doubleValue())
                    .reduce(0, (a, b) -> a + b);
            double avg = averageTotal / student.getEvaluations().size();
            student.setAverageGrade(BigDecimal.valueOf(avg));
        }
    }

    StudentViewModel mapStudent(Student student) {
        StudentViewModel studentViewModel = modelMapper.map(student, StudentViewModel.class);

        StringBuilder sb = new StringBuilder();
        student.getEvaluations().forEach(e -> {
                    sb.append(String.format("<strong>Evaluation </strong> <br /> Grade 1 'Problem definition': %.2f" +
                            " <br />Grade 2 'Explanation of the Solution': %.2f" +
                            " <br />Grade 3 'Delivering value': %.2f" +
                            " <br />Grade 4 'Pitch Credibility': %.2f" +
                            " <br />Grade 5 'Future plans': %.2f" +
                            " <br />Average grade: %.2f" +
                            " <br />Feedback: %s <br />", e.getFirstGrade(), e.getSecondGrade(), e.getThirdGrade(), e.getFourthGrade(), e.getFifthGrade(), e.getAverageSubGrade(), e.getFeedback()));
                }
        );
        studentViewModel.setAverageGrade(student.getAverageGrade());
        studentViewModel.setEvaluations(sb.toString().trim());

        return studentViewModel;
    }

}
