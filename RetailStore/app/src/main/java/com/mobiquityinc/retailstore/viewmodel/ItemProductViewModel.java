package com.mobiquityinc.retailstore.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mobiquityinc.retailstore.model.Product;
import com.mobiquityinc.retailstore.view.ProductDetailActivity;
import com.mobiquityinc.retailstore.view.ProductsActivity;

/**
 * Created by KK on 10/7/2017.
 */

public class ItemProductViewModel extends BaseObservable {
    private Product product;
    private Context context;

    public ItemProductViewModel(Product product, Context context) {
        this.product = product;
        this.context = context;
    }
    public String getProductName() {
        return product.getProductName();
    }

    public String getProductPrice() {
        return product.getProductPrice();
    }

    public String getProductImage() {
        return product.getProductImage();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        Log.i(getClass().getName(),"onItemClick");
        context.startActivity(ProductDetailActivity.launchDetail(view.getContext(), product));
    }

    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }
}
