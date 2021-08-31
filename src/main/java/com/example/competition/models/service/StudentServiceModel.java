package com.example.competition.models.service;

import java.math.BigDecimal;

public class StudentServiceModel {

    private String id;
    private String name;
    private String university;
    private String iteration;
    private String area;
    private String description;
    private int orderOfPitch;
    private int panel;
    private BigDecimal averageGrade;

    public StudentServiceModel() {
    }

    public String getId() {
        return id;
    }

    public StudentServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public StudentServiceModel setUniversity(String university) {
        this.university = university;
        return this;
    }

    public String getArea() {
        return area;
    }

    public StudentServiceModel setArea(String area) {
        this.area = area;
        return this;
    }

    public String getIteration() {
        return iteration;
    }

    public StudentServiceModel setIteration(String iteration) {
        this.iteration = iteration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StudentServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public StudentServiceModel setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public int getOrderOfPitch() {
        return orderOfPitch;
    }

    public StudentServiceModel setOrderOfPitch(int orderOfPitch) {
        this.orderOfPitch = orderOfPitch;
        return this;
    }

    public int getPanel() {
        return panel;
    }

    public StudentServiceModel setPanel(int panel) {
        this.panel = panel;
        return this;
    }
}

