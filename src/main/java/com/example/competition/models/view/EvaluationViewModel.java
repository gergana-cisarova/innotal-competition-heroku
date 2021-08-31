package com.example.competition.models.view;

import java.math.BigDecimal;

public class EvaluationViewModel {

    private String id;
    private BigDecimal university;
    private BigDecimal firstGrade;
    private BigDecimal secondGrade;
    private BigDecimal thirdGrade;
    private BigDecimal fourthGrade;
    private BigDecimal fifthGrade;
    private BigDecimal averageSubGrade;
    private String feedback;
    private String evaluated;

    public EvaluationViewModel() {
    }

    public String getId() {
        return id;
    }

    public EvaluationViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public BigDecimal getUniversity() {
        return university;
    }

    public EvaluationViewModel setUniversity(BigDecimal university) {
        this.university = university;
        return this;
    }

    public BigDecimal getFirstGrade() {
        return firstGrade;
    }

    public EvaluationViewModel setFirstGrade(BigDecimal firstGrade) {
        this.firstGrade = firstGrade;
        return this;
    }

    public BigDecimal getSecondGrade() {
        return secondGrade;
    }

    public EvaluationViewModel setSecondGrade(BigDecimal secondGrade) {
        this.secondGrade = secondGrade;
        return this;
    }

    public BigDecimal getThirdGrade() {
        return thirdGrade;
    }

    public EvaluationViewModel setThirdGrade(BigDecimal thirdGrade) {
        this.thirdGrade = thirdGrade;
        return this;
    }

    public BigDecimal getFourthGrade() {
        return fourthGrade;
    }

    public EvaluationViewModel setFourthGrade(BigDecimal fourthGrade) {
        this.fourthGrade = fourthGrade;
        return this;
    }

    public BigDecimal getFifthGrade() {
        return fifthGrade;
    }

    public EvaluationViewModel setFifthGrade(BigDecimal fifthGrade) {
        this.fifthGrade = fifthGrade;
        return this;
    }

    public BigDecimal getAverageSubGrade() {
        return averageSubGrade;
    }

    public EvaluationViewModel setAverageSubGrade(BigDecimal averageSubGrade) {
        this.averageSubGrade = averageSubGrade;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public EvaluationViewModel setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public String getEvaluated() {
        return evaluated;
    }

    public EvaluationViewModel setEvaluated(String evaluated) {
        this.evaluated = evaluated;
        return this;
    }
}
