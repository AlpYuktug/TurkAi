package com.alpyuktug.turkai.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.Preference
import androidx.preference.PreferenceManager

class CustomSharedPrefences {

    companion object
    {
        private val PreferencesMail = "UserEmail"
        private var sharedPrefences : SharedPreferences? = null

        @Volatile private  var instance : CustomSharedPrefences? = null
        private  val lock = Any();
        operator  fun invoke(context: Context) : CustomSharedPrefences = instance ?: synchronized(lock)
        {
            instance ?:makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context) : CustomSharedPrefences {
            sharedPrefences = PreferenceManager.getDefaultSharedPreferences(context)
            return  CustomSharedPrefences()
        }
    }

    fun saveEmail(UserEmail: String)
    {
        sharedPrefences?. edit(commit = true)
        {
            putString(PreferencesMail,UserEmail)
        }
    }

    fun getEmail() = sharedPrefences?.getString(PreferencesMail,"")

}