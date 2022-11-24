package co.trakien.interfaces;

import java.util.List;

import co.trakien.models.FiltersDto;

import co.trakien.models.ProductDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FiltersApi {

    @GET
    public Call<String> getAllFilters();
    @POST("/categories")
    public Call<List<String>>getAllCategories();
    @POST("/categories/filter")
    public Call<List<String>> getFilterCategories(@Body FiltersDto filters);
    @GET("/categories/{category}")
    public Call<List<ProductDto>> getByCategories(@Path("category") String category);
    @GET("/brands")
    public Call<List<String>> getAllBrands();
    @POST("/brands/filter")
    public Call<List<String>> getFilterBrands(@Body FiltersDto filters);
    @GET("/brands/{brand}")
    public Call<List<ProductDto>> getByBrand(@Path("brand") String brand);
    @GET("/names/{name}")
    public Call<List<ProductDto>> getLikeName(@Path("name") String name);
    @POST("/allFilter")
    public Call<List<ProductDto>> getAllFilter(@Body FiltersDto filters);

}
