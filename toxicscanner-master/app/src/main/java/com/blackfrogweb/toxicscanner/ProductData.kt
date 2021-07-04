package com.blackfrogweb.toxicscanner

import java.util.*

class ProductData(barcode : String = "", label : String = "", batch : String ="", endDate : Date = Date())
{
    public var barcode : String = barcode
    public var label : String = label
    public var batch : String = batch
    public var endDate : Date = endDate
}
