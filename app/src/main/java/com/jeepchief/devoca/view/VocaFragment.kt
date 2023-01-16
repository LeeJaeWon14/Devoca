package com.jeepchief.devoca.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.jeepchief.devoca.databinding.FragmentVocaBinding
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
            voca.observe(requireActivity()) { entity ->
                binding.apply {
                    tvVocaName.text = entity.vocaName
                    tvVocaDesc.text = entity.vocaDesc
                    entity.vocaFrom?.let { tvVocaFrom.text = it } ?: run { tvVocaFrom.isVisible = false }
                }
            }
        }
    }

    private fun makeRandomVid() : Int = Random(5).nextInt()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}