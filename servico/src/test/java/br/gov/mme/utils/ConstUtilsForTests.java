package br.gov.mme.utils;

import java.time.LocalDateTime;

import br.gov.mme.enumerator.FlStatus;

/**
 * Constantes compartilhadas para os testes.
 **/
public abstract class ConstUtilsForTests {

	private ConstUtilsForTests() {
	}

	public static final String DEFAULT_STRING_TAM_9 = "aaaaaaaaa";

	public static final String UPDATED_STRING_TAM_9 = "bbbbbbbbb";

	public static final FlStatus DEFAULT_FL_STATUS = FlStatus.S;

	public static final FlStatus UPDATED_FL_STATUS = FlStatus.N;

	public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();

}
