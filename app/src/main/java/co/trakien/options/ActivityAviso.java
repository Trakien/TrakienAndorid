package co.trakien.options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.trakien.HomeActivity;
import co.trakien.R;
import co.trakien.products.ProductsActivity;

public class ActivityAviso extends AppCompatActivity {
    Button avisar, volver;
    EditText producto,precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);
        avisar = findViewById(R.id.volver2);
        volver = findViewById(R.id.volver);
        producto = findViewById(R.id.editProducto);
        precio = findViewById(R.id.editPrecio);
        avisar.setOnClickListener(view -> setAviso());
        volver.setOnClickListener(view -> goHome());
    }

    private void setAviso(){
        if((producto.getText().toString().equals("") && producto.getText().toString().equals(""))){
            System.out.println(producto.getText().toString());
            Toast.makeText(getApplicationContext(), "Ingrese los campos Requeridos", Toast.LENGTH_LONG).show();
            this.producto.setError("Campo Requerido");
            this.precio.setError("Campo Requerido");
        }else{
        Toast.makeText(getApplicationContext(),"Alerta creada con exito",Toast.LENGTH_SHORT).show();
        goHome();
        }
    }

    public void goHome(){
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }
}