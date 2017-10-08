package com.mobiquityinc.retailstore.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mobiquityinc.retailstore.R;
import com.mobiquityinc.retailstore.adapter.ProductRecyclerAdapter;
import com.mobiquityinc.retailstore.data.ProductFactory;
import com.mobiquityinc.retailstore.databinding.ActivityProductsBinding;
import com.mobiquityinc.retailstore.viewmodel.ProductViewModel;

import java.util.Observable;
import java.util.Observer;

public class ProductsActivity extends AppCompatActivity implements Observer{

    private ProductViewModel productViewModel;
    private ActivityProductsBinding activityProductsBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        initDataBinding();
        setSupportActionBar(activityProductsBinding.toolbar);
        setupListProductView(activityProductsBinding.listProduct);
        setupObserver(productViewModel);
    }

    /**
     * Initialize components
     */
    private void initDataBinding() {
        activityProductsBinding = DataBindingUtil.setContentView(this, R.layout.activity_products);
        productViewModel = new ProductViewModel(this);
        activityProductsBinding.setMainViewModel(productViewModel);
        productViewModel.fetchProductList(ProductFactory.GET_PRODUCT_ALL);
    }

    /**
     * setup List Adapter
     * @param listProduct List of Product
     */
    private void setupListProductView(RecyclerView listProduct) {
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(this);
        listProduct.setAdapter(adapter);
        listProduct.setLayoutManager(new GridLayoutManager(this,2));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        productViewModel.reset();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSortFurniture) {
            productViewModel.fetchProductList(ProductFactory.GET_PRODUCT_FURNITURE);
            return true;
        }else if(item.getItemId() == R.id.menuSortElectronics){
            productViewModel.fetchProductList(ProductFactory.GET_PRODUCT_ELECTRONICS);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void update(Observable observable, Object o) {
        Log.i(getClass().getName(),"update");
        if (observable instanceof ProductViewModel) {
            ProductRecyclerAdapter productRecyclerAdapter = (ProductRecyclerAdapter) activityProductsBinding.listProduct.getAdapter();
            ProductViewModel productViewModel = (ProductViewModel) observable;
            productRecyclerAdapter.setProductsList(productViewModel.getProductList());
        }
    }
}
