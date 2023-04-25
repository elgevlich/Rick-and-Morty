package com.example.rickandmorty.presentation.fragments.locations.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.character.Character;


import java.util.List;

public class LocationDetailsAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

	Context context;
	List<Character> postsList;
	private OnClickListener onClickListener;

	public LocationDetailsAdapter(Context context, List<Character> postsList, OnClickListener onClickListener) {
		this.context = context;
		this.postsList = postsList;
		this.onClickListener = onClickListener;
	}

	@NonNull
	@Override
	public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_character, parent, false);
		return new CharacterViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
		holder.tvNameCharacter.setText((postsList.get(position).getName()));
		holder.tvSpeciesCharacter.setText((postsList.get(position).getSpecies()));
		holder.tvGenderCharacter.setText((postsList.get(position).getGender()));
		holder.tvStatusCharacter.setText((postsList.get(position).getStatus()));
		Glide.with(holder.ivCharacter)
			.load(postsList.get(position).getImage())
			.centerCrop()
			.into(holder.ivCharacter);
		holder.itemView.setOnClickListener(view -> {
			if (onClickListener != null) {
				onClickListener.onClick(postsList.get(position), position);
			}
		});
	}

	@Override
	public int getItemCount() {
		return postsList.size();
	}
		public interface OnClickListener {
		void onClick(Character character, int position);
	}

}