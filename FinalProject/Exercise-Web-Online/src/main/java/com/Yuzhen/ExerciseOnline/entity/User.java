package com.Yuzhen.ExerciseOnline.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank(message="邮箱不能为空！")
    @Email(message="邮箱格式错误！")
    private String email;
    @NotBlank(message="密码不能为空！")
    @Length(min=6, max=20, message="密码长度必须为6到20个字符！")
    private String password;
    private String password2;
    private String username;
    private String university;
    private String ID;
    private String validateCode;
    private byte usertype;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public byte getUsertype() {
        return usertype;
    }
    public void setUsertype(byte usertype) {
        this.usertype = usertype;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getValidateCode() {
        return validateCode;
    }
    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
