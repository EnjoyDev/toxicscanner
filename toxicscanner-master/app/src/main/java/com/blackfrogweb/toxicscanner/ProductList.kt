package com.blackfrogweb.toxicscanner

import android.content.Context
import android.content.res.Resources
import com.opencsv.CSVReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class ProductList private constructor(context: Context)
{
    val list : List<ProductData> = buildProductList(context)

    companion object {
        private var instance: ProductList? = null
        fun getInstance(context: Context): ProductList {
            if (instance == null) {
                instance = ProductList(context)
            }

            return instance!!
        }
    }

    fun searchProduct( barcode : String) : List<ProductData>
    {
        return list.filter { pdt -> pdt.barcode == barcode }
    }

    private fun buildProductList(context : Context) : List<ProductData>
    {
        var streamReader: InputStreamReader? = null
        var csvReader: CSVReader? = null
        val productList : MutableList<ProductData> = mutableListOf()
        try
        {
            streamReader = InputStreamReader(context.assets?.open("toxic.csv"))
            csvReader = CSVReader(streamReader)
            var record = csvReader.readNext()
            while ( record != null)
            {
                if(record.size >= 5)
                {
                    val pdtData = ProductData(record[0], record[1],  record[2], record[3], record[4])
                    productList.add(pdtData)
                }
                record = csvReader.readNext()
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
        finally {
            csvReader!!.close()
            streamReader!!.close()
        }
        return productList.toList()
    }
}