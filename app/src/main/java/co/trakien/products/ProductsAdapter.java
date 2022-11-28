package co.trakien.products;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import co.trakien.R;
import co.trakien.models.ProductDto;
import co.trakien.models.StoreDto;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<ProductDto> products;
    private Context context;

    public ProductsAdapter(List<ProductDto> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {
        ProductDto product = products.get(position);
        String img = getMinPrice(product.getStores(),3);
        if (img != null) {
        Picasso.get()
                .load(img)
                .error(R.mipmap.ic_launcher)
                .resize(200,250)
                .centerInside()
                .into(holder.imageProduct);
        }
        holder.name.setText("Nombre: " + product.getName());
        holder.brand.setText("Marca: " + product.getBrand());
        holder.price.setText("Precio: " + getMinPrice(product.getStores(),0));
        holder.store.setText("Tienda: " + getMinPrice(product.getStores(),1));
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl(getMinPrice(product.getStores(),2));
            }
        });
        //pendiente enlazar a grafica details
        //holder.details.callOnClick();
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        launchBrowser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(launchBrowser);
    }

    public static Bitmap LoadImageFromWebOperations(String url) {
        try {
            InputStream is = new URL(url).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private String getMinPrice(List<StoreDto> stores, int opt){
        int res = 0;
        String resStore = "";
        String link = "";
        String img = "";
        for(StoreDto store : stores){
            int price = Integer.parseInt(store.getPrices().get(0));
            if (price > res) {
                res = price;
                resStore = store.getName();
                link = store.getUrl();
                img = store.getImg();
            }
        }
        return opt == 0 ? String.valueOf(res) : opt == 1 ? resStore : opt == 2 ? link : img;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageProduct;
        private TextView name;
        private TextView brand;
        private TextView price;
        private TextView store;
        private Button link;
        private Button details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_name);
            brand = itemView.findViewById(R.id.card_brand);
            price = itemView.findViewById(R.id.card_price);
            store = itemView.findViewById(R.id.card_store);
            link = itemView.findViewById(R.id.card_link);
            details = itemView.findViewById(R.id.card_details);
        }
    }
}
