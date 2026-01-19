package com.example.tracker.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.PetAdapter
import com.example.tracker.model.Pet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate

class PetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pets, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .findViewById<TextView>(R.id.txtHeaderTitle)
            .text = "My Pets"

        val petList = listOf(
            Pet(null, 0,"Buddy", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null, 0,"Max", "Dog", "Golden Retriever", "Female", LocalDate.of(2003,5, 21)),
            Pet(null, 0,"Tucker", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null,0,"Buddy", "Dog", "Golden Retriever", "Female", LocalDate.of(2003,5, 21)),
            Pet(null,0,"Max", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null, 0,"Tucker", "Dog", "Golden Retriever", "Female", LocalDate.of(2003,5, 21)),
            Pet(null,0,"Buddy", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null,0,"Max", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null, 0,"Tucker", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null,0,"Buddy", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null,0,"Max", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
            Pet(null, 0,"Tucker", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21))
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.petRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = PetAdapter(petList) { pet ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetProfileFragment())
                .setTransition(TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
            Toast.makeText(requireContext(), "Clicked: ${pet.name}", Toast.LENGTH_SHORT).show()
        }

        val fabAddPet = view.findViewById<FloatingActionButton>(R.id.fab_add_pet)

        fabAddPet.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetFormActivityFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}