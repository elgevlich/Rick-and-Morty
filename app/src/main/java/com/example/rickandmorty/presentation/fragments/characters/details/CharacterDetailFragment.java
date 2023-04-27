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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.data.api.EpisodeApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.episode.Episode;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.presentation.Navigator;
import com.example.rickandmorty.presentation.fragments.episodes.details.EpisodeDetailViewModel;

import java.util.List;
import java.util.Objects;

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
	private ImageButton backButton;
	private RecyclerView rvListOfEpisodes;

	private final CharacterDetailViewModel detailCharacterViewModel;
	private EpisodeDetailViewModel viewModelDetail;
	EpisodeApi api;
	CharacterDetailsAdapter.OnClickListener clickListener;
	Navigator navigator;

	CompositeDisposable compositeDisposable = new CompositeDisposable();

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
		initViews(view);
		navigator = (Navigator)requireActivity();
		api = RetrofitInstance.INSTANCE.getEpisodeApi();

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

		clickListener = (episode, position) -> {
			//TODO!!
		};

		int index = requireActivity().getFragmentManager().getBackStackEntryCount();
		assert getFragmentManager() != null;
		FragmentManager.BackStackEntry backEntry = getFragmentManager().getBackStackEntryAt(index);
		String tag = backEntry.getName();
		backButton.setOnClickListener(v -> {
			if (Objects.equals(tag, "Characters")) {
				navigator.popUpToBackStack("Characters");
			} else {
				navigator.popUpToBackStack("Location");
			}
		});
	}

	private void fetchData() {
		compositeDisposable.add(api.getListOfEpisodesForDetails(detailCharacterViewModel.episodesIds)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::displayData, throwable -> Log.d("tag", throwable.toString())));
	}

	private void displayData(List<Episode> posts) {
		CharacterDetailsAdapter adapter = new CharacterDetailsAdapter(requireContext(), posts, clickListener);
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
		backButton = view.findViewById(R.id.back_button);
		rvListOfEpisodes.setHasFixedSize(true);
	}

	@Override
	public void onStop() {
		compositeDisposable.clear();
		super.onStop();
	}

}
