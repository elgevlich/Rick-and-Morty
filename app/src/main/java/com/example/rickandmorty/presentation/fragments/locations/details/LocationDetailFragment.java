package com.example.rickandmorty.presentation.fragments.locations.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.domain.model.location.Location;
import com.example.rickandmorty.presentation.Navigator;
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailViewModel;
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class LocationDetailFragment extends Fragment implements LocationDetailsAdapter.OnClickListener {

	private TextView locationName;
	private TextView locationType;
	private TextView locationDimension;
	private ImageButton backButton;
	private RecyclerView rvListOfCharacters;

	private final LocationDetailViewModel detailLocationViewModel;
	private CharacterDetailViewModel characterDetailViewModel;
	LocationDetailsAdapter.OnClickListener clickListener;
	CompositeDisposable compositeDisposable = new CompositeDisposable();
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
		characterDetailViewModel = new ViewModelProvider(this).get(CharacterDetailViewModel.class);
		return inflater.inflate(R.layout.fragment_location_details, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
		navigator = (Navigator)requireActivity();
		navigator.hideBottomNav();

		final Observer<Location> observer = location -> {
			assert location != null;
			locationName.setText(location.getName());
			locationType.setText(location.getType());
			locationDimension.setText(location.getDimension());
			detailLocationViewModel.setListOfCharacters(location);
		};

		detailLocationViewModel.getSelectedItemLocation().observe(getViewLifecycleOwner(), observer);
		detailLocationViewModel.getCharacters();
		detailLocationViewModel.fetchData();
		displayData();

		backButton.setOnClickListener(v -> {
				navigator.popUpToBackStack();
				detailLocationViewModel.clearListOfCharacters();
			}
		);
	}

	private void displayData() {
		@SuppressLint("NotifyDataSetChanged") final Observer<List<Character>> observer = listOfCharacters -> {
			assert listOfCharacters != null;
			LocationDetailsAdapter adapter = new LocationDetailsAdapter(requireContext(), listOfCharacters, clickListener);
			rvListOfCharacters.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		};
		detailLocationViewModel.responseCharacters.observe(getViewLifecycleOwner(), observer);
	}

	private void initViews(View view) {
		locationName = view.findViewById(R.id.location_name);
		locationType = view.findViewById(R.id.location_type);
		locationDimension = view.findViewById(R.id.location_dimension);
		backButton = view.findViewById(R.id.back_button);
		rvListOfCharacters = view.findViewById(R.id.characters_list);
		rvListOfCharacters.setHasFixedSize(true);
	}

	@Override
	public void onStop() {
		compositeDisposable.clear();
		super.onStop();
	}

	@Override public void onClick(Character character, int position) {
		characterDetailViewModel.onClickItemCharacter(character);
		navigator = (Navigator)requireActivity();
		navigator.replaceFragment(new CharacterDetailFragment(characterDetailViewModel));
	}

}