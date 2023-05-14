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
import com.example.rickandmorty.databinding.FragmentLocationDetailsBinding;
import com.example.rickandmorty.domain.model.character.CharacterResult;
import com.example.rickandmorty.domain.model.location.LocationResult;
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

	private FragmentLocationDetailsBinding binding;
	private final LocationDetailViewModel detailLocationViewModel;
	private CharacterDetailViewModel characterDetailViewModel;
	CompositeDisposable compositeDisposable = new CompositeDisposable();
	Navigator navigator;

	public LocationDetailFragment(@NotNull LocationDetailViewModel viewModelDetail) {
		this.detailLocationViewModel = viewModelDetail;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = FragmentLocationDetailsBinding.inflate(inflater);
		characterDetailViewModel = new ViewModelProvider(this).get(CharacterDetailViewModel.class);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
		navigator = (Navigator)requireActivity();
		navigator.hideBottomNav();

		final Observer<LocationResult> observer = location -> {
			assert location != null;
			locationName.setText(location.getName());
			locationType.setText(location.getType());
			locationDimension.setText(location.getDimension());
		};

		detailLocationViewModel.getSelectedItemCharacter().observe(getViewLifecycleOwner(), observer);
		displayData();

		backButton.setOnClickListener(v -> {
				navigator.popUpToBackStack();
				detailLocationViewModel.clearListOfCharacters();
			}
		);
	}

	@Override
	public void onStop() {
		compositeDisposable.clear();
		super.onStop();
	}

	@Override public void onClick(CharacterResult character) {
		characterDetailViewModel.onClickItemCharacter(character);
		navigator = (Navigator)requireActivity();
		navigator.replaceFragment(new CharacterDetailFragment(characterDetailViewModel));
	}

	private void displayData() {
		@SuppressLint("NotifyDataSetChanged") final Observer<List<CharacterResult>> observer = listOfCharacter -> {
			LocationDetailsAdapter adapter = new LocationDetailsAdapter(requireContext(), listOfCharacter, this);
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

}