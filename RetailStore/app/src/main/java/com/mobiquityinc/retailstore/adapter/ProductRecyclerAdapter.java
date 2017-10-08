package com.mobiquityinc.retailstore.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mobiquityinc.retailstore.R;
import com.mobiquityinc.retailstore.databinding.ItemProductBinding;
import com.mobiquityinc.retailstore.model.Product;
import com.mobiquityinc.retailstore.viewmodel.ItemProductViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by wmtandroid5 on 27/9/17.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>{
    private String TAG=getClass().getName();
    private Context mContext;


    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
        notifyDataSetChanged();
    }

    private List<Product> productsList;
    public ProductRecyclerAdapter(Context context) {
        this.mContext = context;
        this.productsList= Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductBinding itemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_product,parent,false);
        return new ViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Product product = productsList.get(position);
        holder.bindProduct(product);
        /*holder.ivProduct.setLayoutParams(new LinearLayout.LayoutParams(width/2,width/2));
            //Show media from server
            holder.ivProduct.setDrawingCacheEnabled(true);
            Glide.with(mContext)
                    .load(product.getProductImage())
                    .override(width / 2, width / 2)
                    .into(holder.ivProduct);*/
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding mItemProductBinding;
        ViewHolder(ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.mItemProductBinding = itemProductBinding;
        }

        void bindProduct(Product product) {
            Log.i(TAG,"bindProduct:"+product.getProductImage());
            if (mItemProductBinding.getProductViewModel() == null) {
                mItemProductBinding.setProductViewModel(
                        new ItemProductViewModel(product, itemView.getContext()));
            } else {
                mItemProductBinding.getProductViewModel().setProduct(product);
            }
        }
    }
    @Override
    public int getItemCount() {
        return productsList.size();
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

}

