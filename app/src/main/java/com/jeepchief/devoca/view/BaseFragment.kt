package com.jeepchief.devoca.view

import android.content.Context
import androidx.fragment.app.Fragment
import com.jeepchief.devoca.MainActivity
import com.jeepchief.devoca.util.Log

open class BaseFragment : Fragment() {
    open lateinit var mContext: Context
    open lateinit var mActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("onContext in ${this.javaClass.name}")
        mContext = context
        mActivity = context as MainActivity
    }
}