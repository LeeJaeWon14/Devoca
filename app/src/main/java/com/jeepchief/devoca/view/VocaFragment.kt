package com.jeepchief.devoca.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jeepchief.devoca.R
import com.jeepchief.devoca.databinding.FragmentVocaBinding
import com.jeepchief.devoca.util.Log
import com.jeepchief.devoca.viewmodel.MainViewModel
import kotlin.random.Random

class VocaFragment : BaseFragment() {
    private var _binding: FragmentVocaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVocaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        Log.e("mContext >> $mContext")

        // init UI
        binding.apply {
            rlVocaLayout.setOnClickListener {
                viewModel.getVoca(mContext, makeRandomVid())
            }
            rlVocaLayout.performClick()
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            voca.observe(mActivity) { entity ->
                entity?.let {
                    try {
                        binding.apply {
                            tvVocaName.text = entity.vocaName
                            tvVocaDesc.text = entity.vocaDesc
                            entity.vocaFrom?.let { tvVocaFrom.text = it } ?: run { tvVocaFrom.isVisible = false }
                        }
                    } catch (e: Exception) {
                        Log.e(e.message!!)
                    }
                } ?: run {
                    try {
                        Toast.makeText(mContext, getString(R.string.msg_empty_voca_list), Toast.LENGTH_SHORT).show()
                    } catch (e: IllegalStateException) {
                        Log.e("Entity is null! \r\n${e.message}")
                        mActivity.onBackPressed()
                    }
                }
            }
        }
    }

    /**
     * Make randon vid for test.
     * @return vid: Int
     */
    private fun makeRandomVid() : Int = Random(5).nextInt()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}