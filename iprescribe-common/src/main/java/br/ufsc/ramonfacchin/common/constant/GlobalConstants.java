package br.ufsc.ramonfacchin.common.constant;

/**
 * Constants for the whole system. Defined for frequent use.
 * 
 * @author ramonfacchin
 * 
 */
public interface GlobalConstants {

	/**
	 * Time constants, in millis
	 */
	public interface TIME {
		public static final int SECOND = 1000;
		public static final int MINUTE = 60 * SECOND;
		public static final int HOUR = 60 * MINUTE;
		public static final int DAY = 24 * HOUR;
		public static final int WEEK = 7 * DAY;
	}
	
	/**
	 * Web constants, like default web pages.
	 */
	public interface WEB {
		public interface URL {
			public static final String CONTEXT = "/iprescribe-web";
			public static final String HOME = "/index.jsf";
			public static final String LOGIN = "/login.jsf";
			public static final String LOGOUT = "/logout";
			public static final String REGISTER = "/public/user/register.jsf";
			public static final String USER_CERTIFICATES = "/restricted/user/mycertificates.jsf";
			public static final String USER_VIEW_CERTIFICATE = "/restricted/certificate/view-certificate.jsf";
			public static final String AUTHCHECK = "/public/user/authcheck.jsf";
			public static final String MD_CERTIFICATES = "/restricted/medicaldoctor/mycertificates.jsf";
			public static final String MD_CERTIFY = "/restricted/medicaldoctor/certify.jsf";
			public static final String MD_ADD_LICENSE = "/restricted/medicaldoctor/add-license.jsf";
			public static final String MD_VIEW_CERTIFICATE = "/restricted/medicaldoctor/view-certificate.jsf";
			public static final String ADMIN_LICENSE_REQUEST = "/restricted/administrator/license-request.jsf";
			public static final String ADMIN_CONFIGURATIONS = "/restricted/administrator/configurations.jsf";
		}
	}

}
