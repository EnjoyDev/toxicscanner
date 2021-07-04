package com.blackfrogweb.toxicscanner

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

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
            val rowView = inflater.inflate(R.layout.list_item_product, parent, false)
            val labelTextView = rowView.findViewById(R.id.textView_productItem_label) as TextView
            labelTextView.text = item.label
            val cbTextView = rowView.findViewById(R.id.textView_productItem_cb) as TextView
            cbTextView.text = item.barcode
            val batchTextView: TextView = rowView.findViewById(R.id.textView_productItem_batch)
            batchTextView.text = item.batch
            val dateTextView: TextView = rowView.findViewById(R.id.textView_productItem_date)
            dateTextView.text = item.endDate
            return rowView
        }

}