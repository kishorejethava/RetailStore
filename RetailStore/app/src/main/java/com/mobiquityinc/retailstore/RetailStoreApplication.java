package com.mobiquityinc.retailstore;

import android.app.Application;
import android.content.Context;

import com.mobiquityinc.retailstore.data.ProductFactory;
import com.mobiquityinc.retailstore.data.ProductService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KK on 10/7/2017.
 */

public class RetailStoreApplication extends Application{
    private ProductService productService;
    private Scheduler scheduler;

    private static RetailStoreApplication get(Context context) {
        return (RetailStoreApplication) context.getApplicationContext();
    }

    public static RetailStoreApplication create(Context context) {
        return RetailStoreApplication.get(context);
    }

    public ProductService getProductService() {
        if (productService == null) {
            productService = ProductFactory.create();
        }

        return productService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
