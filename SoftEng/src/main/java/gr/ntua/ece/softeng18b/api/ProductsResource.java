package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.Limits;
import gr.ntua.ece.softeng18b.data.model.Product;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        
        
        // Parse "start" parameter
        Integer start = 0;

        String startAttr = getQueryValue("start");
        if(startAttr != null) {
            try {
                start = Integer.parseInt(startAttr);
            }
            catch(Exception e) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid start parameter: " + startAttr);
            }
        }

        // Parse "count" parameter
        Integer count = 20;

        String countAttr = getQueryValue("count");
        if (countAttr != null) {
            try {
                count = Integer.parseInt(countAttr);
            }
            catch(Exception e) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid count parameter: " + countAttr);
            }
        }

        // Parse "status" parameter
        Integer withdrawn = 0;
        String status = getQueryValue("status");

        if (status == null) {
            status = "ACTIVE";
        }

        if (status.equals("ALL")) {
          withdrawn = -1;
        }
        else if (status.equals("ACTIVE")) {
          withdrawn = 0;
        }
        else if (status.equals("WITHDRAWN")) {
          withdrawn = 1;
        }
        else {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid status parameter: " + status);
        }

        // Parse "sort" parameter
        String column = "id";
        String order  =  "DESC";

        String sortAttr = getQueryValue("sort");
        if (sortAttr != null) {

            String[] sort = sortAttr.split("\\|");

            if (sort.length < 2) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort parameter: " + sort.length);
            }

            column = sort[0];
            if (!column.equals("id") && !column.equals("name")) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort - column parameter: " + column);
            }

            order  = sort[1];
            if (!order.equals("ASC") && !order.equals("DESC")) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort - order parameter: " + order);
            }

        }


        List<Product> products = dataAccess.getProducts(new Limits(start, count), column, order, withdrawn);

        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("count", count);
        //map.put("total", xxx);
        map.put("products", products);

        return new JsonMapRepresentation(map);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {

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

        Product product = dataAccess.addProduct(name, description, category, withdrawn, tags);

        return new JsonProductRepresentation(product);
    }
}
