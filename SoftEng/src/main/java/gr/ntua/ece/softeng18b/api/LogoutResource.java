package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.model.User;
import gr.ntua.ece.softeng18b.AuthenticationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.restlet.util.Series;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


public class LogoutResource extends ServerResource {

    public static final String AUTHENTICATION_HEADER = "X-OBSERVATORY-AUTH";
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();


    @Override
    protected Representation post(Representation entity) throws ResourceException {
        //Logout - Invalidate token

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue(AUTHENTICATION_HEADER);

        AuthenticationService authenticationService = new AuthenticationService();
        authenticationService.destroyToken(token);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "ok");

        return new JsonMapRepresentation(map);
    }
}
