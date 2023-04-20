package com.example.rickandmorty.presentation.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.episode.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {

	private List<Episode> episodes = new ArrayList<>();

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
		notifyDataSetChanged();
	}

	@NonNull @Override public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode_horizontal, parent, false);
		return new EpisodesViewHolder(view);
	}

	@Override public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
		Episode episode = episodes.get(position);
		holder.ep_name.setText(episode.getName());
		holder.ep_number.setText(episode.getEpisode());
	}

	@Override public int getItemCount() {
		return episodes.size();
	}

	static class EpisodesViewHolder extends RecyclerView.ViewHolder {

		private final TextView ep_name;
		private final TextView ep_number;

		public EpisodesViewHolder(@NonNull View itemView) {
			super(itemView);
			ep_name = itemView.findViewById(R.id.ep_name);
			ep_number = itemView.findViewById(R.id.ep_number);
		}

	}

}
