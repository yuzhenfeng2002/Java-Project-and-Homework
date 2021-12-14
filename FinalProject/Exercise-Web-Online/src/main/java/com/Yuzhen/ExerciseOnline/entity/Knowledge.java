package com.Yuzhen.ExerciseOnline.entity;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Knowledge {
    private int id;
    private int subject_id;
    @NotBlank
    private String subject_name;
    @NotBlank
    private String title;
    private String content;
    private List<Integer> dependency;
    private double progress;
    private boolean isDependent;

    public boolean isDependent() {
        return isDependent;
    }

    public void setDependent(boolean dependent) {
        isDependent = dependent;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public List<Integer> getDependency() {
        return dependency;
    }

    public void setDependency(List<Integer> dependency) {
        this.dependency = dependency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
