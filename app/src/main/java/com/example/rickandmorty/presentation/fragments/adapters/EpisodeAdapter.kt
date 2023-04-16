package com.example.rickandmorty.presentation.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.databinding.ItemEpisodeBinding

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {


	private var episodeList = emptyList<Episode>()

	class EpisodeViewHolder(val binding: ItemEpisodeBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
		return EpisodeViewHolder(
			ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun getItemCount(): Int = episodeList.size

	override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
		val item = episodeList[position]
		holder.binding.epName.text = item.name
		holder.binding.epNumber.text = item.episode
		holder.binding.epAirDate.text = item.air_date
	}

	fun setEpisodes(episodes: List<Episode>) {
		episodeList = episodes
		notifyDataSetChanged()
	}

}