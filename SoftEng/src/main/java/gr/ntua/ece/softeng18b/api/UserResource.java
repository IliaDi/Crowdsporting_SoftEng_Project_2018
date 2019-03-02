package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.model.User;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class UserResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();


    @Override
    protected Representation post(Representation entity) throws ResourceException {

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String email = form.getFirstValue("email");
        String fullname = form.getFirstValue("fullname");
        String password = form.getFirstValue("password");

        //validate the values (in the general case)
        //...

        User user = dataAccess.addUser(email, fullname, password);

        return new JsonUserRepresentation(user);
    }
}