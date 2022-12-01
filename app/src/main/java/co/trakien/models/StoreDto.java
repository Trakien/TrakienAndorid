package co.trakien.models;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class StoreDto {

    private String name;
    private String url;
    private List<String> prices;
    private List<Date> updateDates;
    private String img;

    public StoreDto() {
    }

    public StoreDto(String name, String url, List<String> prices, List<Date> dates, String img) {
        this.name = name;
        this.url = url;
        this.prices = prices;
        this.updateDates = dates;
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

    public List<Date> getUpdateDates() {
        return updateDates;
    }

    public void setUpdateDates(List<Date> updateDates) {
        this.updateDates = updateDates;
    }

    public Integer getPriceAtDate(String dateToSearch){
        Integer res = null;

        for (int i = 0; i < updateDates.size(); i++){
            String dateToEvaluate = updateDates.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
            if(dateToEvaluate.equals(dateToSearch)){
                res = Integer.parseInt(prices.get(i));
            }
        }
        if (res == null){

        }
        return res;
    }
}