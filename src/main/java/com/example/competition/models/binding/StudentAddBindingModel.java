package com.example.competition.models.binding;


import javax.validation.constraints.*;

public class StudentAddBindingModel {

    @NotEmpty
    @Size(min = 3, max = 250)
    private String name;
    @NotNull
    private String university;
    @NotNull
    private String iteration;
    @NotNull
    @Size(min = 5, max = 50)
    private String area;
    @NotEmpty
    @Size(min = 5, max = 150)
    private String description;
    @Min(1)
    @Max(100)
    private int orderOfPitch;
    @Min(1)
    @Max(2)
    private int panel;

    public StudentAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public StudentAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getArea() {
        return area;
    }

    public StudentAddBindingModel setArea(String area) {
        this.area = area;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public StudentAddBindingModel setUniversity(String university) {
        this.university = university;
        return this;
    }

    public String getIteration() {
        return iteration;
    }

    public StudentAddBindingModel setIteration(String iteration) {
        this.iteration = iteration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StudentAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getOrderOfPitch() {
        return orderOfPitch;
    }

    public StudentAddBindingModel setOrderOfPitch(int orderOfPitch) {
        this.orderOfPitch = orderOfPitch;
        return this;
    }

    public int getPanel() {
        return panel;
    }

    public StudentAddBindingModel setPanel(int panel) {
        this.panel = panel;
        return this;
    }
}
