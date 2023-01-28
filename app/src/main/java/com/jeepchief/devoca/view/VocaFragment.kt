package com.jeepchief.devoca.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.jeepchief.devoca.R
import com.jeepchief.devoca.databinding.FragmentVocaBinding
import com.jeepchief.devoca.model.database.DevocaDatabase
import com.jeepchief.devoca.util.Log
import com.jeepchief.devoca.viewmodel.MainViewModel
import com.jeepchief.devoca.viewmodel.VocaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class VocaFragment : BaseFragment() {
    private var _binding: FragmentVocaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<VocaViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
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
                viewModel.getVoca(mContext, makeRandomVid(5))
            }
            rlVocaLayout.performClick()

            ivBackButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            voca.observe(requireActivity()) { entity ->
                try {
                    binding.apply {
                        tvVocaName.text = entity.vocaName
                        tvVocaDesc.text = entity.vocaDesc
                        entity.vocaFrom?.let { tvVocaFrom.text = it } ?: run { tvVocaFrom.visibility = View.INVISIBLE }
                    }
                } catch(e: NullPointerException) {
                    Toast.makeText(mContext, getString(R.string.msg_empty_voca_list), Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    /**
     * Make random vid for test.
     * @return vid: Int
     */
    private fun makeRandomVid(count: Int = 5) : Int = Random.nextInt(count).also { Log.e("Set vid count is $count") }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}