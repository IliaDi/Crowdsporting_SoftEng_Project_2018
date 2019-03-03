package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.model.User;
import gr.ntua.ece.softeng18b.AuthenticationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


public class LoginResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        //All login attempts succeed with the same token

        //Create a new restlet form
        Form form = new Form(entity);

        String username = form.getFirstValue("username");
        String password = form.getFirstValue("password");

        Optional<User> optional = dataAccess.getUserName(username);
        User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "User: " + username + " not found"));

        if (!user.getPassword().equals(password))
          throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid password");


        AuthenticationService authenticationService = new AuthenticationService();
        String token = authenticationService.createToken();

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);

        return new JsonMapRepresentation(map);
    }
}

