package com.example.rickandmorty.presentation.fragments.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.data.model.Character

class CharacterAdapter(private val listener: Listener) :
	PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator) {


	class CharacterViewHolder(val binding: ItemCharacterBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
		return CharacterViewHolder(
			ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}


	override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
		val characterPosition = getItem(position)
		if (characterPosition != null) {
			holder.binding.name.text = characterPosition.name
			holder.binding.gender.text = characterPosition.gender
			holder.binding.status.text = characterPosition.status
			holder.binding.species.text = characterPosition.species
			Glide.with(holder.binding.imageCharacter)
				.load(characterPosition.image)
				.centerCrop()
				.into(holder.binding.imageCharacter)
			holder.itemView.rootView.setOnClickListener {
				listener.onClick(characterPosition)
			}

		}
	}

	object CharacterComparator : DiffUtil.ItemCallback<Character>() {

		override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
			return oldItem == newItem
		}
	}

	interface Listener {

		fun onClick(character: Character)
	}

}