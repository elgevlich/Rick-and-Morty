package com.example.rickandmorty.presentation.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Location
import com.example.rickandmorty.databinding.ItemLocationBinding

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {


	private var locationsList = emptyList<Location>()

	class LocationViewHolder(val binding: ItemLocationBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
		return LocationViewHolder(
			ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun getItemCount(): Int = locationsList.size

	override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
		val item = locationsList[position]
		holder.binding.locationName.text = item.name
		holder.binding.locationType.text = item.type
		holder.binding.locationDimension.text = item.dimension
	}

	fun setLocations(locations: List<Location>) {
		locationsList = locations
		notifyDataSetChanged()
	}

}