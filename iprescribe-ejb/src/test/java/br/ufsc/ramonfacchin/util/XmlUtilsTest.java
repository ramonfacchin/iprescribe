package br.ufsc.ramonfacchin.util;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.entity.Identity;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.enumeration.GenderEnum;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

public class XmlUtilsTest {
	
	@Test
	public void testConvertEntityToXml() throws Exception {
		String xml = IPrescribeStringUtils.parseEntityToXmlString(new MedicalCertificateVO(getSampleMedicalCertificate()));
		System.out.println(xml);
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><medicalCertificate><certifiedId>1</certifiedId><comments>Atesto para os devidos fins que Ramon Facchin nao esta em condicoes de trabalhar no periodo de 19/09 a 22/09</comments><goal>Trabalho</goal><issued>2013-05-02T13:00:00-03:00</issued><issuerLicenseId>1</issuerLicenseId><validity>2013-05-05T13:00:00-03:00</validity></medicalCertificate>";
		Assert.assertEquals(expected, xml);
	}
	
	private MedicalCertificate getSampleMedicalCertificate() {
		MedicalCertificate mc = new MedicalCertificate();
		Identity identity = new Identity();
		identity.setId(1l);
		identity.setName("Master");
		identity.setBirth(IPrescribeDateUtils.createDate(2, Calendar.MAY, 1988, 13, 0, 0, 0));
		identity.setCitizenRegistry("00000000000");
		identity.setGender(GenderEnum.FEMALE);

		User master = new User();
		master.setId(1l);
		master.setUsername("master");
		master.setPassword("Pudim@2012");
		master.setEmail("master@iprescribe.com");
		master.setIdentity(identity);

		MedicalLicense license = new MedicalLicense();
		license.setId(1L);
		license.setCity("City");
		license.setCountry("Country");
		license.setState("State");
		license.setUser(master);
		license.setProfessionalId("123321");
		
		mc.setIssuerLicense(license);
		
		identity = new Identity();
		identity.setId(1l);
		identity.setName("Ramon Facchin");
		identity.setBirth(IPrescribeDateUtils.createDate(2, Calendar.MAY, 1988, 13, 0, 0, 0));
		identity.setCitizenRegistry("05769984945");
		identity.setGender(GenderEnum.MALE);
		mc.setCertified(identity);
		
		mc.setId(1l);
		mc.setActive(true);
		mc.setComments("Atesto para os devidos fins que Ramon Facchin nao esta em condicoes de trabalhar no periodo de 19/09 a 22/09");
		mc.setGoal("Trabalho");
		mc.setIssued(IPrescribeDateUtils.createDate(2, Calendar.MAY, 2013, 13, 0, 0, 0));
		mc.setValidity(IPrescribeDateUtils.createDate(5, Calendar.MAY, 2013, 13, 0, 0, 0));
		return mc;
	}

}
