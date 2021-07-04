package com.blackfrogweb.toxicscanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class ProductListViewModel(application: Application) : AndroidViewModel(application)
{
    private fun buildProductList() : List<ProductData>
    {
        val productList = Vector<ProductData>(10000)
        try {
            val `is` = InputStreamReader(getApplication<Application>().assets?.open("toxic.csv"))
            val reader = BufferedReader(`is`)
            reader.readLine()
            var line: String?

            val i = 0
            while (reader.readLine().also { line = it } != null)
            {
                val parts = line!!.split( ',')
                if(parts.size > 4)
                {
                    val pdtData = ProductData(parts[0], parts[1], parts[2], Date())
                    productList.add(pdtData)
                }
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
        return productList.toList()
    }

    private val productList : List<ProductData> = buildProductList()

    public val productAdapter : ProductItemAdapter = ProductItemAdapter(application, productList)
}