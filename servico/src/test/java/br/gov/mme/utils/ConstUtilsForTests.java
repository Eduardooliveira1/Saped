package br.gov.mme.utils;

import java.time.LocalDateTime;

import br.gov.mme.enumerator.FlStatus;

/**
 * Constantes compartilhadas para os testes.
 **/
public abstract class ConstUtilsForTests {

	private ConstUtilsForTests() {
	}

	public static final String DEFAULT_STRING_TAM_14 = "11111111111111";

	public static final String DEFAULT_STRING_TAM_9 = "aaaaaaaaa";

	public static final FlStatus DEFAULT_FL_STATUS = FlStatus.N;

	public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();

}
