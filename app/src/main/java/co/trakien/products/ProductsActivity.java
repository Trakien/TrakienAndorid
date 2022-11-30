package co.trakien.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.trakien.LoginActivity;
import co.trakien.R;
import co.trakien.constants.Const;
import co.trakien.data.model.LoggedInUser;
import co.trakien.interfaces.FiltersApi;
import co.trakien.models.FiltersDto;
import co.trakien.models.ProductDto;
import co.trakien.products.elements.MultiSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsActivity extends AppCompatActivity {

    private List<ProductDto> products = new ArrayList<>();
    private List<String> categories;
    private List<String> brands;
    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private EditText searchFilter;
    private MultiSpinner brandsFilter;
    private MultiSpinner categoriesFilter;
    private FiltersDto filters = new FiltersDto("",new ArrayList<>(),Arrays.asList("Celulares"));
    private String token = LoggedInUser.getInstance().getToken();
    private ImageButton firstPage, prevPage, nextPage, lastPage;
    private TextView pagesText;
    private final Integer numProducts = 12;
    private Integer actPage = 0, pages;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        searchFilter = findViewById(R.id.searchName);
        brandsFilter = findViewById(R.id.brands);
        categoriesFilter = findViewById(R.id.categories);
        pagesText = findViewById(R.id.info_pages);
        firstPage = findViewById(R.id.first_page);
        prevPage = findViewById(R.id.prev_page);
        nextPage = findViewById(R.id.next_page);
        lastPage = findViewById(R.id.last_page);
        initCategories();
        initBrands();
        recyclerView = findViewById(R.id.rv_products);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        initSearchFilter();
        initPaginator();
    }

    private void initPaginator(){
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actPage = 0;
                setTextPages();
                setAdapter();
            }
        });
        prevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actPage > 0){
                    actPage--;
                    setTextPages();
                    setAdapter();
                }
            }
        });
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actPage < pages - 1){
                    actPage++;
                    setTextPages();
                    setAdapter();
                }
            }
        });
        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actPage =  pages - 1;
                setTextPages();
                setAdapter();
            }
        });
    }

    private void setTextPages(){
        pagesText.setText((actPage + 1) + "/" + pages);
    }

    private void initCategories(){
        Retrofit filterAPI = new Retrofit.Builder().baseUrl(Const.products_url).addConverterFactory(GsonConverterFactory.create()).build();
        FiltersApi filtersApiService = filterAPI.create(FiltersApi.class);
        Call<List<String>> call = filtersApiService.getAllCategories(token);

        call.enqueue(new Callback<List<String>>() {

            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()){
                    filters.setCategories(response.body());
                    categories = response.body();
                    setCategories();
                    setProducts();
                } else {
                    goLogin();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBrands(){
        Retrofit filterAPI = new Retrofit.Builder().baseUrl(Const.products_url).addConverterFactory(GsonConverterFactory.create()).build();
        FiltersApi filtersApiService = filterAPI.create(FiltersApi.class);
        Call<List<String>> call = filtersApiService.getAllBrands(token);

        call.enqueue(new Callback<List<String>>() {

            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()){
                    filters.setBrands(response.body());
                    brands = response.body();
                    setBrands();
                    setProducts();
                } else {
                    goLogin();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCategories(){
        categoriesFilter.setItems(categories, "Categoria", categoriesFilter.getSelectedItems());
        categoriesFilter.setListener(new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected, List<String> items) {
                if (items.size()>0)
                    filters.setCategories(items);
                else
                    filters.setCategories(categories);
                filterBrands();
                setProducts();
            }
        });

    }

    private void setBrands(){
        brandsFilter.setItems(brands, "Marca", brandsFilter.getSelectedItems());
        brandsFilter.setListener(new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected, List<String> items) {
                if (items.size()>0)
                    filters.setBrands(items);
                else
                    filters.setBrands(brands);
                filterCategories();
                setProducts();
            }
        });
        setProducts();
    }

    private void filterBrands(){
        Retrofit filterAPI = new Retrofit.Builder().baseUrl(Const.products_url).addConverterFactory(GsonConverterFactory.create()).build();
        FiltersApi filtersApiService = filterAPI.create(FiltersApi.class);
        Call<List<String>> call = filtersApiService.getFilterBrands(filters,token);

        call.enqueue(new Callback<List<String>>() {

            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()){
                    brands = response.body();
                    setBrands();
                } else {
                    goLogin();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterCategories(){
        Retrofit filterAPI = new Retrofit.Builder().baseUrl(Const.products_url).addConverterFactory(GsonConverterFactory.create()).build();
        FiltersApi filtersApiService = filterAPI.create(FiltersApi.class);
        Call<List<String>> call = filtersApiService.getFilterCategories(filters,token);

        call.enqueue(new Callback<List<String>>() {

            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()){
                    categories = response.body();
                    setCategories();
                } else {
                    goLogin();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSearchFilter(){
        searchFilter.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    filters.setSearch(String.valueOf(searchFilter.getText()));
                    filterBrands();
                    filterCategories();
                    setProducts();
                    return true;
                }
                return false;
            }
        });
    }

    private void setAdapter(){
        List<ProductDto> pageProducts = new ArrayList<>();
        if(actPage - 1 != pages)
            if (products.size() >= (actPage + 1) * numProducts) pageProducts = products.subList(actPage * numProducts, (actPage + 1) * numProducts);
        else
            if (products.size() > actPage) pageProducts = products.subList(actPage * numProducts, products.size());
        productsAdapter = new ProductsAdapter(pageProducts, getApplicationContext());
        recyclerView.setAdapter(productsAdapter);
    }

    private void setProducts(){
        Retrofit filterAPI = new Retrofit.Builder().baseUrl(Const.products_url).addConverterFactory(GsonConverterFactory.create()).build();
        FiltersApi filtersApiService = filterAPI.create(FiltersApi.class);
        Call<List<ProductDto>> call = filtersApiService.getAllFilter(filters,token);

        call.enqueue(new Callback<List<ProductDto>>() {

            @Override
            public void onResponse(Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
                if (response.isSuccessful()){
                    products = response.body();
                    actPage = 0;
                    pages = (int) Math.ceil(products.size() / numProducts);
                    setTextPages();
                    setAdapter();
                } else {
                    Toast.makeText(ProductsActivity.this, "Redireccion login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void goLogin(){
        Toast.makeText(ProductsActivity.this, "Vuelva a iniciar sesión", Toast.LENGTH_SHORT).show();
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}