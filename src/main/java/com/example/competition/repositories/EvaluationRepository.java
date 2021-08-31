package com.example.competition.repositories;

import com.example.competition.models.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository  extends JpaRepository<Evaluation, String> {

    List<Evaluation> findAllByEvaluator_IdAndEvaluated_Id(String evaluatorId, String evaluatedId);
}
