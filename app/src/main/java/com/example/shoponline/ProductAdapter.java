package com.example.shoponline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    public List<ProductModel> productModelList = new ArrayList<>();
    public List<ProductModel> getProductModelListFilter = new ArrayList<>();
    public Context context;
    public ProductClickListener productClickListener;

    public ProductAdapter(List<ProductModel> productModels, Context context, ProductClickListener productClickListener) {
        this.productModelList = productModels;
        this.context = context;
        this.getProductModelListFilter = productModels;
        this.productClickListener = productClickListener;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0) {
                    filterResults.values = getProductModelListFilter;
                    filterResults.count = getProductModelListFilter.size();
                }
                else {
                    String searchStr = constraint.toString().toLowerCase();
                    List<ProductModel> productModels = new ArrayList<>();
                    for(ProductModel productModel : getProductModelListFilter) {
                        if(productModel.getProductName().toLowerCase().contains(searchStr)) {
                            productModels.add(productModel);
                        }
                    }
                    filterResults.values = productModels;
                    filterResults.count = productModels.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                productModelList = (List<ProductModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface ProductClickListener{
        void selectedProduct(ProductModel productModel);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_products, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel productModel = productModelList.get(position);
        String productName = productModel.getProductName();
        int imageView = productModel.getImageView();
        String price = productModel.getPrice();

        holder.productName.setText(productName);
        holder.imageView.setImageResource(imageView);
        holder.price.setText(price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClickListener.selectedProduct(productModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView productName;
        private TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);


        }
    }

}
