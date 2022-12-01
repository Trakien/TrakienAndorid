package co.trakien.options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import co.trakien.HomeActivity;
import co.trakien.R;
import co.trakien.products.ProductsActivity;

public class ActivityAviso extends AppCompatActivity {
    Button avisar, volver;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);
        info = findViewById(R.id.info);
        avisar = findViewById(R.id.avisar);
        volver = findViewById(R.id.volver);

        avisar.setOnClickListener(view -> setAviso());
        volver.setOnClickListener(view -> goHome());
    }

    private void setAviso(){
        info.setText("Sobrepaso precio seleccionado");
    }

    public void goHome(){
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }
}