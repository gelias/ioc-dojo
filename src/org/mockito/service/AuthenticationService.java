package org.mockito.service;

import static org.apache.commons.lang.StringUtils.isEmpty;

import org.mockito.provider.GoogleProvider;

public class AuthenticationService {
	
	private GoogleProvider provider;
	
	public AuthenticationService(GoogleProvider provider) {
		this.provider = provider;
	}

	public boolean doLogin(String user, String password) {
		validateUserAndPasswordAndThrowsException(user, password);

		return provider.authenticate(user, password);

	}

	private void validateUserAndPasswordAndThrowsException(String user,
			String password) {
		if (isEmpty(user) || isEmpty(password))
			throw new RuntimeException();
	}

}
