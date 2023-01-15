package com.jeepchief.devoca.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jeepchief.devoca.R
import com.jeepchief.devoca.databinding.FragmentMainBinding
import com.jeepchief.devoca.databinding.LayoutDialogVocaInputBinding

class MainFragment : BaseFragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

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

        navController = Navigation.findNavController(view)
        binding.apply {
            btnVoca.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_vocaFragment)
            }
            btnSetting.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_settingFragment2)
            }
            btnInputVoca.setOnClickListener {
                val dlgView = LayoutDialogVocaInputBinding.inflate(layoutInflater)
                val dlg = AlertDialog.Builder(mContext).create().apply {
                    setView(dlgView.root)
                    setCancelable(false)
                }

                dlgView.apply {
                    btnInputVocaComplete.setOnClickListener {
                        val button = it as Button
                        when(button.text.toString()) {
                            getString(R.string.button_name_close) -> {
                                dlg.dismiss()
                            }
                            getString(R.string.button_name_input_finish) -> {
//                                if(edtVocaName.text.toString().isEmpty() || edtVocaDesc.text.toString().isEmpty()) {
//                                    Toast.makeText(mContext, getString(R.string.msg_check_empty), Toast.LENGTH_SHORT).show()
//                                }
//                                else {
//                                    // Database code..
//                                    dlg.dismiss()
//                                }

                                // Will writing database code..
                                dlg.dismiss()
                            }
                        }
                    }

                    edtVocaName.addTextChangedListener { changeButtonName(it, this) }
                    edtVocaDesc.addTextChangedListener { changeButtonName(it, this) }
                }
            }
        }
    }

    private fun changeButtonName(it: Editable?, binding: LayoutDialogVocaInputBinding) {
        binding.apply {
            it?.toString()?.let { str ->
                if(str.isNotEmpty()) btnInputVocaComplete.text = getString(R.string.button_name_input_finish)
                else {
                    if(edtVocaName.text.isEmpty() && edtVocaDesc.text.isEmpty())
                        btnInputVocaComplete.text = getString(R.string.button_name_close)
                }
            }
        }
    }

    private val buttonCheckChangedListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            /* no-op */
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            /* no-op */
        }

        override fun afterTextChanged(it: Editable?) {

        }
    }
}