package com.jeepchief.devoca.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context : Context) {
    interface OnDataChanged {
        fun onDataChanged(id : String?, data : String = "DELETE")
    }
    private val PREF_NAME = "PreferenceOfDevoca"
    private var preference : SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREF_DATA = "Preference"
        const val USE_NETWORK = "NETWORK"

        private var instance : PreferenceManager? =null
        @Synchronized
        fun getInstance(context: Context) : PreferenceManager? {
            if(instance == null)
                instance = PreferenceManager(context)

            return instance
        }
    }

    fun getString(id: String?) : String? {
        return preference.getString(id, "")
    }

    fun getBoolean(id: String?) : Boolean {
        return preference.getBoolean(id, false)
    }

    fun setValue(id: String?, value: String) : Boolean {
        return preference.edit()
            .putString(id, value)
            .commit()
    }

    fun setValue(id: String?, value: Boolean) : Boolean {
        return preference.edit()
            .putBoolean(id, value)
            .commit()
    }

    fun removeValue(id: String?) : Boolean {
        return preference.edit()
            .remove(id)
            .commit()
    }
}