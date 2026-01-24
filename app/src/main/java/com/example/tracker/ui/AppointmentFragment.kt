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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.AppointmentAdapter
import com.example.tracker.model.Appointment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.time.LocalDateTime

class AppointmentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    override fun onResume() {
        super.onResume()
        requireActivity()
            .findViewById<TextView>(R.id.txtHeaderTitle)
            .text = "Appointments"
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarView = view.findViewById< MaterialCalendarView>(R.id.calendarAppointments)
        val today = CalendarDay.today()
        calendarView.setDateSelected(today, true)

        val mockDate = LocalDateTime.of(2003, 5, 2, 0,0,0,0)

        val appointments = listOf(
            Appointment(1, 1, "Check-up", "Urgent", "123 Plaza", mockDate, "Confirmed"),
            Appointment(1, 1, "Check-up", "Urgent", "123 Plaza", mockDate, "Confirmed"),
            Appointment(1, 1, "Check-up", "Urgent", "123 Plaza", mockDate, "Confirmed"),
            Appointment(1, 1, "Check-up", "Urgent", "123 Plaza", mockDate, "Confirmed"),
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAppointments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = AppointmentAdapter(appointments, {appointment ->
            val bottomSheet = AppointmentDetailsBottomSheet()
            bottomSheet.show(parentFragmentManager, "AppointmentDetailsBottomSheet")
        })

        val buttonAdd = view.findViewById<Button>(R.id.buttonAddAppointment)

        buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_appointment_to_appointmentForm)
        }
    }
}