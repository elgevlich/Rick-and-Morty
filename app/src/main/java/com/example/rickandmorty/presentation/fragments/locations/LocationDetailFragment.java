package com.example.rickandmorty.presentation.fragments.locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rickandmorty.R;

public class LocationDetailFragment extends Fragment {

	private static final String NAME_KEY = "nameKey";
	private static final String TYPE_KEY = "typeKey";
	private static final String DIMENSION_KEY = "dimensionKey";

	private String name;
	private String type;
	private String dimension;


	private TextView locationName;
	private TextView locationType;
	private TextView locationDimension;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			name = getArguments().getString(NAME_KEY);
			type = getArguments().getString(TYPE_KEY);
			dimension = getArguments().getString(DIMENSION_KEY);
		}
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
		initViews(view);
	}


	public void initViews(View view) {

		locationName = view.findViewById(R.id.location_name);
		locationType = view.findViewById(R.id.location_type);
		locationDimension = view.findViewById(R.id.location_dimension);

		locationName.setText(name);
		locationType.setText(type);
		locationDimension.setText(dimension);


	}

	public static LocationDetailFragment newInstance(String name, String type, String dimension) {
		LocationDetailFragment detailsFragment = new LocationDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putString(NAME_KEY, name);
		bundle.putString(TYPE_KEY, type);
		bundle.putString(DIMENSION_KEY, dimension);

		detailsFragment.setArguments(bundle);
		return detailsFragment;
	}

}