package com.example.rickandmorty.presentation.fragments.characters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.data.api.CharacterApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.episode.Episode;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.presentation.fragments.adapters.DetailsCharacterAdapter;


import java.util.List;

import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailFragment extends Fragment {

	private ImageView characterImage;
	private TextView characterName;
	private TextView characterStatus;
	private TextView characterSpecies;
	private TextView characterGender;
	private TextView characterOrigin;

	private final CharacterDetailViewModel detailCharacterViewModel;

	CompositeDisposable compositeDisposable = new CompositeDisposable();
	RecyclerView rvListOfEpisodes;
	CharacterApi api;

	public CharacterDetailFragment(@NotNull CharacterDetailViewModel viewModelDetail) {
		this.detailCharacterViewModel = viewModelDetail;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(
		LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState
	) {
		return inflater.inflate(R.layout.fragment_character_details, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvListOfEpisodes = view.findViewById(R.id.episodes_list);
		api = RetrofitInstance.INSTANCE.getCharacterApi();
		rvListOfEpisodes.setHasFixedSize(true);

		characterImage = view.findViewById(R.id.image_character);
		characterName = view.findViewById(R.id.name);
		characterStatus = view.findViewById(R.id.status);
		characterSpecies = view.findViewById(R.id.species);
		characterGender = view.findViewById(R.id.gender);
		characterOrigin = view.findViewById(R.id.origin);

		final Observer<Character> observer = character1 -> {
			assert character1 != null;
			Glide.with(requireContext())
				.load(character1.getImage())
				.into(characterImage);
			characterName.setText(character1.getName());
			characterSpecies.setText(character1.getSpecies());
			characterGender.setText(character1.getGender());
			characterStatus.setText(character1.getStatus());
			characterOrigin.setText(character1.getOrigin().getName());

		};
		detailCharacterViewModel.getSelectedItemCharacter().observe(getViewLifecycleOwner(), observer);

		detailCharacterViewModel.getEpisodes();
		fetchData();
		detailCharacterViewModel.clearListOfEpisodes();
	}

	private void fetchData() {
		compositeDisposable.add(api.getDetailEpisode(detailCharacterViewModel.episodesIds)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::displayData, throwable -> Log.d("tag", throwable.toString())));
	}

	private void displayData(List<Episode> posts) {
		DetailsCharacterAdapter adapter = new DetailsCharacterAdapter(requireContext(), posts);
		rvListOfEpisodes.setAdapter(adapter);
	}

	@Override
	public void onStop() {
		compositeDisposable.clear();
		super.onStop();
	}

}
