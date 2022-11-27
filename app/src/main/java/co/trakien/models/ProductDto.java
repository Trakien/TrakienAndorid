package co.trakien.models;

import java.util.List;

public class ProductDto {

    private String id;
    private String ref;
    private String name;
    private String category;
    private String brand;
    private String updateDates;
    private List<StoreDto> stores;

    public ProductDto() {
    }

    public ProductDto(String id, String ref, String name, String category, String brand,
                      List<StoreDto> stores) {
        this.id = id;
        this.ref = ref;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.updateDates = updateDates;
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

}