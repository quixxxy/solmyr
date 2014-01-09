package com.quixxxy.solmyr.web.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quixxxy.solmyr.web.controller.LoginController;

public class LoginControllerTest {

	@Test
	public void testLogin() {
		LoginController controller = new LoginController();
		assertEquals("solmyr.login.view", controller.login());
	}

}
