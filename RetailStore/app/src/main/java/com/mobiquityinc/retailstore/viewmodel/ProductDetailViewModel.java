package com.mobiquityinc.retailstore.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mobiquityinc.retailstore.model.Product;
import com.mobiquityinc.retailstore.view.ProductDetailActivity;

/**
 * Created by KK on 10/7/2017.
 */

public class ProductDetailViewModel extends BaseObservable {
    private Product product;

    public ProductDetailViewModel(Product product) {
        this.product = product;
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

    public String getCategory() {
        return product.getCategory();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }
}
