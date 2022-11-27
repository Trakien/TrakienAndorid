package co.trakien.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.trakien.R;
import co.trakien.models.ProductDto;
import co.trakien.models.StoreDto;

public class ProductsActivity extends AppCompatActivity {

    private List<ProductDto> products;
    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView = findViewById(R.id.rv_products);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        setProducts();
    }

    private void setProducts(){
        products = new ArrayList<ProductDto>();
        List<StoreDto> stores1 = new ArrayList<StoreDto>();
        List<String> prices1 = new ArrayList<String>();
        List<Date> dates1 = new ArrayList<Date>();
        dates1.add(new Date());
        prices1.add("12");
        stores1.add(new StoreDto("Ktronix","url",prices1,dates1));
        products.add(new ProductDto("0","0","celular1","celular","samsung",stores1));
        List<StoreDto> stores2 = new ArrayList<StoreDto>();
        List<String> prices2 = new ArrayList<String>();
        List<Date> dates2 = new ArrayList<Date>();
        dates2.add(new Date());
        prices2.add("100");
        stores2.add(new StoreDto("Alkomprar","url",prices2,dates2));
        products.add(new ProductDto("0","0","celular2","celular","apple",stores2));
        productsAdapter = new ProductsAdapter(products, getApplicationContext());
        recyclerView.setAdapter(productsAdapter);
    }
}