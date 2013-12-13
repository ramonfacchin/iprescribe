package br.ufsc.ramonfacchin.converter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;

@Named("medicalLicenseConverter")
@RequestScoped
public class MedicalLicenseConverter implements Converter {
	
	@EJB
	IMedicalLicenseServiceLocal medicalLicenseService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (IPrescribeStringUtils.isNotBlank(value)) {
			return medicalLicenseService.findById(new Long(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		MedicalLicense ml = (MedicalLicense) value;
		return ml.getId()+"";
	}

}
