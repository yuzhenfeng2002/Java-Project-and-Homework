package com.Yuzhen.ExerciseOnline.entity;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class Answer {
    private int id;
    private int exercise_id;
    private String answer;
    private int is_right;
    private String user_email;
    private Timestamp create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIs_right() {
        return is_right;
    }

    public void setIs_right(int is_right) {
        this.is_right = is_right;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}