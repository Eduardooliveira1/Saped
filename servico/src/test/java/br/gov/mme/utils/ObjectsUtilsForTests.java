package br.gov.mme.utils;

import br.gov.mme.domain.Pessoa;

/*
 * Criador de objetos auxiliares para os Beans 
 * nos testes.
 */
public abstract class ObjectsUtilsForTests {
	
	private ObjectsUtilsForTests() {
	}

	public static Pessoa getDefaultPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setDataCadastro(ConstUtilsForTests.DEFAULT_DATE_TIME);
		pessoa.setStatus(ConstUtilsForTests.DEFAULT_FL_STATUS);
		return pessoa;
	}

}
