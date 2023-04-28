package com.example.rickandmorty.presentation.fragments.characters.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.data.api.EpisodeApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.episode.Episode;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.presentation.Navigator;
import com.example.rickandmorty.presentation.fragments.episodes.details.EpisodeDetailFragment;
import com.example.rickandmorty.presentation.fragments.episodes.details.EpisodeDetailViewModel;
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailFragment;
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailViewModel;

import java.util.List;

import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailFragment extends Fragment implements CharacterDetailsAdapter.OnClickListener {

	private ImageView characterImage;
	private TextView characterName;
	private TextView characterStatus;
	private TextView characterSpecies;
	private TextView characterGender;
	private TextView characterOrigin;
	private TextView characterLocation;
	private ImageButton backButton;
	private RecyclerView rvListOfEpisodes;

	private final CharacterDetailViewModel characterDetailViewModel;
	private EpisodeDetailViewModel episodeDetailViewModel;
	private LocationDetailViewModel locationDetailViewModel;
	EpisodeApi api;
	Navigator navigator;

	CompositeDisposable compositeDisposable = new CompositeDisposable();

	public CharacterDetailFragment(@NotNull CharacterDetailViewModel viewModelDetail) {
		this.characterDetailViewModel = viewModelDetail;
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
		locationDetailViewModel = new ViewModelProvider(this).get(LocationDetailViewModel.class);
		episodeDetailViewModel = new ViewModelProvider(this).get(EpisodeDetailViewModel.class);
		return inflater.inflate(R.layout.fragment_character_details, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
		navigator = (Navigator)requireActivity();
		api = RetrofitInstance.INSTANCE.getEpisodeApi();
		navigator.hideBottomNav();
		final Observer<Character> observer = character -> {
			assert character != null;
			Glide.with(requireContext())
				.load(character.getImage())
				.into(characterImage);
			characterName.setText(character.getName());
			characterSpecies.setText(character.getSpecies());
			characterGender.setText(character.getGender());
			characterStatus.setText(character.getStatus());
			characterOrigin.setText(character.getOrigin().getName());
			characterLocation.setText(character.getLocation().getName());

			characterOrigin.setOnClickListener(v -> {
				locationDetailViewModel.setLocationName(character.getOrigin().getName());
				navigator.replaceFragment(new LocationDetailFragment(locationDetailViewModel));
			});
			characterLocation.setOnClickListener(v -> {
				locationDetailViewModel.setLocationName(character.getOrigin().getName());
				navigator.replaceFragment(new LocationDetailFragment(locationDetailViewModel));
			});
		};

		characterDetailViewModel.getSelectedItemCharacter().observe(getViewLifecycleOwner(), observer);
		characterDetailViewModel.getEpisodes();
		fetchData();

		backButton.setOnClickListener(v -> {
			navigator.popUpToBackStack();
			characterDetailViewModel.clearListOfEpisodes();
		});
	}

	private void fetchData() {
		compositeDisposable.add(api.getListOfEpisodesForDetails(characterDetailViewModel.episodesIds)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::displayData, throwable -> Log.d("tag", throwable.toString())));
	}

	private void displayData(List<Episode> posts) {
		CharacterDetailsAdapter adapter = new CharacterDetailsAdapter(requireContext(), posts, this);
		rvListOfEpisodes.setAdapter(adapter);
	}

	private void initViews(View view) {
		rvListOfEpisodes = view.findViewById(R.id.episodes_list);
		characterImage = view.findViewById(R.id.image_character);
		characterName = view.findViewById(R.id.name);
		characterStatus = view.findViewById(R.id.status);
		characterSpecies = view.findViewById(R.id.species);
		characterGender = view.findViewById(R.id.gender);
		characterOrigin = view.findViewById(R.id.origin);
		characterLocation = view.findViewById(R.id.location);
				backButton = view.findViewById(R.id.back_button);
		rvListOfEpisodes.setHasFixedSize(true);
	}

	@Override
	public void onStop() {
		compositeDisposable.clear();
		super.onStop();
	}

	@Override public void onClick(Episode episode) {
		episodeDetailViewModel.onClickItemEpisode(episode);
		navigator = (Navigator)requireActivity();
		navigator.replaceFragment(new EpisodeDetailFragment(episodeDetailViewModel));
	}

}
