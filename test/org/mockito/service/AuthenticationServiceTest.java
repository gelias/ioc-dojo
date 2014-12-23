package org.mockito.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.provider.GoogleProvider;


public class AuthenticationServiceTest {
	
	@Mock
	private GoogleProvider provider;
	
	private AuthenticationService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new AuthenticationService(provider);
	}

	@Test(expected=RuntimeException.class)
	public void deveValidarLoginVazio() {
		service.doLogin(null, null);
	}
	
	@Test(expected=RuntimeException.class)
	public void deveValidarSenhaVazia() {
		service.doLogin("login", null);
	}
	
	@Test
	public void deveValidarSenhaEUsuarioInvalidos() {
		BDDMockito.given(provider.authenticate(Mockito.anyString(), Mockito.anyString())).willReturn(false);
		assertFalse(service.doLogin("login", "senha"));	
	}
	
	@Test
	public void deveValidarSenhaEUsuarioValidos() {
        BDDMockito.given(provider.authenticate(Mockito.anyString(), Mockito.anyString())).willReturn(true);
		assertTrue(service.doLogin("guilherme.elias@gmail.com", "xpt9"));	
	}
}
