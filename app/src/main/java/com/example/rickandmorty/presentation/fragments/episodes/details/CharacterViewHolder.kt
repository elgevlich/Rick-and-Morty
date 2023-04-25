package com.example.rickandmorty.presentation.fragments.episodes.details

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	var tvNameCharacter: TextView
	var tvSpeciesCharacter: TextView
	var tvGenderCharacter: TextView
	var tvStatusCharacter: TextView
	var ivCharacter: ImageView

	init {
		ivCharacter = itemView.findViewById(R.id.image_character)
		tvNameCharacter = itemView.findViewById(R.id.name)
		tvSpeciesCharacter = itemView.findViewById(R.id.species)
		tvGenderCharacter = itemView.findViewById(R.id.gender)
		tvStatusCharacter = itemView.findViewById(R.id.status)
	}
}