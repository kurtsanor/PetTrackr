package com.example.tracker.ui

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tracker.R
import com.example.tracker.database.AppDatabase
import com.example.tracker.database.DatabaseProvider
import com.example.tracker.model.Pet
import com.example.tracker.service.PetService
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class PetFormActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var petService: PetService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pet_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        setupDatePicker()
        setupGenderDropdown()

        db = DatabaseProvider.getDatabase(this)
        petService = PetService(db.petDao())

        val petName = findViewById<TextInputEditText>(R.id.etPetName)
        val petType = findViewById<TextInputEditText>(R.id.etPetType)
        val petBreed = findViewById<TextInputEditText>(R.id.etBreed)
        val petGender = findViewById<AutoCompleteTextView>(R.id.actvGender)
        val petBirthdate = findViewById<TextInputEditText>(R.id.etBirthDate)

        val buttonAddPet = findViewById<Button>(R.id.buttonAddPet)
        buttonAddPet.setOnClickListener {
            lifecycleScope.launch {
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val newPet = Pet(
                    userId = intent.getLongExtra("USER_ID", -1L),
                    name = petName.text.toString(),
                    type = petType.text.toString(),
                    breed = petBreed.text.toString(),
                    gender = petGender.text.toString(),
                    birthDate = LocalDate.parse(petBirthdate.text.toString(), formatter)
                )
                try {
                    petService.insert(newPet)
                    Toast.makeText(this@PetFormActivity, "Pet has been added", Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: RuntimeException) {
                    Toast.makeText(this@PetFormActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


    private fun setupDatePicker() {
        val dateInput = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etBirthDate)

        dateInput.setOnClickListener {
            // 1. Create the Date Picker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Birth Date")
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

    private fun setupGenderDropdown() {
        // 1. Define the options
        val genders = arrayOf("Male", "Female", "Other")

        // 2. Create the adapter
        // R.layout.simple_exposed_dropdown_item is a default Material layout for dropdowns
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)

        // 3. Find the view and set the adapter
        val genderAutoComplete = findViewById< AutoCompleteTextView>(R.id.actvGender)
        genderAutoComplete.setAdapter(adapter)
    }
}