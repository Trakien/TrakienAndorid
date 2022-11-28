package co.trakien.interfaces;

import java.util.List;

import co.trakien.models.FiltersDto;

import co.trakien.models.ProductDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FiltersApi {

    @POST("/api/v2/filters/categories")
    public Call<List<String>> getAllCategories(@Header("Authorization") String authToken);
    @POST("/api/v2/filters/brands")
    public Call<List<String>> getAllBrands(@Header("Authorization") String authToken);
    @POST("/api/v2/filters/categories/filter")
    public Call<List<String>> getFilterCategories(@Body FiltersDto filters, @Header("Authorization") String authToken);
    @POST("/api/v2/filters/brands/filter")
    public Call<List<String>> getFilterBrands(@Body FiltersDto filters, @Header("Authorization") String authToken);
    @POST("/api/v2/filters/allFilter")
    public Call<List<ProductDto>> getAllFilter(@Body FiltersDto filters, @Header("Authorization") String authToken);

}
