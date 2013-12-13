package br.ufsc.ramonfacchin.controller.mb.util;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.ufsc.ramonfacchin.enumeration.GenderEnum;

@Named("enumSelects")
@ApplicationScoped
public class EnumSelects {

	public List<GenderEnum> getGenders() {
		return Arrays.asList(GenderEnum.values());
	}

}
