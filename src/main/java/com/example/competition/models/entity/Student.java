package com.example.competition.models.entity;

import com.example.competition.models.entity.enums.Iteration;
import com.example.competition.models.entity.enums.University;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String area;
    @Enumerated(EnumType.STRING)
    @NotNull
    private University university;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Iteration iteration;

    @Column(columnDefinition = "Text", nullable = false)
    private String description;

    @OneToMany(mappedBy = "evaluated", targetEntity = Evaluation.class, cascade = CascadeType.ALL)
    private Set<Evaluation> evaluations = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal averageGrade = new BigDecimal("0");

    @Column(nullable = false)
    private int orderOfPitch;
    @Column(nullable = false)
    private int panel;


    public Student() {
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getArea() {
        return area;
    }

    public Student setArea(String area) {
        this.area = area;
        return this;
    }

    public University getUniversity() {
        return university;
    }

    public Student setUniversity(University university) {
        this.university = university;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Student setDescription(String description) {
        this.description = description;
        return this;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public Student setIteration(Iteration iteration) {
        this.iteration = iteration;
        return this;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public Student setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public Student setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public int getOrderOfPitch() {
        return orderOfPitch;
    }

    public Student setOrderOfPitch(int orderOfPitch) {
        this.orderOfPitch = orderOfPitch;
        return this;
    }

    public int getPanel() {
        return panel;
    }

    public Student setPanel(int panel) {
        this.panel = panel;
        return this;
    }
}

