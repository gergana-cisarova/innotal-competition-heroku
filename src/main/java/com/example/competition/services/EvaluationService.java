package com.example.competition.services;

import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.service.EvaluationServiceModel;
import com.example.competition.models.view.EvaluationViewModel;

import java.util.List;

public interface EvaluationService {
    void createEvaluation(EvaluationServiceModel evaluationServiceModel);

    List<EvaluationViewModel> findEvaluationByEvaluator(String evaluatorId, String studentId);

}
