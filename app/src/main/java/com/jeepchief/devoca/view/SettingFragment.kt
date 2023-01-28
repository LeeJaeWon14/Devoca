package com.jeepchief.devoca.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeepchief.devoca.R
import com.jeepchief.devoca.databinding.FragmentSettingBinding
import com.jeepchief.devoca.ui.DialogHelper
import com.jeepchief.devoca.util.Log
import com.jeepchief.devoca.util.PreferenceManager

class SettingFragment : BaseFragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init UI
        binding.apply {
            ivBackButton.setOnClickListener {
                requireActivity().onBackPressed()
            }

            when(mActivity.isNetworkUse) {
                true -> rbNetwork.isSelected = true
                else -> rbLocal.isSelected = true
            }

            rgNetworkSetting.setOnCheckedChangeListener { radioGroup, i ->
                when(radioGroup.checkedRadioButtonId) {
                    R.id.rb_network -> {
                        rbNetwork.isSelected.run {
                            PreferenceManager.getInstance(mContext)?.setValue(PreferenceManager.USE_NETWORK, this).also {
                                Log.e("preference commit >> $it")
                            }
                            mActivity.isNetworkUse = this
                        }
                    }
                    R.id.rb_local -> {
                        PreferenceManager.getInstance(mContext)?.setValue(PreferenceManager.USE_NETWORK, rbNetwork.isSelected)
                    }
                }
            }

            btnRemoveVocaAll.setOnClickListener {
                DialogHelper.basicDialog(
                    mContext,
                    "경고",
                    "저장해두었던 단어들이 모두 삭제됩니다.\r\n정말 삭제하시겠습니까?",
                    positive = { dialogInterface, i ->
                        //todo: Remove vocabularies on db
                    },
                    negative = { _, _ -> }
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}