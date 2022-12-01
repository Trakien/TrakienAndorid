package co.trakien.interfaces;

import java.util.List;

import co.trakien.models.CustomerDto;
import co.trakien.models.LoginDto;
import co.trakien.models.TokenDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerApi {

    @GET("/api/v2/customers")
    public Call<List<CustomerDto>> getAll();
    @GET("/api/v2/customers/{id}")
    public Call<CustomerDto> findById(@Path("id") String id);
    @GET("/api/v2/customers/email/{email}")
    public Call<CustomerDto> findByEmail(@Path("email") String email, @Header("Authorization") String authToken);
    @POST("/api/v2/customers")
    public Call<CustomerDto> create(@Body CustomerDto customer);
    @PUT("/api/v2/customers/{id}")
    public Call<CustomerDto> update(@Body CustomerDto customer, @Path("id") String id);
    @POST("/v2/auth")
    public Call<TokenDto> login(@Body LoginDto loginDto);


}
