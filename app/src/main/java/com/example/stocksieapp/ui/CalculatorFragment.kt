package com.example.stocksieapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.stocksieapp.R
import com.google.android.material.textfield.TextInputEditText

class CalculatorFragment : Fragment() {

    companion object {
        fun newInstance() = CalculatorFragment()
    }

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var textEdit: EditText;
    private lateinit var futureValue: TextView;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calculator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java);
        textEdit = view!!.findViewById(R.id.input_text);
        futureValue = view!!.findViewById(R.id.future_value);
        textEdit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                futureValue.setText(s);
            }
        });
        // TODO: Use the ViewModel
    }

}