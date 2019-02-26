package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.model.Shop;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class ShopResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }

        Optional<Shop> optional = dataAccess.getShop(id);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));

        return new JsonShopRepresentation(shop);
    }

    @Override
    protected Representation delete() throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }

        Optional<Shop> optional = dataAccess.deleteShop(id);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));

        Map<String, String> msg = new HashMap<>();
        msg.put("message", "OK");

        return new JsonMapRepresentation(msg);
    }

    @Override
    protected Representation put(Representation entity) throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        double lng = Double.valueOf(form.getFirstValue("lng"));
        double lat = Double.valueOf(form.getFirstValue("lat"));
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));
        String[] tags = form.getValuesArray("tags");

        //validate the values (in the general case)
        //...

        Optional<Shop> optional = dataAccess.updateShop(id, name, address, lng, lat, withdrawn, tags);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));


        return new JsonShopRepresentation(shop);
    }


    @Override
    protected Representation patch(Representation entity) throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        String lng = form.getFirstValue("lng");
        String lat = form.getFirstValue("lat");
        String withdrawn = (form.getFirstValue("withdrawn"));
        String tags = form.getFirstValue("tags");

        Optional<Shop> optional = null;

        if (name != null) {
            optional = dataAccess.patchShop(id, "name", name);
        }
        else if (address != null) {
            optional = dataAccess.patchShop(id, "address", address);
        }
        else if (lng != null) {
            optional = dataAccess.patchShop(id, "lng", lng);
        }
        else if (lat != null) {
            optional = dataAccess.patchShop(id, "lat", lat);
        }
        else if (withdrawn != null) {
            optional = dataAccess.patchShop(id, "withdrawn", withdrawn);
        }
        else if (tags != null) {
            optional = dataAccess.patchShop(id, "tags", tags);
        }        else {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "None field changed");
        }

        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));

        return new JsonShopRepresentation(shop);
    }


}
