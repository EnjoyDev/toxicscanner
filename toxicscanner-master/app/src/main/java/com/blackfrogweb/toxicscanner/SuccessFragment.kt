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
        val verdict = safeArgs.result

        v.fragment_success_text_view_code.text = code

        if(verdict) {
            v.resultscan.text = "AVIS DE RAPPEL SUR LE PRODUIT"
            v.setBackgroundColor(Color.RED)
        }
        else {
            v.resultscan.text = "PRODUIT NON CONTAMINE"
            v.setBackgroundColor(Color.GREEN)
        }

        v.fragment_success_button_back_to_scanner.setOnClickListener {
            findNavController().navigateUp()
        }

        return v
    }

}