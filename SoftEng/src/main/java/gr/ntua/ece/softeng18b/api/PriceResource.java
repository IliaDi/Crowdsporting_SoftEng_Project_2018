package gr.ntua.ece.softeng18b.api;

import gr.ntua.ece.softeng18b.conf.Configuration;
import gr.ntua.ece.softeng18b.data.DataAccess;
import gr.ntua.ece.softeng18b.data.Limits;
import gr.ntua.ece.softeng18b.data.model.Price;
import gr.ntua.ece.softeng18b.data.model.PricesInfo;
import gr.ntua.ece.softeng18b.data.model.Product;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class PriceResource extends ServerResource {

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


        // Parse "sort" parameter
        String column = "id";
        String order  =  "ASC";

        String sortAttr = getQueryValue("sort");
        if (sortAttr != null) {

            String[] sort = sortAttr.split("\\|");

            if (sort.length < 2) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort parameter: " + sort.length);
            }

            column = sort[0];
            if (!column.equals("geoDist") && !column.equals("price") && !column.equals("date")) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort - column parameter: " + column);
            }

            order  = sort[1];
            if (!order.equals("ASC") && !order.equals("DESC")) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort - order parameter: " + order);
            }

        }

        // Parse "shops" paramter
        String[] shops = getQuery().getValuesArray("shops");
        Long[]   shopIds = new Long[shops.length];
        for (int i = 0; i < shops.length; i++) {
            try {
                shopIds[i] = Long.parseLong(shops[i]);
            }
            catch(Exception e) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id parameter: " + shops[i]);
            }
        }

        // Parse "products" parameter
        String[] prods = getQuery().getValuesArray("products");
        Long[]   prodIds = new Long[prods.length];
        for (int i = 0; i < prods.length; i++) {
            try {
                prodIds[i] = Long.parseLong(prods[i]);
            }
            catch(Exception e) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid prod id parameter: " + prods[i]);
            }
        }

        // Parse "dateFrom" parameter
        String dateFrom = getQueryValue("dateFrom");
        if (dateFrom == null)
            dateFrom = "curdate()";

        String[] tags = getQuery().getValuesArray("tags");

        List<PricesInfo> prices = dataAccess.getPrice(new Limits(start, count), column, order, dateFrom, prodIds, shopIds, tags);

        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("count", count);
        //map.put("total", xxx);
        map.put("prices", prices);

        return new JsonMapRepresentation(map);
    }




    @Override
    protected Representation post(Representation entity) throws ResourceException {

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        Double price = Double.valueOf(form.getFirstValue("price"));
        Long pid = Long.valueOf(form.getFirstValue("pid"));
        Long sid = Long.valueOf(form.getFirstValue("sid"));
        String dateFrom = form.getFirstValue("dateFrom");
        String dateTo = form.getFirstValue("dateTo");


        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateFrom, format);
        LocalDate endDate = LocalDate.parse(dateTo, format).plusDays(1);

        //validate the values (in the general case)
        //...

        List<LocalDate> dates = startDate.datesUntil(endDate).collect(Collectors.toList());

        Price priceItem = dataAccess.addPrice(price, pid, sid, endDate.format(format));

        List<Price> prices = dates.stream()
                                  .map(date -> dataAccess.addPrice(price, pid, sid, date.format(format)))
                                  .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("prices", prices);

        return new JsonMapRepresentation(map);
    }
}
