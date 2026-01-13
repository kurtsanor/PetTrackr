package com.example.tracker.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.MedicalRecordAdapter
import com.example.tracker.adapter.VaccinationAdapter
import com.example.tracker.model.MedicalRecord
import java.time.LocalDate

class MedicalHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_history, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val medicals = listOf(
            MedicalRecord(null, 0, "General Check-up", LocalDate.of(2025, 1, 1), "Fever", "Anti-biotics", "Take care"),
            MedicalRecord(null, 0, "General Check-up", LocalDate.of(2025, 1, 1), "Fever", "Anti-biotics", "Take care"),
            MedicalRecord(null, 0, "General Check-up", LocalDate.of(2025, 1, 1), "Fever", "Anti-biotics", "Take care"),
            MedicalRecord(null, 0, "General Check-up", LocalDate.of(2025, 1, 1), "Fever", "Anti-biotics", "Take care"),
            MedicalRecord(null, 0, "General Check-up", LocalDate.of(2025, 1, 1), "Fever", "Anti-biotics", "Take care"),
            MedicalRecord(null, 0, "General Check-up", LocalDate.of(2025, 1, 1), "Fever", "Anti-biotics", "Take care"),
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMedical)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MedicalRecordAdapter(medicals)
    }


}