package com.example.rickandmorty.presentation.fragments.episodes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.domain.model.episode.EpisodeResult

class EpisodesListAdapter(private val listener: Listener) :
	PagingDataAdapter<EpisodeResult, EpisodesListAdapter.EpisodeViewHolder>(EpisodeComparator) {

	class EpisodeViewHolder(val binding: ItemEpisodeBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
		return EpisodeViewHolder(
			ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
		val episodePosition = getItem(position)
		if (episodePosition != null) {
			holder.binding.epName.text = episodePosition.name
			holder.binding.epNumber.text = episodePosition.episode
			holder.binding.epAirDate.text = episodePosition.air_date
			holder.itemView.rootView.setOnClickListener {
				listener.onClick(episodePosition)
			}
		}
	}

	object EpisodeComparator : DiffUtil.ItemCallback<EpisodeResult>() {

		override fun areItemsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
			return oldItem == newItem
		}
	}

	interface Listener {

		fun onClick(episode: EpisodeResult)
	}
}