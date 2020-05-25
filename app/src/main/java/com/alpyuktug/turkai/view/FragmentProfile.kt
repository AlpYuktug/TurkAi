package com.alpyuktug.turkai.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.alpyuktug.turkai.R
import com.alpyuktug.turkai.model.User
import com.alpyuktug.turkai.service.RetrofitClient
import com.alpyuktug.turkai.util.CustomSharedPrefences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentProfile : Fragment() {

    var textViewEMail : TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

}
