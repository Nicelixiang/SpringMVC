package com.example;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	@NotEmpty(message="姓名不能为空")
	private String name;
	@NotNull
	@Max(value = 80,message="年龄不能超过80")
	@Min(value = 10,message="年龄不能小于10")
	private Integer age;
	@NotEmpty
	@Length(max=11,min=7)
	private String phoneNum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", phoneNum=" + phoneNum + "]";
	}
	public User(String name, Integer age, String phoneNum) {
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
	}
	public User() {
	}
	
}
