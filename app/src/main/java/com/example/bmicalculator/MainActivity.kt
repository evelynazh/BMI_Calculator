package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAddres = findViewById<EditText>(R.id.editTextAddres)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            calculateBMI(editTextHeight, editTextWeight, radioGroupGender, textViewResult, editTextName, editTextAddres)
        }
        buttonReset.setOnClickListener{
            reset(editTextHeight, editTextWeight, textViewResult, editTextName, editTextAddres)
        }
    }

    private fun reset(
        editTextHeight: EditText, editTextWeight: EditText, textViewResult: TextView, editTextName: EditText, editTextAddres: EditText
    ) {
        editTextAddres.setText("")
        editTextHeight.setText("")
        editTextWeight.setText("")
        textViewResult.setText("")
        editTextName.setText("")

    }

    private fun calculateBMI(
        editTextHeight: EditText,
        editTextWeight: EditText,
        radioGroupGender: RadioGroup,
        textViewResult: TextView,
        editTextName: EditText,
        editTextAddres : EditText
    ) {
        val name = editTextName.text.toString()
        val addres = editTextAddres.text.toString()
        val height = editTextHeight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()
        // Memperoleh ID RadioBu1on yang dipilih
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        // Memeriksa jenis kelamin yang dipilih
        val gender = when (selectedGenderId) {
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"
        }
        // Menghitung BMI berdasarkan jenis kelamin
        val bmi = when (gender) {
            "Laki-laki" -> weight / ((height / 100) * (height / 100))
            "Perempuan" -> weight / ((height / 100) * (height / 100)) * 0.9
            else -> 0.0
        }
        val result = when {
            bmi < 18.5 -> "Berat badan kurang"
            bmi >= 18.5 && bmi < 24.9 -> "Berat badan normal"
            bmi >= 25 && bmi < 29.9 -> "Berat badan berlebih"
            else -> "Obesitas"
        }
        textViewResult.text = "$name \n$addres \nBMI: %.2f\n$result".format(bmi)
    }
}

