package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.model.Product;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class ProductResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }

        Optional<Product> optional = dataAccess.getProduct(id);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));

        return new JsonProductRepresentation(product);
    }

    @Override
    protected Representation delete() throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }

        Optional<Product> optional = dataAccess.deleteProduct(id);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));

        Map<String, String> msg = new HashMap<>();
        msg.put("message", "OK");

        return new JsonMapRepresentation(msg);
    }

    @Override
    protected Representation put(Representation entity) throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        String category = form.getFirstValue("category");
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));
        String[] tags = form.getValuesArray("tags");

        //validate the values (in the general case)
        //...

        Optional<Product> optional = dataAccess.updateProduct(id, name, description, category, withdrawn, tags);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));


        return new JsonProductRepresentation(product);
    }


    @Override
    protected Representation patch(Representation entity) throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        String category = form.getFirstValue("category");
        String withdrawn = form.getFirstValue("withdrawn");
        String tags = form.getFirstValue("tags");

        Optional<Product> optional = null;

        if (name != null) {
            optional = dataAccess.patchProduct(id, "name", name);
        }
        else if (description != null) {
            optional = dataAccess.patchProduct(id, "description", description);
        }
        else if (category != null) {
            optional = dataAccess.patchProduct(id, "category", category);
        }
        else if (withdrawn != null) {
            optional = dataAccess.patchProduct(id, "withdrawn", withdrawn);
        }
        else if (tags != null) {
            optional = dataAccess.patchProduct(id, "tags", tags);
        }
        else {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "None field changed");
        }

        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));

        return new JsonProductRepresentation(product);
    }


}
