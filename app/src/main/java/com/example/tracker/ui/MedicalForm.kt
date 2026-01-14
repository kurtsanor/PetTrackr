package com.example.tracker.ui

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tracker.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MedicalForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_medical_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }
        setupDatePicker()
    }

    private fun setupDatePicker() {
        val dateInput = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etRecordDate)

        dateInput.setOnClickListener {
            // 1. Create the Date Picker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date of record")
                .setTheme(R.style.CustomDatePickerTheme)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            // 2. Show the picker
            datePicker.show(supportFragmentManager, "BIRTH_DATE_PICKER")

            // 3. Handle the positive button (Save/OK)
            datePicker.addOnPositiveButtonClickListener { selection ->
                // Format the date to a readable string
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dateString = formatter.format(Date(selection))
                dateInput.setText(dateString)
            }
        }
    }
}