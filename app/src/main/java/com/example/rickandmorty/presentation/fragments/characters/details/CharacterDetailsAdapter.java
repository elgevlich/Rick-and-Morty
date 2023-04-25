package com.example.rickandmorty.presentation.fragments.characters.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.episode.Episode;

import java.util.List;

public class CharacterDetailsAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {

	Context context;
	List<Episode> postsList;

	public CharacterDetailsAdapter(Context context, List<Episode> postsList) {
		this.context = context;
		this.postsList = postsList;
	}

	@NonNull
	@Override
	public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_episode_horizontal, parent, false);
		return new EpisodeViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
		holder.tvNameEpisode.setText((postsList.get(position).getName()));
		holder.tvNumberEpisode.setText((postsList.get(position).getEpisode()));
	}

	@Override
	public int getItemCount() {
		return postsList.size();
	}

}