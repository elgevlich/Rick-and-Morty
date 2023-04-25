package com.example.rickandmorty.presentation.fragments.episodes.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.character.Character

class EpisodeDetailsAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

	val context: Context? = null
	private val postsList: List<Character>? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
		val view: View = LayoutInflater.from(parent.context)
			.inflate(R.layout.item_character, parent, false)
		return CharacterViewHolder(view)
	}

	override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
		holder.tvNameCharacter.text = postsList!![position].name
		holder.tvSpeciesCharacter.text = postsList[position].species
		holder.tvGenderCharacter.text = postsList[position].gender
		holder.tvStatusCharacter.text = postsList[position].status
		Glide.with(holder.ivCharacter)
			.load(postsList[position].image)
			.centerCrop()
			.into(holder.ivCharacter)
	}

	override fun getItemCount(): Int {
		return postsList!!.size
	}

	companion object {

		@JvmStatic
		fun newInstance() = EpisodeDetailsAdapter()
	}
}

