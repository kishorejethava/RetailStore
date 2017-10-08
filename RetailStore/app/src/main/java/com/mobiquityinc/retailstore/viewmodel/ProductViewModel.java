package com.mobiquityinc.retailstore.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.mobiquityinc.retailstore.RetailStoreApplication;
import com.mobiquityinc.retailstore.data.ProductFactory;
import com.mobiquityinc.retailstore.data.ProductResponse;
import com.mobiquityinc.retailstore.data.ProductService;
import com.mobiquityinc.retailstore.model.Product;
import com.mobiquityinc.retailstore.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by KK on 10/6/2017.
 */

public class ProductViewModel extends Observable  {
    private List<Product> productList;
    private Context context;
    public ObservableInt productRecycler;
    public ObservableInt noInternetMessage;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProductViewModel(@NonNull Context context) {

        this.context = context;
        this.productList = new ArrayList<>();
        productRecycler = new ObservableInt(View.VISIBLE);
        noInternetMessage = new ObservableInt(View.VISIBLE);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
    public void onTryAgain(View view){
        fetchProductList(ProductFactory.GET_PRODUCT_ALL);

    }
    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
    public void fetchProductList(String url) {
        if(NetworkUtil.isOnline(context)){
            noInternetMessage.set(View.GONE);
        }else{
            noInternetMessage.set(View.VISIBLE);
            return;
        }
        Log.i(getClass().getName(),"fetchProductList:"+url);
        RetailStoreApplication retailStoreApplication = RetailStoreApplication.create(context);
        ProductService productService = retailStoreApplication.getProductService();

        Disposable disposable = productService.fetchProductList(url)
                .subscribeOn(retailStoreApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductResponse>() {
                    @Override public void accept(ProductResponse productResponse) throws Exception {
                        Log.i("ProductResponse:",""+productResponse.getProductList());
                        changeProductDataSet(productResponse.getProductList());
                        productRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        Log.e("ProductResponse:",""+throwable.getMessage());
                    }
                });

        compositeDisposable.add(disposable);
    }
    private void changeProductDataSet(List<Product> products) {
        productList.clear();
        productList.addAll(products);
        setChanged();
        notifyObservers();
    }
}
