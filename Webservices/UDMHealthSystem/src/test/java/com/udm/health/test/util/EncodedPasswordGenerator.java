package com.udm.health.test.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/appCtx-udm-root.xml")
public class EncodedPasswordGenerator {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void generatePassword() {
		CharSequence unencodedPassword = "test";
		System.out.println(passwordEncoder.encode(unencodedPassword));
	}
	
}
