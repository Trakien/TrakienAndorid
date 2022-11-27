package co.trakien.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        //Pendiente agregar imagen
        //Glide.with(context).load(product.getImage);
        holder.name.setText("Nombre: " + product.getName());
        holder.brand.setText("Marca: " + product.getBrand());
        holder.price.setText("Precio: " + getMinPrice(product.getStores(),0));
        holder.store.setText("Tienda: " + getMinPrice(product.getStores(),1));
        //pendiente enlazar a link
        //holder.link.callOnClick();
        //pendiente enlazar a grafica details
        //holder.details.callOnClick();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private String getMinPrice(List<StoreDto> stores, int opt){
        int res = 0;
        String resStore = "";
        for(StoreDto store : stores){
            int price = Integer.parseInt(store.getPrices().get(0));
            if (price > res) {
                res = price;
                resStore = store.getName();
            }
        }
        return opt == 0 ? String.valueOf(res) : resStore;
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
