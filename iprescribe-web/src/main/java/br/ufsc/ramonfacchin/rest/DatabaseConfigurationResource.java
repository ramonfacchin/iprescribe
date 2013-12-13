package br.ufsc.ramonfacchin.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the {@link DatabaseConfiguration} table.
 */
@Path("/databaseconfiguration")
@RequestScoped
public class DatabaseConfigurationResource {
	
	@Inject
	IDatabaseConfigurationServiceLocal databaseConfigurationService;
	
	@GET
	@Path("/xml")
	@Produces(value="text/xml")
	public List<DatabaseConfiguration> listAllDatabaseConfigurationsXML() {
		return databaseConfigurationService.listAll();
	}

	@GET
	@Path("/xml/{id:[0-9][0-9]*}")
	@Produces("text/xml")
	public DatabaseConfiguration lookupDatabaseConfigurationByIdXML(@PathParam("id") long id) {
		return databaseConfigurationService.findById(id);
	}

	@GET
	@Path("/xml/bykey/{key}")
	@Produces("text/xml")
	public DatabaseConfiguration lookupDatabaseConfigurationByKeyXML(@PathParam("key") String key) {
		return databaseConfigurationService.findByKey(key);
	}
	
	@GET
	@Path("/json")
	@Produces(value="text/json")
	public List<DatabaseConfiguration> listAllDatabaseConfigurationsJSON() {
		return databaseConfigurationService.listAll();
	}

	@GET
	@Path("/json/{id:[0-9][0-9]*}")
	@Produces("text/json")
	public DatabaseConfiguration lookupDatabaseConfigurationByIdJSON(@PathParam("id") long id) {
		return databaseConfigurationService.findById(id);
	}

	@GET
	@Path("/json/bykey/{key}")
	@Produces("text/json")
	public DatabaseConfiguration lookupDatabaseConfigurationByKeyJSON(@PathParam("key") String key) {
		return databaseConfigurationService.findByKey(key);
	}
}
