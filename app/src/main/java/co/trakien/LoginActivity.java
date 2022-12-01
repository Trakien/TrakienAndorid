package co.trakien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.trakien.constants.Const;
import co.trakien.data.model.LoggedInUser;
import co.trakien.interfaces.CustomerApi;
import co.trakien.models.LoginDto;
import co.trakien.models.TokenDto;
import co.trakien.options.Profile;
import co.trakien.products.ProductsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    Button registrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        final EditText password = findViewById(R.id.editTextTextPassword2);
        final Button iniciarSesion = findViewById(R.id.button);
        registrarse = findViewById(R.id.button2);
        registrarse.setOnClickListener(v-> goRegister());
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    iniciarSesion.setEnabled(true);
                }else{
                    iniciarSesion.setEnabled(false);
                    email.setError("Correo invalido");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iniciarSesion.setOnClickListener(v -> login(email.getText().toString(),password.getText().toString()));

    }
    public LoggedInUser user;

    private void login(String email, String password){
        Retrofit customerAPI = new Retrofit.Builder().baseUrl(Const.customers_url).addConverterFactory(GsonConverterFactory.create()).build();
        CustomerApi customerApiService =customerAPI.create(CustomerApi.class);
        Call<TokenDto> res = customerApiService.login(new LoginDto(email,password));
        user = LoggedInUser.getInstance();
        user.setEmail(email);
        res.enqueue(new Callback<TokenDto>() {
            @Override
            public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                if(response.isSuccessful()){
                    user.setToken(response.body().getToken());
                    goHome();
                }else {
                    Toast.makeText(getApplicationContext(), "Datos Incorrectos", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<TokenDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void goHome(){
        Intent home = new Intent(this, Profile.class);
        startActivity(home);
    }
    public void goRegister(){
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }
}