package co.trakien;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.trakien.constants.Const;
import co.trakien.data.model.LoggedInUser;
import co.trakien.interfaces.CustomerApi;
import co.trakien.models.CustomerDto;
import co.trakien.models.LoginDto;
import co.trakien.models.TokenDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {


    Button registrarse;
    EditText email;
    EditText password;
    EditText name;
    EditText lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registrarse = findViewById(R.id.button2);
        email = findViewById(R.id.editTextTextEmailAddress4);
        password = findViewById(R.id.editTextTextPassword3);
        name = findViewById(R.id.editTextTextPersonName5);
        lastName = findViewById(R.id.editTextTextPersonName6);
        registrarse.setOnClickListener(v-> register(name.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString()));
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Email invalido");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    private void register(String name, String lastName,String email, String password){
        if(email.isEmpty() && password.isEmpty() && name.isEmpty() && lastName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingrese los campos Requeridos", Toast.LENGTH_LONG).show();
            this.email.setError("Campo Requerido");
            this.password.setError("Campo Requerido");
            this.name.setError("Campo Requerido");
            this.lastName.setError("Campo Requerido");
        }else{
        Retrofit customerAPI = new Retrofit.Builder().baseUrl(Const.customers_url).addConverterFactory(GsonConverterFactory.create()).build();
        CustomerApi customerApiService =customerAPI.create(CustomerApi.class);
        Call<CustomerDto> res = customerApiService.create(new CustomerDto("",name,password,email,lastName,""));
        res.enqueue(new Callback<CustomerDto>() {
            @Override
            public void onResponse(Call<CustomerDto> call, Response<CustomerDto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cuenta creada", Toast.LENGTH_LONG).show();
                    goLogin();
                }

            }

            @Override
            public void onFailure(Call<CustomerDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });}
    }
    public void goLogin(){
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}