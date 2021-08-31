package com.example.competition.services.impl;

import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.entity.Student;
import com.example.competition.models.entity.UserEntity;
import com.example.competition.models.service.EvaluationServiceModel;
import com.example.competition.models.view.EvaluationViewModel;
import com.example.competition.repositories.EvaluationRepository;
import com.example.competition.services.EvaluationService;
import com.example.competition.services.StudentService;
import com.example.competition.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements EvaluationService {


    private final EvaluationRepository evaluationRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final StudentService studentService;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, ModelMapper modelMapper, UserService userService, StudentService studentService) {
        this.evaluationRepository = evaluationRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.studentService = studentService;
    }


    @Override
    public void createEvaluation(EvaluationServiceModel evaluationServiceModel) {
        Evaluation evaluation = modelMapper.map(evaluationServiceModel, Evaluation.class);
        BigDecimal a = evaluationServiceModel.getFirstGrade();
        BigDecimal b = evaluationServiceModel.getSecondGrade();
        BigDecimal c = evaluationServiceModel.getThirdGrade();
        BigDecimal d = evaluationServiceModel.getFourthGrade();
        BigDecimal e = evaluationServiceModel.getFifthGrade();
        BigDecimal average = (a.add(b).add(c).add(d).add(e)).divide(BigDecimal.valueOf(5));
        evaluation.setAverageSubGrade(average);

        UserEntity evaluator = this.userService.findByUsername(evaluationServiceModel.getEvaluator().getUsername());
        Student evaluated = this.studentService.findById(evaluationServiceModel.getEvaluated().getId());
        evaluation.setEvaluated(evaluated);
        evaluation.setEvaluator(evaluator);
        Set<Evaluation> toBeDeleted = evaluator.getEvaluations()
                .stream()
                .filter(eval -> eval.getEvaluated().getId()==(evaluated.getId()))
                .collect(Collectors.toSet());
        userService.removeEvaluationsWhenEditing(toBeDeleted, evaluationServiceModel.getEvaluator().getId());
        studentService.removeEvaluationsWhenEditing(toBeDeleted, evaluationServiceModel.getEvaluated().getId());
        toBeDeleted.stream()
                .forEach(ev -> evaluationRepository.delete(ev));
        evaluationRepository.save(evaluation);

        //
        Set<Evaluation> check1 = evaluated.getEvaluations();
        Set<Evaluation> check2 = evaluator.getEvaluations();
        //

        System.out.println();
        userService.setEvaluationsOfEvaluator(evaluation, evaluationServiceModel.getEvaluator().getId());
        studentService.addEvaluationToStudent(evaluation, evaluationServiceModel.getEvaluated().getId());
        Set<Evaluation> check3 = evaluated.getEvaluations();
        Set<Evaluation> check4 = evaluator.getEvaluations();
        System.out.println();

    }

    @Override
    public List<EvaluationViewModel> findEvaluationByEvaluator(String evaluatorId, String studentId) {
      List<Evaluation> result = evaluationRepository.findAllByEvaluator_IdAndEvaluated_Id(evaluatorId, studentId);

      return result
              .stream()
              .map(e ->  modelMapper.map(e, EvaluationViewModel.class))
              .collect(Collectors.toList());
    }
}
