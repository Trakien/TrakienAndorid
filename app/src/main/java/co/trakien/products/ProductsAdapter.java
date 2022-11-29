package co.trakien.products;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.trakien.R;
import co.trakien.models.ProductDto;
import co.trakien.models.StoreDto;
import co.trakien.products.elements.MultiSpinner;

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
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.name.setText("Nombre: " + product.getName());
        holder.brand.setText("Marca: " + product.getBrand());
        holder.price.setText("Precio: " + formatter.format(Integer.parseInt(getMinPrice(product.getStores(),0))).replace(".00",""));
        holder.store.setText("Tienda: " + getMinPrice(product.getStores(),1));
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl(getMinPrice(product.getStores(),2));
            }
        });
        if(product.isVisibility()){
            holder.details.setText("CERRAR");
            holder.graphLayout.setVisibility(View.VISIBLE);
        } else {
            holder.details.setText("DETALLES");
            holder.graphLayout.setVisibility(View.GONE);
        }
        List<String> stores = new ArrayList<>();
        ArrayList<String> xAxe = (ArrayList<String>) product.getXChartInfo();
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        for (StoreDto store : product.getStores()){
            List<Entry> entryList = new ArrayList<>();
            for (int i = 0 ; i < xAxe.size() ; i++){
                entryList.add(new Entry(i,store.getPriceAtDate(xAxe.get(i))));
            }
            LineDataSet values = new LineDataSet(entryList,store.getName());
            stores.add(store.getName());
            int color = store.getName().equals("Alkosto") ? Color.rgb(235, 91, 37) : store.getName().equals("Ktronix") ? Color.BLUE : Color.YELLOW;
            values.setColor(color);
            values.setLineWidth(2f);
            values.setValueTextSize(0);
            lineDataSets.add(values);
        }
        holder.graph.setData(new LineData(lineDataSets));
        Description d = holder.graph.getDescription();
        d.setText("Trakien");
        holder.graph.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                d.setText(xAxe.get((int)e.getX()) + ": " + e.getY());
            }

            @Override
            public void onNothingSelected() {
                d.setText("Trakien");
            }
        });
        holder.graph.setScaleEnabled(false);
        holder.graph.setDragEnabled(false);
        holder.graph.getAxisRight().setEnabled(false);
        YAxis leftAxis = holder.graph.getAxisLeft();
        leftAxis.setGranularity(1f);
        XAxis xAxis = holder.graph.getXAxis();
        xAxis.setValueFormatter( new IndexAxisValueFormatter(xAxe) );
        Legend legend = holder.graph.getLegend();
        legend.setEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        holder.storeFilter.setItems(stores, "Tiendas",new ArrayList<>());
        holder.storeFilter.setListener(new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected, List<String> items) {
                if(items.size()>0){
                    List<ILineDataSet> filteredLines = new ArrayList<>();
                    for (ILineDataSet line : lineDataSets){
                        if(items.contains(line.getLabel())){
                            filteredLines.add(line);
                        }
                    }
                    holder.graph.setData(new LineData(filteredLines));
                    holder.graph.setVisibility(View.INVISIBLE);
                    holder.graph.setVisibility(View.VISIBLE);
                } else {
                    holder.graph.setData(new LineData(lineDataSets));
                    holder.graph.setVisibility(View.INVISIBLE);
                    holder.graph.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        launchBrowser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(launchBrowser);
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
        private TextView name, brand, price, store;
        private Button link, details;
        private LineChart graph;
        private ConstraintLayout graphLayout;
        private MultiSpinner storeFilter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_name);
            brand = (TextView) itemView.findViewById(R.id.card_brand);
            price = (TextView) itemView.findViewById(R.id.card_price);
            store = (TextView) itemView.findViewById(R.id.card_store);
            link = (Button) itemView.findViewById(R.id.card_link);
            details = (Button) itemView.findViewById(R.id.card_details);
            graph = (LineChart) itemView.findViewById(R.id.lineChart);
            storeFilter = (MultiSpinner) itemView.findViewById(R.id.storesFilter);
            graphLayout = (ConstraintLayout) itemView.findViewById(R.id.graph_layout);

            details.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ProductDto product = products.get(getAdapterPosition());
                    product.setVisibility(!product.isVisibility());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
