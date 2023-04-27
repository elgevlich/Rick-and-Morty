package com.example.rickandmorty.presentation.fragments.characters.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.domain.model.episode.Episode;
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailsAdapter;

import java.util.List;

public class CharacterDetailsAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {

	Context context;
	List<Episode> postsList;
	private OnClickListener onClickListener;

	public CharacterDetailsAdapter(Context context, List<Episode> postsList, OnClickListener onClickListener) {
		this.context = context;
		this.postsList = postsList;
		this.onClickListener = onClickListener;
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

	public interface OnClickListener {
		void onClick(Episode episode, int position);
	}

}