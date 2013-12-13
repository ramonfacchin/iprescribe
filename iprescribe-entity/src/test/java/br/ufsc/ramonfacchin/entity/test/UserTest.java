package br.ufsc.ramonfacchin.entity.test;

import junit.framework.Assert;

import org.junit.Test;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;

public class UserTest {

	@Test
	public void testEncryptPassword() throws Exception {
		String password = "Pudim@2012";
		String encrypted = IPrescribeStringUtils.encryptPassword(password);
		Assert.assertTrue(IPrescribeStringUtils.checkPassword(password, encrypted));
		Assert.assertFalse(IPrescribeStringUtils.checkPassword("Pudim@2013", encrypted));
	}

}
