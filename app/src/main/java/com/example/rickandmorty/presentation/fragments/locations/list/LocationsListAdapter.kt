package com.example.rickandmorty.presentation.fragments.locations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.domain.model.location.LocationResult
import com.example.rickandmorty.databinding.ItemLocationBinding

class LocationsListAdapter(private val listener: Listener) :
	PagingDataAdapter<LocationResult, LocationsListAdapter.LocationViewHolder>(LocationComparator) {

	class LocationViewHolder(val binding: ItemLocationBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
		return LocationViewHolder(
			ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
		val locationPosition = getItem(position)
		if (locationPosition != null) {
			holder.binding.locationName.text = locationPosition.name
			holder.binding.locationType.text = locationPosition.type
			holder.binding.locationDimension.text = locationPosition.dimension
			holder.itemView.rootView.setOnClickListener {
				listener.onClick(locationPosition)
			}
		}
	}

	object LocationComparator : DiffUtil.ItemCallback<LocationResult>() {

		override fun areItemsTheSame(oldItem: LocationResult, newItem: LocationResult): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: LocationResult, newItem: LocationResult): Boolean {
			return oldItem == newItem
		}
	}

	interface Listener {

		fun onClick(location: LocationResult)
	}

}