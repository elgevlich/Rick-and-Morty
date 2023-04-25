package com.example.rickandmorty.presentation.fragments.locations.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.data.api.CharacterApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.domain.model.location.Location;
import com.example.rickandmorty.presentation.Navigator;
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailViewModel;
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LocationDetailFragment extends Fragment {

	private TextView locationName;
	private TextView locationType;
	private TextView locationDimension;
	private ImageButton backButton;

	private final LocationDetailViewModel detailLocationViewModel;
	LocationDetailsAdapter adapter;
	private CharacterDetailViewModel viewModelDetail;
	LocationDetailsAdapter.OnClickListener clickListener;
	CompositeDisposable compositeDisposable = new CompositeDisposable();
	RecyclerView rvListOfCharacters;
	CharacterApi api;
	Navigator navigator;

	public LocationDetailFragment(@NotNull LocationDetailViewModel viewModelDetail) {
		this.detailLocationViewModel = viewModelDetail;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(
		LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState
	) {
		return inflater.inflate(R.layout.fragment_location_detail, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvListOfCharacters = view.findViewById(R.id.characters_list);
		api = RetrofitInstance.INSTANCE.getCharacterApi();
		rvListOfCharacters.setHasFixedSize(true);

		clickListener = (character, position) -> {
			viewModelDetail = new CharacterDetailViewModel();
			viewModelDetail.onClickItemCharacter(character);
			navigator = (Navigator)requireActivity();
			navigator.replaceFragment(new CharacterDetailFragment(viewModelDetail), "Character");
		};

		locationName = view.findViewById(R.id.location_name);
		locationType = view.findViewById(R.id.location_type);
		locationDimension = view.findViewById(R.id.location_dimension);
		backButton = view.findViewById(R.id.back_button);

		final Observer<Location> observer = location1 -> {
			assert location1 != null;
			locationName.setText(location1.getName());
			locationType.setText(location1.getType());
			locationDimension.setText(location1.getDimension());
		};
		detailLocationViewModel.getSelectedItemLocation().observe(getViewLifecycleOwner(), observer);
		detailLocationViewModel.getCharacters();
		fetchData();
		detailLocationViewModel.clearListOfCharacters();

		Navigator navigator = (Navigator) requireActivity();
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				navigator.popUpToBackStack("Locations");
			}
		});
	}

	private void fetchData() {
		compositeDisposable.add(api.getListOfCharactersForDetails(detailLocationViewModel.charactersIds)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::displayData, throwable -> Log.d("tag", throwable.toString())));
	}

	private void displayData(List<Character> posts) {
		LocationDetailsAdapter adapter = new LocationDetailsAdapter(requireContext(), posts, clickListener);
		rvListOfCharacters.setAdapter(adapter);
	}

	@Override
	public void onStop() {
		compositeDisposable.clear();
		super.onStop();
	}

}