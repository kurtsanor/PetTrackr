package com.example.tracker.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.VaccinationAdapter
import com.example.tracker.model.Vaccination
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.time.LocalDate

class VaccinationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccination, container, false)
    }

    override fun onResume() {
        super.onResume()
        requireActivity()
            .findViewById<TextView>(R.id.txtHeaderTitle)
            .text = "Vaccinations"
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarView = view.findViewById< MaterialCalendarView>(R.id.calendarView)
        val today = CalendarDay.today()
        calendarView.setDateSelected(today, true)

        val vaccinations = listOf(
            Vaccination(1, 0, "Booster", "Max needs booster lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5)),
            Vaccination(1, 0, "Anti-Rabies", "Max needs rabies lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5)),
            Vaccination(1, 0, "Anti-Rabies", "Max needs rabies lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5)),
            Vaccination(1, 0, "Anti-Rabies", "Max needs rabies lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5)),
            Vaccination(1, 0, "Anti-Rabies", "Max needs rabies lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5)),
            Vaccination(1, 0, "Anti-Rabies", "Max needs rabies lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5)),
            Vaccination(1, 0, "Anti-Rabies", "Max needs rabies lorem ipsum dolor lorem", LocalDate.of(2025, 5, 5))
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewVaccination)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = VaccinationAdapter(vaccinations) { vaccination ->
            val bottomSheet = VaccinationDetailsBottomSheet()
            bottomSheet.show(parentFragmentManager, "VaccinationDetailsBottomSheet")
        }

        val buttonAdd = view.findViewById<Button>(R.id.buttonAddVaccine)

        buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_vaccinations_to_vaccinationForm)
        }
    }


}