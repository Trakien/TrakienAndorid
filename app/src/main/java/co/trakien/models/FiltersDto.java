package co.trakien.models;

import java.util.List;

public class FiltersDto {

    private String search;
    private List<String> brands;
    private List<String> categories;

    public FiltersDto() {
    }

    public FiltersDto(String search, List<String> brands, List<String> categories) {
        this.search = search;
        this.brands = brands;
        this.categories = categories;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}