package com.example.tplab2.login_management;

import com.example.tplab2.data_acess_management.DataAcessor;
import com.example.tplab2.login_management.User.UserType;

import java.sql.SQLException;

public class LoginController {
	private final LoginForm m_loginForm;
	private final DataAcessor m_dataAccessor;

	public LoginController(/*Stage stage,*/ DataAcessor dataAcessor) {
		m_loginForm = new LoginForm(/*stage,*/ this);
		m_dataAccessor = dataAcessor;
	}

	public UserType login(String nickname, String password) throws SQLException {
		return m_dataAccessor.defineUserType(new User(nickname, password));
	}

	public void start() {
		m_loginForm.show();
	}

	public boolean databaseRollback() {
		return m_dataAccessor.rollback();
	}

	public UserType getLastUserType() {
		return m_loginForm.getLastUserType();
	}
}