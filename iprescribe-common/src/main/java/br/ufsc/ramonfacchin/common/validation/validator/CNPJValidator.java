/**
 * 
 */
package br.ufsc.ramonfacchin.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.common.validation.annotation.CPF;

/**
 * Validador de CNPJ.
 *
 * @author Ramon Facchin / ramon@gmail.com
 * @since Apr 5, 2013
 *
 */
public class CNPJValidator implements ConstraintValidator<CPF, String> {
	
	private boolean formatado;

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(CPF constraintAnnotation) {
		formatado = constraintAnnotation.formatted();
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return false;
		}
		if (!isFormatoValido(value)) {
			return false;
		}
		
		//Para referencia sobre a formula do CNPJ: http://pt.wikipedia.org/wiki/Cadastro_Nacional_da_Pessoa_Jur%C3%ADdica#Pseudoc.C3.B3digo
		String valorSomenteDigitos = IPrescribeStringUtils.removerLettersAndSpecialChars(value);
		int primeiroDigitoVerificador = new Integer(valorSomenteDigitos.charAt(12)+"");
		int segundoDigitoVerificador = new Integer(valorSomenteDigitos.charAt(13)+"");
		
		int somatorio = 0;
		int valorEsperado = 0;
		
		//validando primeiro digito verificador
		somatorio += 5*new Integer(valorSomenteDigitos.charAt(0)+"");
		somatorio += 4*new Integer(valorSomenteDigitos.charAt(1)+"");
		somatorio += 3*new Integer(valorSomenteDigitos.charAt(2)+"");
		somatorio += 2*new Integer(valorSomenteDigitos.charAt(3)+"");

		somatorio += 9*new Integer(valorSomenteDigitos.charAt(4)+"");
		somatorio += 8*new Integer(valorSomenteDigitos.charAt(5)+"");
		somatorio += 7*new Integer(valorSomenteDigitos.charAt(6)+"");
		somatorio += 6*new Integer(valorSomenteDigitos.charAt(7)+"");

		somatorio += 5*new Integer(valorSomenteDigitos.charAt(8)+"");
		somatorio += 4*new Integer(valorSomenteDigitos.charAt(9)+"");
		somatorio += 3*new Integer(valorSomenteDigitos.charAt(10)+"");
		somatorio += 2*new Integer(valorSomenteDigitos.charAt(11)+"");
				
		valorEsperado = somatorio % 11;
		
		if (valorEsperado < 2) {
			valorEsperado = 0;
		} else {
			valorEsperado = 11 - valorEsperado;
		}
		
		if (valorEsperado != primeiroDigitoVerificador) {
			return false;
		}
		
		//validando segundo digito verificador
		somatorio = 0;
		valorEsperado = 0;
		somatorio += 6*new Integer(valorSomenteDigitos.charAt(0)+"");
		somatorio += 5*new Integer(valorSomenteDigitos.charAt(1)+"");
		somatorio += 4*new Integer(valorSomenteDigitos.charAt(2)+"");
		somatorio += 3*new Integer(valorSomenteDigitos.charAt(3)+"");

		somatorio += 2*new Integer(valorSomenteDigitos.charAt(4)+"");
		somatorio += 9*new Integer(valorSomenteDigitos.charAt(5)+"");
		somatorio += 8*new Integer(valorSomenteDigitos.charAt(6)+"");
		somatorio += 7*new Integer(valorSomenteDigitos.charAt(7)+"");

		somatorio += 6*new Integer(valorSomenteDigitos.charAt(8)+"");
		somatorio += 5*new Integer(valorSomenteDigitos.charAt(9)+"");
		somatorio += 4*new Integer(valorSomenteDigitos.charAt(10)+"");
		somatorio += 3*new Integer(valorSomenteDigitos.charAt(11)+"");
		somatorio += 2*new Integer(valorSomenteDigitos.charAt(12)+"");
				
		valorEsperado = somatorio % 11;
		
		if (valorEsperado < 2) {
			valorEsperado = 0;
		} else {
			valorEsperado = 11 - valorEsperado;
		}
		
		if (valorEsperado != segundoDigitoVerificador) {
			return false;
		}
		
		return true;
	}
	
	public boolean isFormatoValido(String value) {
		String padrao = null;
		if (formatado) {
			padrao = "\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}";
		} else {
			padrao = "\\d{14}";
		}
		boolean respeitaPadrao = value.matches(padrao);
		if (!respeitaPadrao) {
			return false;
		}
		return true;
	}
	
	public void setFormatado(boolean formatado) {
		this.formatado = formatado;
	}
	
	public boolean getFormatado() {
		return this.formatado;
	}

}
