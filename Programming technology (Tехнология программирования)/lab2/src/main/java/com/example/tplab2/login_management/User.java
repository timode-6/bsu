package com.example.tplab2.login_management;

public class User {
	private final String m_nickname;
	private final String m_password;
	public enum UserType {
		MANAGER,
		RECEPTIONIST,
		ADMINISTRATOR,
		UNKNOWN
	}

	public User(String nickname, String password){
		m_nickname = nickname;
		m_password = password;
	}

	public String getNickname() {
		return m_nickname;
	}

	public String getPassword() {
		return m_password;
	}

}