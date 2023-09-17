package com.example.arithmetic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var operand1: EditText
    private lateinit var operand2: EditText
    private lateinit var spinnerOption: Spinner
    private lateinit var calculate: Button
    private lateinit var showResult: TextView

//    var OperationType = listOf("Add", "Subtract", "multiply", "Divide")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerOption = findViewById(R.id.spinnerOption)
        operand1 = findViewById(R.id.operand1)
        operand2 = findViewById(R.id.operand2)
        calculate = findViewById(R.id.calculate)
        showResult = findViewById(R.id.showResult)
        showResult.isSelected = true

        ArrayAdapter.createFromResource(
            this,
            R.array.operation_type,
            R.layout.spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_layout)
            spinnerOption.adapter = adapter
        }
        setInputWatcher(operand1)
        setInputWatcher(operand2)
        calculate()
    }

    private fun setInputWatcher(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 20) {
                    Toast.makeText(this@MainActivity, "Maximum input 20 digits", Toast.LENGTH_SHORT).show()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun calculate() {
        calculate.setOnClickListener {
            val num1Str = operand1.text.toString()
            val num2Str = operand2.text.toString()
            val num1 = operand1.text.toString().toDoubleOrNull()
            val num2 = operand2.text.toString().toDoubleOrNull()
            val selectedOperation = spinnerOption.selectedItem.toString()

//            Before calculate, clear the output of last calculation
            showResult.text = ""

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Please enter numbers as input", Toast.LENGTH_SHORT).show()
            } else {
                val result = when (selectedOperation) {
                    "Add" -> num1 + num2
                    "Subtract" -> num1 - num2
                    "Multiply" -> num1 * num2
                    "Divide" -> {
                        if (num2 != 0.0) {
                            num1 / num2
                        } else {
                            Toast.makeText(this, "Divide by Zero not allowed", Toast.LENGTH_SHORT).show()
                            null
                        }
                    }
                    "Module" -> {
                        if (num2 != 0.0) {
                            num1 % num2
                        } else {
                            Toast.makeText(this, "Module by Zero not allowed", Toast.LENGTH_SHORT).show()
                            null
                        }
                    }
                    else -> null
                }
                if (result == null) {
                    showResult.text = "Non-valid"
                } else {
                    var formattedResult = result.toString()
                    formattedResult = if (formattedResult.length > 12) {
                        String.format("%.2E", result)
                    } else {
                        String.format("%.2f", result)
                    }
                    showResult.text = formattedResult
                }
            }
        }
    }
}