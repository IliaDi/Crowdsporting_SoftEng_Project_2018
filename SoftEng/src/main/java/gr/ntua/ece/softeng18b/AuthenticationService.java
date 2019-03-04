package gr.ntua.ece.softeng18b;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;

import java.util.UUID;
import java.util.Optional;

public class AuthenticationService {

	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

	public String createToken() {

		String token =  UUID.randomUUID().toString();
		dataAccess.saveToken(token);

		return token;
	}

	public void destroyToken(String token) {
		dataAccess.deleteToken(token);
		return;
	}

	public boolean validateToken(String token) {
		return dataAccess.findToken(token).isPresent();
	}


}
