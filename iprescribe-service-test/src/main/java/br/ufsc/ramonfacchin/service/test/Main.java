/**
 * 
 */
package br.ufsc.ramonfacchin.service.test;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufsc.ramonfacchin.common.exception.ServiceLocationException;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationService;
import br.ufsc.ramonfacchin.service.util.ServiceLocator;

/**
 * TODO Descrição da classe.
 *
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 22, 2013
 *
 */
public class Main {

	/**
	 * TODO Descrição do Método
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IDatabaseConfigurationService databaseConfigurationService = (IDatabaseConfigurationService) ServiceLocator.getInstance().getService(IDatabaseConfigurationService.class);
			if (databaseConfigurationService != null) {
				List<DatabaseConfiguration> listAll = databaseConfigurationService.listAll();
				if (listAll != null) {
					StringBuilder sb = new StringBuilder();
					for (DatabaseConfiguration dbc : listAll) {
						sb.append(dbc.getKey()).append("=").append(dbc.getValue()).append("\n");
					}
					JOptionPane.showMessageDialog(null, sb.toString());
				}
			}
		} catch (ServiceLocationException e) {
			e.printStackTrace();
		}
	}

}
