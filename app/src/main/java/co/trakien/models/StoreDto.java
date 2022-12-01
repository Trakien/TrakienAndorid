package co.trakien.models;

import java.util.Date;
import java.util.List;

public class StoreDto {

    private String name;
    private String url;
    private List<String> prices;
    private List<Date> dates;
    private String img;

    public StoreDto() {
    }

    public StoreDto(String name, String url, List<String> prices, List<Date> dates, String img) {
        this.name = name;
        this.url = url;
        this.prices = prices;
        this.dates = dates;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}