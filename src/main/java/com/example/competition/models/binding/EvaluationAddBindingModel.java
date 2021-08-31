package com.example.competition.models.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class EvaluationAddBindingModel {

    @DecimalMax(value="5.0")
    @DecimalMin(value="0.0")
    private BigDecimal firstGrade;
    @DecimalMax(value="5.0")
    @DecimalMin(value="0.0")
    private BigDecimal secondGrade;
    @DecimalMax(value="5.0")
    @DecimalMin(value="0.0")
    private BigDecimal thirdGrade;
    @DecimalMax(value="5.0")
    @DecimalMin(value="0.0")
    private BigDecimal fourthGrade;
    @DecimalMax(value="5.0")
    @DecimalMin(value="0.0")
    private BigDecimal fifthGrade;

    @NotEmpty
    @Size(min = 5, max = 1500)
    private String feedback;

    public EvaluationAddBindingModel() {
    }

    public BigDecimal getFirstGrade() {
        return firstGrade;
    }

    public EvaluationAddBindingModel setFirstGrade(BigDecimal firstGrade) {
        this.firstGrade = firstGrade;
        return this;
    }

    public BigDecimal getSecondGrade() {
        return secondGrade;
    }

    public EvaluationAddBindingModel setSecondGrade(BigDecimal secondGrade) {
        this.secondGrade = secondGrade;
        return this;
    }

    public BigDecimal getThirdGrade() {
        return thirdGrade;
    }

    public EvaluationAddBindingModel setThirdGrade(BigDecimal thirdGrade) {
        this.thirdGrade = thirdGrade;
        return this;
    }

    public BigDecimal getFourthGrade() {
        return fourthGrade;
    }

    public EvaluationAddBindingModel setFourthGrade(BigDecimal fourthGrade) {
        this.fourthGrade = fourthGrade;
        return this;
    }

    public BigDecimal getFifthGrade() {
        return fifthGrade;
    }

    public EvaluationAddBindingModel setFifthGrade(BigDecimal fifthGrade) {
        this.fifthGrade = fifthGrade;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public EvaluationAddBindingModel setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }


}
