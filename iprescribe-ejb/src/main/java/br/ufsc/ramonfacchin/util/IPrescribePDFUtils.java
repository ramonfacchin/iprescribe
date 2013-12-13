package br.ufsc.ramonfacchin.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class IPrescribePDFUtils {
	
	public static final Font FONT_TITLE = new Font(Font.HELVETICA, 18, Font.BOLD);
	public static final Font FONT_SUBTITLE = new Font(Font.HELVETICA, 14, Font.BOLDITALIC);
	public static final Font FONT_TEXT = new Font(Font.HELVETICA, 12);
	public static final Font FONT_TEXT_EMPHASYS = new Font(Font.HELVETICA,12,Font.ITALIC);
	public static final Font FONT_FIELD_LABEL = new Font(Font.HELVETICA,12,Font.BOLD);
	public static final Font FONT_FIELD_VALUE = new Font(Font.HELVETICA, 12);
	public static final Font FONT_MD_NAME = new Font(Font.HELVETICA,10,Font.BOLD);;
	public static final Font FONT_MD_SPECIALTY = new Font(Font.HELVETICA,10,Font.ITALIC);
	public static final Font FONT_MD_PROFESSIONAL_ID = new Font(Font.HELVETICA,10,Font.ITALIC);
	public static final Font FONT_FOOTER = new Font(Font.HELVETICA,8);
	public static final Font FONT_AUTHCHECK = new Font(Font.COURIER,8);
	
	public static final String METADATA_TITLE = "METADATA-TITLE";
	public static final String METADATA_SUBJECT = "METADATA-SUBJECT";
	public static final String METADATA_KEYWORDS = "METADATA-KEYWORDS";
	public static final String METADATA_AUTHOR = "METADATA-AUTHOR";
	public static final String METADATA_CREATOR = "METADATA-CREATOR";
	public static final String METADATA_PAGESIZE = "METADATA-PAGESIZE";
	
	public static final String CERTIFIED_LABEL = "CERTIFIED-LABEL";
	public static final String CERTIFIED_VALUE = "CERTIFIED-VALUE";
	public static final String PURPOSE_LABEL = "PURPOSE-LABEL";
	public static final String PURPOSE_VALUE = "PURPOSE-VALUE";
	public static final String ISSUED_LABEL = "ISSUED-LABEL";
	public static final String ISSUED_VALUE = "ISSUED-VALUE";
	public static final String EXPIRES_LABEL = "EXPIRES-LABEL";
	public static final String EXPIRES_VALUE = "EXPIRES-VALUE";
	public static final String PROGNOSYS_LABEL = "PROGNOSYS-LABEL";
	public static final String PROGNOSYS_VALUE = "PROGNOSYS-VALUE";
	public static final String DIAGNOSYS_LABEL = "DIAGNOSYS-LABEL";
	public static final String DIAGNOSYS_VALUE = "DIAGNOSYS-VALUE";
	public static final String THERAPEUTIC_BEHAVIOR_LABEL = "THERAPEUTIC-BEHAVIOR-LABEL";
	public static final String THERAPEUTIC_BEHAVIOR_VALUE = "THERAPEUTIC-BEHAVIOR-VALUE";
	public static final String HEALTH_CONSEQUENCES_LABEL = "HEALTH-CONSEQUENCES-LABEL";
	public static final String HEALTH_CONSEQUENCES_VALUE = "HEALTH-CONSEQUENCES-VALUE";
	public static final String COMPLEMENTARY_EXAMS_RESULTS_LABEL = "COMPLEMENTARY-EXAM-RESULTS-LABEL";
	public static final String COMPLEMENTARY_EXAMS_RESULTS_VALUE = "COMPLEMENTARY-EXAMS-RESULTS-VALUE";
	public static final String ICD_LABEL = "ICD-LABEL";
	public static final String ICD_VALUE = "ICD-VALUE";
	public static final String TITLE = "TITLE";
	public static final String COMMENTS = "COMMENTS";
	public static final String MD_NAME = "MD-NAME";
	public static final String MD_SPECIALTY = "MD-SPECIALTY";
	public static final String MD_PROFESSIONAL_ID = "MD-PROFESSIONAL-ID";
	public static final String LOCATION = "LOCATION";
	public static final String CONTACT_EMAIL = "CONTACT-EMAIL";
	public static final String CONTACT_PHONE = "CONTACT-PHONE";
	public static final String AUTHENTICITY_MESSAGE = "AUTHENTICITY-MESSAGE";
	public static final String AUTHENTICITY_URL = "AUTHENTICITY-URL";
	
	public static ByteArrayOutputStream generateMedicalCertificatePDFItext(Map<String, String> content) throws DocumentException, IOException {
		Document doc = new Document();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PdfWriter.getInstance(doc, stream);
		
		String metadataTitle = content.get(METADATA_TITLE);
		if (IPrescribeStringUtils.isNotBlank(metadataTitle)) {
			doc.addTitle(metadataTitle);
		}
		
		String metadataSubject = content.get(METADATA_SUBJECT);
		if (IPrescribeStringUtils.isNotBlank(metadataSubject)) {
			doc.addSubject(metadataSubject);
		}
		
		String metadataKeywords = content.get(METADATA_KEYWORDS);
		if (IPrescribeStringUtils.isNotBlank(metadataKeywords)) {
			doc.addKeywords(metadataKeywords);
		}
		
		String metadataAuthor = content.get(METADATA_AUTHOR);
		if (IPrescribeStringUtils.isNotBlank(metadataAuthor)) {
			doc.addAuthor(metadataAuthor);
		}
		
		String metadataCreator = content.get(METADATA_CREATOR);
		if (IPrescribeStringUtils.isNotBlank(metadataCreator)) {
			doc.addCreator(metadataCreator);
		}
		
		String metadataPagesize = content.get(METADATA_PAGESIZE);
		if (IPrescribeStringUtils.isNotBlank(metadataPagesize)) {
			doc.setPageSize(PageSize.getRectangle(metadataPagesize));
		}
		
		doc.open();
		Paragraph titleParagraph = new Paragraph();
		
		Paragraph textParagraph = new Paragraph();
		
		Paragraph mdInfoParagraph = new Paragraph();
		mdInfoParagraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		Paragraph footerParagraph = new Paragraph();
		footerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

		String title = content.get(TITLE);
		if (IPrescribeStringUtils.isNotBlank(title)) {
			addTitle(titleParagraph, title);
			breakLine(titleParagraph, 2);
		}
		
		String certifiedLabel = content.get(CERTIFIED_LABEL);
		String certifiedValue = content.get(CERTIFIED_VALUE);
		if (IPrescribeStringUtils.isNotBlank(certifiedLabel) && IPrescribeStringUtils.isNotBlank(certifiedValue)) {
			addLabelAndValue(textParagraph, certifiedLabel, certifiedValue);
			breakLine(textParagraph);
		}
		
		String purposeLabel = content.get(PURPOSE_LABEL);
		String purposeValue = content.get(PURPOSE_VALUE);
		if (IPrescribeStringUtils.isNotBlank(purposeLabel) && IPrescribeStringUtils.isNotBlank(purposeValue)) {
			addLabelAndValue(textParagraph, purposeLabel, purposeValue);
			breakLine(textParagraph);
		}
		
		String issuedLabel = content.get(ISSUED_LABEL);
		String issuedValue = content.get(ISSUED_VALUE);
		if (IPrescribeStringUtils.isNotBlank(issuedLabel) && IPrescribeStringUtils.isNotBlank(issuedValue)) {
			addLabelAndValue(textParagraph, issuedLabel, issuedValue);
			breakLine(textParagraph);
		}
		
		String expiresLabel = content.get(EXPIRES_LABEL);
		String expiresValue = content.get(EXPIRES_VALUE);
		if (IPrescribeStringUtils.isNotBlank(expiresLabel) && IPrescribeStringUtils.isNotBlank(expiresValue)) {
			addLabelAndValue(textParagraph, expiresLabel, expiresValue);
			breakLine(textParagraph, 2);
		}
		
		String prognosysLabel = content.get(PROGNOSYS_LABEL);
		String prognosysValue = content.get(PROGNOSYS_VALUE);
		if (IPrescribeStringUtils.isNotBlank(prognosysLabel) && IPrescribeStringUtils.isNotBlank(prognosysValue)) {
			addLabelAndValue(textParagraph, prognosysLabel, prognosysValue);
			breakLine(textParagraph, 2);
		}
		
		String diagnosysLabel = content.get(DIAGNOSYS_LABEL);
		String diagnosysValue = content.get(DIAGNOSYS_VALUE);
		if (IPrescribeStringUtils.isNotBlank(diagnosysLabel) && IPrescribeStringUtils.isNotBlank(diagnosysValue)) {
			addLabelAndValue(textParagraph, diagnosysLabel, diagnosysValue);
			breakLine(textParagraph, 2);
		}
		
		String therapeuticBehaviorLabel = content.get(THERAPEUTIC_BEHAVIOR_LABEL);
		String therapeuticBehaviorValue = content.get(THERAPEUTIC_BEHAVIOR_VALUE);
		if (IPrescribeStringUtils.isNotBlank(therapeuticBehaviorLabel) && IPrescribeStringUtils.isNotBlank(therapeuticBehaviorValue)) {
			addLabelAndValue(textParagraph, therapeuticBehaviorLabel, therapeuticBehaviorValue);
			breakLine(textParagraph, 2);
		}
		
		String icdLabel = content.get(ICD_LABEL);
		String icdValue = content.get(ICD_VALUE);
		if (IPrescribeStringUtils.isNotBlank(icdLabel) && IPrescribeStringUtils.isNotBlank(icdValue)) {
			addLabelAndValue(textParagraph, icdLabel, icdValue);
			breakLine(textParagraph, 2);
		}
		
		String healthConsequencesLabel = content.get(HEALTH_CONSEQUENCES_LABEL);
		String healthConsequencesValue = content.get(HEALTH_CONSEQUENCES_VALUE);
		if (IPrescribeStringUtils.isNotBlank(healthConsequencesLabel) && IPrescribeStringUtils.isNotBlank(healthConsequencesValue)) {
			addLabelAndValue(textParagraph, healthConsequencesLabel, healthConsequencesValue);
			breakLine(textParagraph, 2);
		}
		
		String complementaryExamsResultsLabel = content.get(COMPLEMENTARY_EXAMS_RESULTS_LABEL);
		String complementaryExamsResultsValue = content.get(COMPLEMENTARY_EXAMS_RESULTS_VALUE);
		if (IPrescribeStringUtils.isNotBlank(complementaryExamsResultsLabel) && IPrescribeStringUtils.isNotBlank(complementaryExamsResultsValue)) {
			addLabelAndValue(textParagraph, complementaryExamsResultsLabel, complementaryExamsResultsValue);
			breakLine(textParagraph, 2);
		}
		
		String comments = content.get(COMMENTS);
		if (IPrescribeStringUtils.isNotBlank(comments)) {
			addText(textParagraph, comments);
			breakLine(textParagraph, 2);
		}
		
		String mdName = content.get(MD_NAME);
		if (IPrescribeStringUtils.isNotBlank(mdName)) {
			addText(mdInfoParagraph, mdName, FONT_MD_NAME);
			breakLine(mdInfoParagraph);
		}
		
		String mdSpecialty = content.get(MD_SPECIALTY);
		if (IPrescribeStringUtils.isNotBlank(mdSpecialty)) {
			addText(mdInfoParagraph, mdSpecialty, FONT_MD_SPECIALTY);
		}
		
		String mdProfessionalId = content.get(MD_PROFESSIONAL_ID);
		if (IPrescribeStringUtils.isNotBlank(mdProfessionalId)) {
			addText(mdInfoParagraph, " / "+mdProfessionalId, FONT_MD_PROFESSIONAL_ID);
			breakLine(mdInfoParagraph);
		}
		
		String contactPhone = content.get(CONTACT_PHONE);
		if (IPrescribeStringUtils.isNotBlank(contactPhone)) {
			addText(footerParagraph, contactPhone, FONT_FOOTER);
		}
		
		String contactEmail = content.get(CONTACT_EMAIL);
		if (IPrescribeStringUtils.isNotBlank(contactEmail)) {
			addText(footerParagraph, " / ", FONT_FOOTER);
			addText(footerParagraph, contactEmail, FONT_FOOTER);
		}
		
		String location = content.get(LOCATION);
		if (IPrescribeStringUtils.isNotBlank(location)) {
			addText(footerParagraph, " / ", FONT_FOOTER);
			addText(footerParagraph, location, FONT_FOOTER);
		}
		
		breakLine(footerParagraph,3);
		String authenticityMsg = content.get(AUTHENTICITY_MESSAGE);
		String authenticityUrl = content.get(AUTHENTICITY_URL);
		if (IPrescribeStringUtils.isNotBlank(authenticityMsg) && IPrescribeStringUtils.isNotBlank(authenticityUrl)) {
			String authMsg = authenticityMsg.concat(" ").concat(authenticityUrl);
			addText(footerParagraph, authMsg, FONT_AUTHCHECK);
		}
		
		doc.add(titleParagraph);
		
		doc.add(textParagraph);
		
		doc.add(mdInfoParagraph);
		
		doc.add(footerParagraph);
		
		doc.close();
	
		return stream;
	}
	
	public static void addTitle(Phrase phrase, String title) {
		Paragraph par = (Paragraph) phrase;
		par.setAlignment(Paragraph.ALIGN_CENTER);
		par.add(new Phrase(title,FONT_TITLE));
	}
	
	public static void addLabelAndValue(Phrase phrase, String label, String text) {
		phrase.add(new Phrase(label,FONT_FIELD_LABEL));
		phrase.add(new Phrase(" ".concat(text),FONT_FIELD_VALUE));
	}
	
	public static void addText(Phrase phrase, String text) {
		phrase.add(new Phrase(text,FONT_TEXT));
	}
	
	public static void addText(Phrase phrase, String text, Font font) {
		phrase.add(new Phrase(text,font));
	}
	
	public static void breakLine(Phrase phrase) {
		phrase.add(new Paragraph(" "));
	}
	
	public static void breakLine(Phrase phrase, int numberOfLines) {
		for (int i=0; i< numberOfLines; i++) {
			breakLine(phrase);
		}
	}
	
	private static ByteArrayOutputStream generateMedicalCertificateMap() throws DocumentException, IOException {
		Date issued = new Date();
		Date expires = IPrescribeDateUtils.addDays(issued, 10);
		String title = "Atestado Medico";
		String comments = "Atesto para os devidos fins que o sr. Ramon Facchin necessita de 10 dias de repouso, afastado do trabalho, por motivo de doenca.";
		String mdName = "Dr. Who";
		String mdSpecialty = null;//"Traumatologo";
		String mdProfessionalId = "123456";
		String location = "Rua Delfino Conti, Trindade, Florianopolis.";
		String contactEmail = "drwho@mail.com";
		String contactPhone = "9999-9999";
		String purpose = "Trabalhista";
		String certified = "Ramon Facchin";
		String authMsg = "Para verificar a autenticidade deste atestado, visite: ";
		String authUrl = "http://ramonfacchin.ufsc.br:8080/iprescribe-web/medical-certificate/auth-check=987654321";
		
		Map<String, String> certificate = new HashMap<String, String>();
		certificate.put(ISSUED_LABEL, "Emitido em:");
		certificate.put(ISSUED_VALUE, IPrescribeDateUtils.formatDate("dd/MM/yyyy", issued));
		certificate.put(EXPIRES_LABEL, "Valido ate:");
		certificate.put(EXPIRES_VALUE, IPrescribeDateUtils.formatDate("dd/MM/yyyy", expires));
		certificate.put(CERTIFIED_LABEL, "Atestado:");
		certificate.put(CERTIFIED_VALUE, certified);
		certificate.put(COMMENTS, comments);
		certificate.put(MD_NAME, mdName);
		certificate.put(MD_PROFESSIONAL_ID, mdProfessionalId);
		certificate.put(MD_SPECIALTY, mdSpecialty);
		certificate.put(TITLE, title);
		certificate.put(LOCATION, location);
		certificate.put(CONTACT_EMAIL, contactEmail);
		certificate.put(CONTACT_PHONE, contactPhone);
		certificate.put(PURPOSE_LABEL, "Proposito:");
		certificate.put(PURPOSE_VALUE, purpose);
		certificate.put(AUTHENTICITY_MESSAGE, authMsg);
		certificate.put(AUTHENTICITY_URL, authUrl);
		
		certificate.put(METADATA_TITLE, "Umtituloqqer");
		
		
		return generateMedicalCertificatePDFItext(certificate);
	}
	
	public static void main(String[] args) {
		try {
			ByteArrayOutputStream stream = generateMedicalCertificateMap();
			FileUtils.writeByteArrayToFile(new File("/tmp/teste2.pdf"), stream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
