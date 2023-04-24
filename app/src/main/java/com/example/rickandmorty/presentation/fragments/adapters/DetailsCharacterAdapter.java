package com.example.rickandmorty.presentation.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.episode.Episode;

import java.util.List;

public class DetailsCharacterAdapter extends RecyclerView.Adapter<EpisodesViewHolder> {

	Context context;
	List<Episode> postsList;

	public DetailsCharacterAdapter(Context context, List<Episode> postsList) {
		this.context = context;
		this.postsList = postsList;
	}

	@NonNull
	@Override
	public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_episode_horizontal, parent, false);
		return new EpisodesViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
		holder.tvNameEpisode.setText((postsList.get(position).getName()));
		holder.tvNumberEpisode.setText((postsList.get(position).getEpisode()));
	}

	@Override
	public int getItemCount() {
		return postsList.size();
	}

}