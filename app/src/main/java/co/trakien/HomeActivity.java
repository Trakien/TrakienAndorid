package co.trakien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import co.trakien.products.ProductsActivity;

public class HomeActivity extends AppCompatActivity {

    ImageButton productos;
    ImageButton perfil;
    ImageButton trends;
    ImageButton avisos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        productos = findViewById(R.id.busquedaBtn);
        productos.setOnClickListener(view->goProducts());
        perfil = findViewById(R.id.profileBtn);
        perfil.setOnClickListener(view->goPerfil());
        trends = findViewById(R.id.trendsBtn);
        trends.setOnClickListener(view->goTrends());
        avisos = findViewById(R.id.avisoBtn);
        avisos.setOnClickListener(view->goAvisos());
    }

    public void goProducts(){
        Intent products = new Intent(this, ProductsActivity.class);
        startActivity(products);
    }

    public void goPerfil(){
        Intent profile = new Intent(this, HomeActivity.class);
        startActivity(profile);
    }

    public void goTrends(){
        Intent trend = new Intent(this, TrendsActivity.class);
        startActivity(trend);
    }

    public void goAvisos(){
        Intent alert = new Intent(this, HomeActivity.class);
        startActivity(alert);
    }
}