package br.ufsc.ramonfacchin.common.validation.validator.test;

import junit.framework.Assert;

import org.junit.Test;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;

public class IPrescribeStringUtilsTest {

	@Test
	public void testNonceGenerator() {
		String random = IPrescribeStringUtils.generateRandomString(20);
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
		Assert.assertNotSame(random, random = IPrescribeStringUtils.generateRandomString(20));
	}
	
	@Test
	public void testHashers() throws Exception {
		String test = "some random text for testing purposes";
		String testExpectedSha1 = "ea9b187145091f701d5a4eb29736837cdee2232b";
		String testExpectedSha256 = "3d67081f0858b2edd0e940c200e5ec137b1f8891b76bedd5d6710cae8c1a8fe6";
		String testExpectedSha512 = "d279fa1f883c715e7d3109c28c63b36003e88ff323aa2def2bd91e618f17fc266211daf87ae11764daf3ee893ea6e2439000c668008e946e0e69a2cd1e19a653";
		Assert.assertEquals(testExpectedSha1, IPrescribeStringUtils.sha1Hex(test.getBytes()));
		Assert.assertEquals(testExpectedSha256, IPrescribeStringUtils.sha256Hex(test.getBytes()));
		Assert.assertEquals(testExpectedSha512, IPrescribeStringUtils.sha512Hex(test.getBytes()));
	}

}
