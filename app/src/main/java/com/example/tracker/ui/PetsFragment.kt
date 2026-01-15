package com.example.tracker.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.PetAdapter
import com.example.tracker.database.AppDatabase
import com.example.tracker.database.DatabaseProvider
import com.example.tracker.model.Pet
import com.example.tracker.service.PetService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.time.LocalDate

class PetsFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var petService: PetService

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

//        val petList = listOf(
//            Pet(1, 0,"Buddy", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1, 0,"Max", "Dog", "Golden Retriever", "Female", LocalDate.of(2003,5, 21)),
//            Pet(1, 0,"Tucker", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1,0,"Buddy", "Dog", "Golden Retriever", "Female", LocalDate.of(2003,5, 21)),
//            Pet(1,0,"Max", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1, 0,"Tucker", "Dog", "Golden Retriever", "Female", LocalDate.of(2003,5, 21)),
//            Pet(1,0,"Buddy", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1,0,"Max", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1, 0,"Tucker", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1,0,"Buddy", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1,0,"Max", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21)),
//            Pet(1, 0,"Tucker", "Dog", "Golden Retriever", "Male", LocalDate.of(2003,5, 21))
//        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.petRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        db = DatabaseProvider.getDatabase(requireContext())
        petService = PetService(db.petDao())

        viewLifecycleOwner.lifecycleScope.launch {
            val userId = requireActivity().intent.getLongExtra("USER_ID", -1L)
            val petList = petService.findAllByUserId(userId)
            recyclerView.adapter = PetAdapter(petList) { pet ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2, PetProfileFragment())
                    .setTransition(TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(requireContext(), "Clicked: ${pet.name}", Toast.LENGTH_SHORT).show()
            }
        }

        val fabAddPet = view.findViewById<FloatingActionButton>(R.id.fab_add_pet)
        fabAddPet.setOnClickListener {
            val userId = requireActivity().intent.getLongExtra("USER_ID", -1L)
            val petForm = Intent(requireContext(), PetFormActivity:: class.java)
            petForm.putExtra("USER_ID", userId)
            startActivity(petForm)
        }


    }

}