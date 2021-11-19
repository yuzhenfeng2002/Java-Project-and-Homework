package com.Yuzhen.ExerciseOnline.entity;

import javax.validation.constraints.NotBlank;

public class Knowledge {
    private int id;
    @NotBlank
    private int subject_id;
    @NotBlank
    private String title;
    private String content;

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
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}
