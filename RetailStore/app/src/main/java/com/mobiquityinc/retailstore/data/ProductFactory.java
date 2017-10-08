/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobiquityinc.retailstore.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFactory {

  private final static String BASE_URL = "http://demo2672332.mockable.io/";
  public final static String GET_PRODUCT_ALL = "http://demo2672332.mockable.io/getProductList";
  public final static String GET_PRODUCT_FURNITURE = "http://demo2672332.mockable.io/getFurnitureProductList";
  public final static String GET_PRODUCT_ELECTRONICS = "http://demo2672332.mockable.io/getElectronicsProductList";

  public static ProductService create() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(ProductService.class);
  }
}
