package com.blackfrogweb.toxicscanner

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductItemAdapter(application: Application,
                         dataSource: List<ProductData>) : BaseAdapter()
{
    private val inflater: LayoutInflater
            = application.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val data : List<ProductData> = dataSource

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
        View
        {
            val item = getItem(position) as ProductData
            val rowView = convertView?: inflater.inflate(R.layout.list_item_product, parent, false)
            val labelTextView = rowView.findViewById(R.id.textView_productItem_label) as TextView
            labelTextView.text = item.label
            val cbTextView = rowView.findViewById(R.id.textView_productItem_cb) as TextView
            cbTextView.text = item.barcode
            rowView.textView_productItem_batch.text = item.batch
            rowView.textView_productItem_date.text = item.endDate
            rowView.textView_productItem_brand.text = item.brand
            return rowView
        }

}