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
import android.widget.Spinner
import android.widget.TextView
import com.example.stocksieapp.R
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.roundToInt
import android.widget.AdapterView




class CalculatorFragment : Fragment() {

    companion object {
        fun newInstance() = CalculatorFragment()
    }

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var textEdit: EditText;
    private lateinit var futureValue: TextView;
    private lateinit var instrumentDropdown: Spinner;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calculator_fragment, container, false)
    }

    fun compute_value(s: CharSequence): String {
        var value_string: String = s.toString();
        var value_int: Int = value_string.toInt();
        var future_value: Double = value_int * 1.0;
        var variance_percentage = 5.0;
        var pos = instrumentDropdown.selectedItemPosition;
        if (pos == 0) {
            future_value = (future_value * 1.15);
            variance_percentage = 5.0;
        } else if (pos == 1) {
            future_value = (future_value * 1.20);
            variance_percentage = 6.5;
        } else if (pos == 1) {
            future_value = (future_value * 1.30);
            variance_percentage = 7.1;
        } else if (pos == 2) {
            future_value = (future_value * 1.40);
            variance_percentage = 8.9;
        } else if (pos == 3) {
            future_value = (future_value * 2.00);
            variance_percentage = 12.4;
        } else {
            future_value = (future_value * 4.00);
            variance_percentage = 34.0;
        }
        return future_value.roundToInt().toString() + " +/- " + variance_percentage.toString() + "%";
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java);
        textEdit = view!!.findViewById(R.id.input_text);
        futureValue = view!!.findViewById(R.id.future_value);
        instrumentDropdown = view!!.findViewById(R.id.spinner);
        textEdit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                futureValue.setText(compute_value(s));
            }
        });
        instrumentDropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var s : String = textEdit.text.toString();
                if (s.isEmpty()) return;
                futureValue.setText(compute_value(s));
            }
        }
        // TODO: Use the ViewModel
    }

}