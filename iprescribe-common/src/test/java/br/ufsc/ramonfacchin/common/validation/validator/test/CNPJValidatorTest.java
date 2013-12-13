/**
 * 
 */
package br.ufsc.ramonfacchin.common.validation.validator.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ramonfacchin.common.validation.validator.CNPJValidator;

/**
 * Prova de conceito do validador de CPF.
 * 
 * @author Ramon Facchin / ramon@gmail.com
 * @since Apr 5, 2013
 * 
 */
public class CNPJValidatorTest {

	private String cnpj;
	private String contraExemplo;

	private CNPJValidator validador;

	@Before
	public void inicializar() {
		validador = new CNPJValidator();
	}
	
	// 85611627000131

	/**
	 * Verifica se o validador, com formatado=true, rejeita CPFs não formatados.
	 * @throws Exception
	 */
	@Test
	public void testFormatado() throws Exception {
		validador.setFormatado(true);
		cnpj = "85.611.627/0001-31";
		contraExemplo = "85611627000131";
		Assert.assertTrue(validador.isFormatoValido(cnpj));
		Assert.assertFalse(validador.isFormatoValido(contraExemplo));
	}

	/**
	 * Verifica se o validador, com formatado=false, rejeita CPFs formatados.
	 * @throws Exception
	 */
	@Test
	public void testNaoFormatado() throws Exception {
		validador.setFormatado(false);
		contraExemplo = "85.611.627/0001-31";
		cnpj = "85611627000131";
		Assert.assertTrue(validador.isFormatoValido(cnpj));
		Assert.assertFalse(validador.isFormatoValido(contraExemplo));
	}

	/**
	 * Verifica se o validador contempla a fórmula do CPF. 
	 * @throws Exception
	 */
	@Test
	public void testFormulaValida() throws Exception {
		validador.setFormatado(false);
		contraExemplo = "33322233322333";
		cnpj = "85611627000131";
		Assert.assertTrue(validador.isValid(cnpj, null));
		Assert.assertFalse(validador.isValid(contraExemplo, null));
	}

}
