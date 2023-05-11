package com.example.rickandmorty.presentation.fragments.characters.details;

import android.view.View;
import android.widget.TextView;

import com.example.rickandmorty.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class EpisodeViewHolder extends RecyclerView.ViewHolder {

	TextView tvNameEpisode, tvNumberEpisode, tvAirDateEpisode;

	public EpisodeViewHolder(@NonNull View itemView) {
		super(itemView);
		tvNumberEpisode = itemView.findViewById(R.id.ep_number);
		tvNameEpisode = itemView.findViewById(R.id.ep_name);
		tvAirDateEpisode = itemView.findViewById(R.id.ep_air_date);
	}

}