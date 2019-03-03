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
import java.util.List;
import java.util.Arrays;


public class ShopsNameResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String[] shopNames = getQuery().getValuesArray("name");

        if (shopNames.length == 0) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop names");
        }

        List <Shop> shops = dataAccess.getShopsName(shopNames);

        if (shops.equals(null)){
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shops not found with these names");
        }

        Map<String, Object> map = new HashMap<>();

        map.put("shops", shops);

        return new JsonMapRepresentation(map);
    }
}