package com.jeepchief.devoca.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.jeepchief.devoca.R
import com.jeepchief.devoca.databinding.LayoutFromViewBinding

class ThumbnailView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var binding = LayoutFromViewBinding.inflate(LayoutInflater.from(context), this, true)
    init {

    }
}