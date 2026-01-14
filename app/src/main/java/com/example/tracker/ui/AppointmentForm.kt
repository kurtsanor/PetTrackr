package com.example.tracker.ui

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tracker.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AppointmentForm : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointment_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupDateTimePicker()
        val backBtn = findViewById<ImageButton>(R.id.btnBack)
        backBtn.setOnClickListener { finish() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDateTimePicker() {
        val dateInput = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etAppointmentDateTime)

        dateInput.setOnClickListener {

            // 1. Date Picker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Appointment Date")
                .setTheme(R.style.CustomDatePickerTheme)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener { selection ->

                // Convert timestamp to date
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selection

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1   // LocalDateTime uses 1–12
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // 2. Time Picker
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(9)
                    .setMinute(0)
                    .setTitleText("Select Time")
                    .setTheme(R.style.CustomTimePickerTheme)
                    .build()

                timePicker.show(supportFragmentManager, "TimePicker")

                timePicker.addOnPositiveButtonClickListener {

                    val hour = timePicker.hour
                    val minute = timePicker.minute

                    // 3. Convert to LocalDateTime
                    val selectedDateTime = LocalDateTime.of(year, month, day, hour, minute)

                    // 4. Format for display (UI only)
                    val uiFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a")
                    val formatted = selectedDateTime.format(uiFormat)

                    // 5. Display in text field
                    dateInput.setText(formatted)

                    // OPTIONAL: If you want to store it somewhere
                    // appointmentDateTime = selectedDateTime
                }
            }
        }
    }


}