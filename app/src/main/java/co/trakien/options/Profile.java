package co.trakien.options;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.trakien.R;
import co.trakien.data.model.LoggedInUser;
import co.trakien.interfaces.CustomerApi;
import co.trakien.models.CustomerDto;
import co.trakien.products.ProductsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity {
    TextView dateInfo, nameInfo, lastNameInfo, emailinfo;
    Button back;
    String emailToken = LoggedInUser.getInstance().getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        dateInfo = findViewById(R.id.dateInfo);
        nameInfo = findViewById(R.id.nameInfo);
        lastNameInfo = findViewById(R.id.lastNameInfo);
        emailinfo.findViewById(R.id.emailinfo);
        back = findViewById(R.id.back);
        setProfile();

        back.setOnClickListener(view -> goHome());
    }

    private void setProfile(){
        Retrofit customerAPI = new Retrofit.Builder().baseUrl("http://192.168.1.5:81").addConverterFactory(GsonConverterFactory.create()).build();
        CustomerApi customerApiService =customerAPI.create(CustomerApi.class);
        Call<CustomerDto> infoProfile = customerApiService.findByEmail(emailToken);
        infoProfile.enqueue(new Callback<CustomerDto>() {
            @Override
            public void onResponse(@NonNull Call<CustomerDto> call, @NonNull Response<CustomerDto> response) {
                try {
                    if (response.isSuccessful()){
                        CustomerDto cus = response.body();
                        assert cus != null;
                        dateInfo.setText(cus.getCreatedAt());
                        nameInfo.setText(cus.getName());
                        lastNameInfo.setText(cus.getLastName());
                        emailinfo.setText(cus.getEmail());
                    }
                } catch (Exception ex) {
                    Toast.makeText(Profile.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomerDto> call, @NonNull Throwable t) {
                Toast.makeText(Profile.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void goHome(){
        Intent home = new Intent(this, ProductsActivity.class);
        startActivity(home);
    }
}