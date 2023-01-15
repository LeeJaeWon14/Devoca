package com.jeepchief.devoca.view

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    open lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}