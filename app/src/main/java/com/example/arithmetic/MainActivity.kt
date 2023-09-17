package com.example.arithmetic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        ArrayAdapter.createFromResource(
            this,
            R.array.operation_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerOption.adapter = adapter
        }
        calculate()
    }

    private fun calculate() {
        calculate.setOnClickListener {
            val num1 = operand1.text.toString().toDoubleOrNull()
            val num2 = operand2.text.toString().toDoubleOrNull()
            val selectedOperation = spinnerOption.selectedItem.toString()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
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
                    val formattedResult = String.format("%.2f", result)
                    showResult.text = formattedResult
                }
            }
        }
    }
}