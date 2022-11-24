package co.trakien.interfaces;

import java.security.PublicKey;
import java.util.List;

import co.trakien.models.ProductDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
    @GET
    public Call<List<ProductDto>> getAll();
    @GET("/ref/{ref}")
    public Call<ProductDto>findByRef(@Path("ref") String ref);

}
