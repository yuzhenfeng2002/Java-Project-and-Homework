package com.Yuzhen.eRestaurant.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class BUser {
	private Integer id;
	private String desknum;
	private String code;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesknum() {
		return desknum;
	}
	public void setDesknum(String desknum) {
		this.desknum = desknum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
