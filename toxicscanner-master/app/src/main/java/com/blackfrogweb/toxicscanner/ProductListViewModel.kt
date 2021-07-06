package com.blackfrogweb.toxicscanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ProductListViewModel(application: Application) : AndroidViewModel(application)
{
    val productAdapter : ProductItemAdapter = ProductItemAdapter(application, ProductList.getInstance(application.applicationContext).list)
}