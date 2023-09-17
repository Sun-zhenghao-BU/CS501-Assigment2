package com.example.arithmetic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

//    var OperationType = listOf("Add", "Subtract", "multiply", "Divide")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val operand1 : EditText = findViewById(R.id.operand1)
        val operand2 : EditText = findViewById(R.id.operand2)
        val spinnerOption : Spinner = findViewById(R.id.spinnerOption)
        val calculate : Button = findViewById(R.id.calculate)
        val showResult : TextView = findViewById(R.id.showResult)

        ArrayAdapter.createFromResource(
            this,
            R.array.operation_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerOption.adapter = adapter
        }
    }
}