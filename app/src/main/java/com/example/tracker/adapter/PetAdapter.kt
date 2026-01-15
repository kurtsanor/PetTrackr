package com.example.tracker.adapter

import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.model.Pet
import androidx.core.graphics.toColorInt

class PetAdapter(
    private val pets: List<Pet>,
    private val onClick: (Pet) -> Unit
): RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

     class PetViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.petName)
        val breed: TextView = view.findViewById(R.id.petBreed)
        val genderImage: ImageView = view.findViewById(R.id.genderImage)
        val gender: TextView = view.findViewById(R.id.gender)
        val type: TextView = view.findViewById(R.id.petType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets[position]
        holder.name.text = pet.name
        holder.breed.text = pet.breed
        holder.gender.text = pet.gender
        holder.genderImage.setImageResource(if (pet.gender == "Male") R.drawable.male else R.drawable.female)
        if (pet.gender == "Male") holder.genderImage.setColorFilter("#2196F3".toColorInt()) else holder.genderImage.setColorFilter(
            "#E91E63".toColorInt())
        holder.type.text = pet.type

        holder.itemView.setOnClickListener {
            onClick(pet)
        }
    }

    override fun getItemCount(): Int {
        return pets.size
    }

}