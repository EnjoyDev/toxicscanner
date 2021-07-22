package com.blackfrogweb.toxicscanner

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_success.view.*

class SuccessFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_success, container, false)

        val safeArgs: SuccessFragmentArgs by navArgs()
        val code = safeArgs.code
        val matchedProductList = ProductList.getInstance(requireContext()).searchProduct(code)

        v.fragment_success_title
        v.fragment_success_text_view_code.text = code

        if(matchedProductList.isNotEmpty()) {
            v.setBackgroundColor(Color.RED)
            v.fragment_success_title.text =  "AVIS DE RAPPEL SUR LE PRODUIT : " + matchedProductList[0].label
            v.fragment_success_text_view_batchsAndDates.text =
                matchedProductList.joinToString("\n") {
                        p -> p.batch + " : " + p.endDate
                }
        }
        else {
            v.setBackgroundColor(Color.GREEN)
            v.fragment_success_title.text = "Produit non concerné"
            v.fragment_success_text_view_batchsAndDates.text = "code non référencé dans la liste des produits intoxiqués"
        }

        v.fragment_success_button_back_to_scanner.setOnClickListener {
            findNavController().navigateUp()
        }

        return v
    }

}