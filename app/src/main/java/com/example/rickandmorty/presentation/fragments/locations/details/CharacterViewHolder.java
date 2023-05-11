package com.example.rickandmorty.presentation.fragments.locations.details;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

	TextView tvNameCharacter, tvSpeciesCharacter, tvGenderCharacter, tvStatusCharacter;
	ImageView ivCharacter;

	public CharacterViewHolder(@NonNull View itemView) {
		super(itemView);
		ivCharacter = itemView.findViewById(R.id.image_character);
		tvNameCharacter = itemView.findViewById(R.id.name);
		tvSpeciesCharacter = itemView.findViewById(R.id.species);
		tvGenderCharacter = itemView.findViewById(R.id.gender);
		tvStatusCharacter = itemView.findViewById(R.id.status);
	}

}