package co.trakien.models;

public class ProductDto {

    private String id;
    private String ref;
    private String name;
    private String category;
    private String brand;
    private String updateDates;
    private String stores;

    public ProductDto() {
    }

    public ProductDto(String id, String ref, String name, String category, String brand, String updateDates,
                      String stores) {
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

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public String getUpdateDates() {
        return updateDates;
    }

    public void setUpdateDates(String updateDates) {
        this.updateDates = updateDates;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}