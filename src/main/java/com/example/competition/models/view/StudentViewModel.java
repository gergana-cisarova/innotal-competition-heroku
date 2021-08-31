package com.example.competition.models.view;

import java.math.BigDecimal;

public class StudentViewModel {

    private String id;
    private String name;
    private String university;
    private String description;
    private String iteration;
    private String area;
    private String evaluations;
    private int orderOfPitch;
    private int panel;
    private BigDecimal averageGrade;

    public StudentViewModel() {

    }

    public String getId() {
        return id;
    }

    public StudentViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getArea() {
        return area;
    }

    public StudentViewModel setArea(String area) {
        this.area = area;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public StudentViewModel setUniversity(String university) {
        this.university = university;
        return this;
    }

    public String getIteration() {
        return iteration;
    }

    public StudentViewModel setIteration(String iteration) {
        this.iteration = iteration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StudentViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getEvaluations() {
        return evaluations;
    }

    public StudentViewModel setEvaluations(String evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public StudentViewModel setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public int getOrderOfPitch() {
        return orderOfPitch;
    }

    public StudentViewModel setOrderOfPitch(int orderOfPitch) {
        this.orderOfPitch = orderOfPitch;
        return this;
    }

    public int getPanel() {
        return panel;
    }

    public StudentViewModel setPanel(int panel) {
        this.panel = panel;
        return this;
    }
}
