package com.example.competition.models.service;

import com.example.competition.models.entity.Student;
import com.example.competition.models.entity.UserEntity;
import com.example.competition.models.view.EvaluationViewModel;

import java.math.BigDecimal;

public class EvaluationServiceModel {

    private String id;
    private BigDecimal university;
    private BigDecimal firstGrade;
    private BigDecimal secondGrade;
    private BigDecimal thirdGrade;
    private BigDecimal fourthGrade;
    private BigDecimal fifthGrade;
    private BigDecimal averageSubGrade;
    private String feedback;
    private StudentServiceModel evaluated;
    private UserRegistrationServiceModel evaluator;

    public EvaluationServiceModel() {
    }

    public String getId() {
        return id;
    }

    public EvaluationServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public BigDecimal getUniversity() {
        return university;
    }

    public EvaluationServiceModel setUniversity(BigDecimal university) {
        this.university = university;
        return this;
    }

    public BigDecimal getFirstGrade() {
        return firstGrade;
    }

    public EvaluationServiceModel setFirstGrade(BigDecimal firstGrade) {
        this.firstGrade = firstGrade;
        return this;
    }

    public BigDecimal getSecondGrade() {
        return secondGrade;
    }

    public EvaluationServiceModel setSecondGrade(BigDecimal secondGrade) {
        this.secondGrade = secondGrade;
        return this;
    }

    public BigDecimal getThirdGrade() {
        return thirdGrade;
    }

    public EvaluationServiceModel setThirdGrade(BigDecimal thirdGrade) {
        this.thirdGrade = thirdGrade;
        return this;
    }

    public BigDecimal getFourthGrade() {
        return fourthGrade;
    }

    public EvaluationServiceModel setFourthGrade(BigDecimal fourthGrade) {
        this.fourthGrade = fourthGrade;
        return this;
    }

    public BigDecimal getFifthGrade() {
        return fifthGrade;
    }

    public EvaluationServiceModel setFifthGrade(BigDecimal fifthGrade) {
        this.fifthGrade = fifthGrade;
        return this;
    }

    public BigDecimal getAverageSubGrade() {
        return averageSubGrade;
    }

    public EvaluationServiceModel setAverageSubGrade(BigDecimal averageSubGrade) {
        this.averageSubGrade = averageSubGrade;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public EvaluationServiceModel setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public StudentServiceModel getEvaluated() {
        return evaluated;
    }

    public EvaluationServiceModel setEvaluated(StudentServiceModel evaluated) {
        this.evaluated = evaluated;
        return this;
    }

    public UserRegistrationServiceModel getEvaluator() {
        return evaluator;
    }

    public EvaluationServiceModel setEvaluator(UserRegistrationServiceModel evaluator) {
        this.evaluator = evaluator;
        return this;
    }
}
