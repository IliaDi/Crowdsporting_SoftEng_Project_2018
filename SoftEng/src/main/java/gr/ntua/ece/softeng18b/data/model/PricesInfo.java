package gr.ntua.ece.softeng18b.data.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PricesInfo {

    private final double price;
    private final String date;

    private final long   productId;
    private final String productName;
    private List<String> productTags = new ArrayList<>();

    private final long   shopId;
    private final String shopName;
    private List<String> shopTags = new ArrayList<>();
    private final String shopAddress;
    private final int shopDist;

    public PricesInfo(double price, String date, long pid, String pname, long sid, String sname, String address, Integer dist) {
        this.price        = price;
        this.date         = date;
        this.productId    = pid;
        this.productName  = pname;
        this.shopId       = sid;
        this.shopName     = sname;
        this.shopAddress  = address;
        this.shopDist     = dist;
    }


    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }



    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public List<String> getProductTags() {
        return productTags;
    }



    public long getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public List<String> getShopTags() {
        return shopTags;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public int getShopDist() {
        return shopDist;
    }




    public void setProductTags(List<String> tags) {
        this.productTags = tags;
    }

    public void setShopTags(List<String> tags) {
        this.shopTags = tags;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricesInfo pricesInfo = (PricesInfo) o;
        return price == pricesInfo.price && date == pricesInfo.date && productId == pricesInfo.productId && shopId == pricesInfo.shopId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
