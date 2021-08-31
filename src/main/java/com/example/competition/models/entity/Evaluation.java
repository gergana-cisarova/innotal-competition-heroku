package com.example.competition.models.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "evaluations")
public class Evaluation extends BaseEntity {

    @Column(nullable = false)
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private BigDecimal firstGrade;
    @Column(nullable = false)
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private BigDecimal secondGrade;
    @Column(nullable = false)
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private BigDecimal thirdGrade;
    @Column(nullable = false)
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private BigDecimal fourthGrade;
    @Column(nullable = false)
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private BigDecimal fifthGrade;
    @Column(columnDefinition = "Text", nullable = false)
    private BigDecimal averageSubGrade;
    @Column(columnDefinition = "Text", nullable = false)
    private String feedback;

    @ManyToOne
    private Student evaluated;

    @ManyToOne
    private UserEntity evaluator;

    public Evaluation() {
    }

    public Student getEvaluated() {
        return evaluated;
    }

    public Evaluation setEvaluated(Student evaluated) {
        this.evaluated = evaluated;
        return this;
    }

    public BigDecimal getFirstGrade() {
        return firstGrade;
    }

    public Evaluation setFirstGrade(BigDecimal firstGrade) {
        this.firstGrade = firstGrade;
        return this;
    }

    public BigDecimal getSecondGrade() {
        return secondGrade;
    }

    public Evaluation setSecondGrade(BigDecimal secondGrade) {
        this.secondGrade = secondGrade;
        return this;
    }

    public BigDecimal getThirdGrade() {
        return thirdGrade;
    }

    public Evaluation setThirdGrade(BigDecimal thirdGrade) {
        this.thirdGrade = thirdGrade;
        return this;
    }

    public BigDecimal getFourthGrade() {
        return fourthGrade;
    }

    public Evaluation setFourthGrade(BigDecimal fourthGrade) {
        this.fourthGrade = fourthGrade;
        return this;
    }

    public BigDecimal getFifthGrade() {
        return fifthGrade;
    }

    public Evaluation setFifthGrade(BigDecimal fifthGrade) {
        this.fifthGrade = fifthGrade;
        return this;
    }

    public BigDecimal getAverageSubGrade() {
        return averageSubGrade;
    }

    public Evaluation setAverageSubGrade(BigDecimal averageSubGrade) {
        this.averageSubGrade = averageSubGrade;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public Evaluation setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public UserEntity getEvaluator() {
        return evaluator;
    }

    public Evaluation setEvaluator(UserEntity evaluator) {
        this.evaluator = evaluator;
        return this;
    }
}
