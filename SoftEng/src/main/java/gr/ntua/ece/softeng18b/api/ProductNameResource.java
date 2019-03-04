package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.model.Product;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductNameResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String nameAttr = getAttribute("name");

        if (nameAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product's name");
        }

        List<Product> products = dataAccess.getProductsName(nameAttr);	//all the products with this name

        Map<String, Object> map = new HashMap<>();

        map.put("products", products);

        return new JsonMapRepresentation(map);

    }
}