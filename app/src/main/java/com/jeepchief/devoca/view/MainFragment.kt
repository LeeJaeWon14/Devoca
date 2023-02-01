package com.jeepchief.devoca.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jeepchief.devoca.R
import com.jeepchief.devoca.databinding.FragmentMainBinding
import com.jeepchief.devoca.databinding.LayoutDialogVocaInputBinding
import com.jeepchief.devoca.model.database.VocaEntity
import com.jeepchief.devoca.ui.DialogHelper
import com.jeepchief.devoca.util.Log
import com.jeepchief.devoca.viewmodel.MainViewModel

class MainFragment : BaseFragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // api test observe
        viewModel.apiTest.observe(requireActivity()) {
            if(it.isSuccessful) {
                Toast.makeText(mContext, it.body(), Toast.LENGTH_SHORT).show()
            }
            else {
                it.errorBody()?.let { body ->
                    Log.e(body.string())
                } ?: run {
                    Log.e("errorBody is null!, ${it.message()}")
                }
            }
        }

        navController = Navigation.findNavController(view)
        binding.apply {
            btnVoca.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_vocaFragment)
            }
            btnSetting.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_settingFragment2)
            }
            btnInputVoca.setOnClickListener {
                val dlg = DialogHelper.customDialog(mContext, R.drawable.voca_desc_border) { dlg ->
                    LayoutDialogVocaInputBinding.inflate(layoutInflater).apply {
                        btnInputVocaComplete.setOnClickListener {
                            val button = it as Button
                            when(button.text.toString()) {
                                getString(R.string.button_name_close) -> {
//                                val database = Firebase.database("https://devoca-48989-default-rtdb.asia-southeast1.firebasedatabase.app/")
//                                val myRef = database.getReference("test")
//                                myRef.setValue(Random(100).nextInt().toString())
                                    dlg.dismiss()
                                }
                                getString(R.string.button_name_input_finish) -> {
                                    val vocaEntity = VocaEntity(
                                        vocaName = edtVocaName.text.toString(),
                                        vocaDesc = edtVocaDesc.text.toString(),
                                        vocaFrom = edtVocaFrom.text.toString()
                                    )
                                    viewModel.saveVoca(mContext, vocaEntity)

                                    dlg.dismiss()
                                }
                            }
                        }

                        edtVocaName.addTextChangedListener { changeButtonName(it, this) }
                        edtVocaDesc.addTextChangedListener { changeButtonName(it, this) }
                    }
                }
                dlg.show()
            }

            btnApiTest.setOnClickListener {
                viewModel.getApiTest()
            }
        }
    }

    private fun changeButtonName(it: Editable?, binding: LayoutDialogVocaInputBinding) {
        binding.apply {
            if(edtVocaName.text.isNotEmpty() && edtVocaDesc.text.isNotEmpty())
                btnInputVocaComplete.text = getString(R.string.button_name_input_finish)
            else
                btnInputVocaComplete.text = getString(R.string.button_name_close)
        }
    }
}