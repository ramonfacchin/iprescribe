package br.ufsc.ramonfacchin.service.util.test;

import junit.framework.Assert;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.junit.Test;

public class LDAPTest {
	
	public void testLdapConn() throws Exception {
		LdapConnection connection = new LdapNetworkConnection("localhost");
		connection.bind();
		
		Entry entry = new DefaultEntry("cn=ramonfacchin,ou=tcc","ObjectClass: top", "ObjectClass: person", "sn=ramonfacchin", "cn=ramonfacchin");
		connection.add(entry);
		
		EntryCursor entryCursor = connection.search("ou=tcc",  "cn=ramonfacchin", null, null);
		Assert.assertTrue(entryCursor.isFirst());
		
	}

}
