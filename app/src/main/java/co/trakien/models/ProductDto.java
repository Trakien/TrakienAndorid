package co.trakien.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductDto {

    private String id;
    private String ref;
    private String name;
    private String category;
    private String brand;
    private List<StoreDto> stores;
    private boolean visibility = false;

    public ProductDto() {
    }

    public ProductDto(String id, String ref, String name, String category, String brand,
                      List<StoreDto> stores) {
        this.id = id;
        this.ref = ref;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.stores = stores;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<StoreDto> getStores() {
        return stores;
    }

    public void setStores(List<StoreDto> stores) {
        this.stores = stores;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public List<String> getXChartInfo(){
        Set<String> xDates = new TreeSet<>();
        for( StoreDto store : stores ){
            for ( Date date : store.getUpdateDates() ){
                xDates.add(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
            }
        }
        return xDates.stream().collect(Collectors.toList());
    }
}