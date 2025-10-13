package com.example.tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val button1 = view.findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        val button2 = view.findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        val button3 = view.findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        val button4 = view.findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        val button5 = view.findViewById<Button>(R.id.button5)
        button5.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, PetProfileFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}