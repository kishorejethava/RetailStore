package com.mobiquityinc.retailstore.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.mobiquityinc.retailstore.R;
import com.mobiquityinc.retailstore.data.ProductFactory;
import com.mobiquityinc.retailstore.databinding.ActivityProductDetailBinding;
import com.mobiquityinc.retailstore.model.Product;
import com.mobiquityinc.retailstore.viewmodel.ProductDetailViewModel;

/**
 * Created by KK on 10/7/2017.
 */

public class ProductDetailActivity extends AppCompatActivity {
    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    private ActivityProductDetailBinding activityProductDetailBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(getClass().getName(),"onCreate");
        activityProductDetailBinding= DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        setSupportActionBar(activityProductDetailBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }
    public static Intent launchDetail(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, product);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        Product product = (Product) getIntent().getSerializableExtra(EXTRA_PRODUCT);
        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(product);
        Log.i(getClass().getName(),"getProductName:"+productDetailViewModel.getProductName());
        activityProductDetailBinding.setProductDetailViewModel(productDetailViewModel);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
