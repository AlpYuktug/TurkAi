package com.alpyuktug.turkai.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation

import com.alpyuktug.turkai.R
import com.alpyuktug.turkai.service.RetrofitClient
import com.alpyuktug.turkai.util.CustomSharedPrefences
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.fragment_create_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentCreatePost : Fragment() {

    var editTextPost: EditText? = null
    var imageViewPost: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextPost = view.findViewById(R.id.editTextPost)
        imageViewPost = view.findViewById(R.id.imageViewPost)

        imageViewPost!!.setOnClickListener { SharePost() }
    }

    fun SharePost() {
        imageViewPost!!.isEnabled = true

        val customSharedPrefences = CustomSharedPrefences()

        val email = customSharedPrefences.getEmail()!!
        val postdetail = editTextPost!!.text.toString().trim()
        val picture = "https://alperenyukselaltug.com/api/TurkAi/picture/woman.png"

        RetrofitClient.instance.SharePost(email,picture,postdetail,0.0)
            .enqueue(object: Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if(response.body().toString() == "Success")
                    {
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }
                    if(response.body().toString() == "Fail")
                    {
                    }

                }

            })
    }

}
