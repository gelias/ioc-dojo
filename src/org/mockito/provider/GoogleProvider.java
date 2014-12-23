package org.mockito.provider;

public class GoogleProvider extends Provider {

	@Override
	public boolean authenticate(String user, String password) {
		
		System.out.println("Dispatching request to Google ...");
		System.out.println("Validating user and password ...");

		if(user.equals("guilherme.elias@gmail.com") && password.equals("xpt9"))
			return true;
		else 
			return false;
	}
}
