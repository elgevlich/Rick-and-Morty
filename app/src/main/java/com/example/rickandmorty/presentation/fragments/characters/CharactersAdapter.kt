package com.example.rickandmorty.presentation.fragments.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.data.model.Character

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

	private var charactersList = emptyList<Character>()

	class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
		return CharacterViewHolder(
			ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun getItemCount(): Int = charactersList.size

	override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
		val item = charactersList[position]
		holder.binding.name.text = item.name
		holder.binding.gender.text = item.gender
		holder.binding.status.text = item.status
		holder.binding.species.text = item.species
		Glide.with(holder.binding.imageCharacter)
			.load(item.image)
			.into(holder.binding.imageCharacter)
	}

	fun setCharacter(characters: List<Character>) {
		charactersList = characters
		notifyDataSetChanged()
	}

}
