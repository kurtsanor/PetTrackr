package com.example.tracker.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
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
import java.lang.RuntimeException
import java.time.LocalDate

class PetsFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var petService: PetService

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pets, container, false)
    }

    override fun onResume() {
        super.onResume()

        loadPets()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.petRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        db = DatabaseProvider.getDatabase(requireContext())
        petService = PetService(db.petDao())

        val fabAddPet = view.findViewById<FloatingActionButton>(R.id.fab_add_pet)
        fabAddPet.setOnClickListener {
            val userId = requireActivity().intent.getLongExtra("USER_ID", -1L)
            val petForm = Intent(requireContext(), PetFormActivity:: class.java)
            petForm.putExtra("USER_ID", userId)
            startActivity(petForm)
        }
    }

    suspend fun deleteById(id: Long) {
            try {
                petService.deleteById(id)
            } catch (e: RuntimeException) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun loadPets() {
        viewLifecycleOwner.lifecycleScope.launch {
            val userId = requireActivity().intent.getLongExtra("USER_ID", -1L)
            val petList = petService.findAllByUserId(userId).toMutableList()
            val placeholder: LinearLayout? = view?.findViewById(R.id.placeholder_empty_pet)
            if (petList.isEmpty()) {
                placeholder?.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                placeholder?.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            recyclerView.adapter = PetAdapter(petList) { pet ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2, PetProfileFragment())
                    .setTransition(TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(requireContext(), "Clicked: ${pet.name}", Toast.LENGTH_SHORT).show()
            }

            // Swipe functionality
            val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                // We are not doing drag & drop
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                // Called when an item is swiped
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val pet = petList[position] // your list

                    // Show confirmation dialog
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete Pet")
                        .setMessage("Are you sure you want to delete ${pet.name}?")
                        .setPositiveButton("Yes") { dialog, _ ->
                            lifecycleScope.launch {
                                deleteById(pet.id)
                            }
                            // User confirmed, remove item from session only
                            petList.removeAt(position)
                            recyclerView.adapter?.notifyItemRemoved(position)
                            Toast.makeText(requireContext(), "${pet.name} deleted", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            // User cancelled, reset the item so it doesn’t disappear
                            recyclerView.adapter?.notifyItemChanged(position)
                            dialog.dismiss()
                        }
                        .setCancelable(false)
                        .show()

                    // Show a simple toast on swipe
                    val dir = if (direction == ItemTouchHelper.LEFT) "left" else "right"
                    Toast.makeText(requireContext(), "Swiped ${pet.name} to $dir", Toast.LENGTH_SHORT).show()

                    // Optional: reset the swipe so the item doesn't disappear
//                    recyclerView.adapter?.notifyItemChanged(position)
                }
            }

            // Attach the swipe handler to RecyclerView
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(recyclerView)

        }
    }

}