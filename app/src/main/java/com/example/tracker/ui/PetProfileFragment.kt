package com.example.tracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tracker.R

class PetProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonVaccination = view.findViewById< Button>(R.id.buttonVaccination)
        buttonVaccination.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, VaccinationFragment())
                .addToBackStack(null)
                .commit()
        }

        val buttonMedicalHistory = view.findViewById< Button>(R.id.buttonMedicalHistory)
        buttonMedicalHistory.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, MedicalHistoryFragment())
                .addToBackStack(null)
                .commit()
        }

        val buttonGrowth = view.findViewById< Button>(R.id.buttonGrowth)
        buttonGrowth.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, GrowthFragment())
                .addToBackStack(null)
                .commit()
        }

        val buttonDocuments = view.findViewById< Button>(R.id.buttonDocuments)
        buttonDocuments.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, DocumentsFragment())
                .addToBackStack(null)
                .commit()
        }
    }



}