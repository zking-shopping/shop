package com.shopping.web.form;

public class RegisterForm extends FormFather{
	private String username;
	private String password;
	private String phoneNumber;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String toString() {
		return "RegisterForm [username=" + username + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
}
