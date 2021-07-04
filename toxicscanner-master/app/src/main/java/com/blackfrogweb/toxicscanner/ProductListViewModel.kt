package com.blackfrogweb.toxicscanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import com.opencsv.CSVReader

class ProductListViewModel(application: Application) : AndroidViewModel(application)
{
    private fun buildProductList() : List<ProductData>
    {
        var streamReader: InputStreamReader? = null
        var csvReader: CSVReader? = null
        val productList = Vector<ProductData>(10000)
        try
        {
            streamReader = InputStreamReader(getApplication<Application>().assets?.open("toxic.csv"))
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
            csvReader.close()
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

    fun searchProduct( barcode : String) : ProductData?
    {
        return productList.find { pdt -> pdt.barcode == barcode}
    }

    private val productList : List<ProductData> = buildProductList()

    val productAdapter : ProductItemAdapter = ProductItemAdapter(application, productList)
}