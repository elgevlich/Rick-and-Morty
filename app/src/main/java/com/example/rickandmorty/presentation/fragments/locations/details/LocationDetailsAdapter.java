package com.example.rickandmorty.presentation.fragments.locations.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.character.CharacterResult;


import java.util.List;

public class LocationDetailsAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

	Context context;
	List<CharacterResult> postsList;
	private final OnClickListener onClickListener;

	public LocationDetailsAdapter(Context context, List<CharacterResult> postsList, OnClickListener onClickListener) {
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
		CharacterResult item = postsList.get(position);
		Glide.with(holder.ivCharacter)
			.load(postsList.get(position).getImage())
			.centerCrop()
			.into(holder.ivCharacter);
		holder.tvNameCharacter.setText(item.getName());
		holder.tvGenderCharacter.setText(item.getGender());
		holder.tvStatusCharacter.setText(item.getStatus());
		holder.tvSpeciesCharacter.setText(item.getSpecies());
		holder.itemView.getRootView().setOnClickListener(view -> onClickListener.onClick(item));
	}

	@Override
	public int getItemCount() {
		return postsList.size();
	}

	public interface OnClickListener {

		void onClick(CharacterResult character);

	}

}