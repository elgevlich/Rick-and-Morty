package com.example.rickandmorty.presentation.fragments.characters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;

public class CharacterDetailFragment extends Fragment {

    private static final String NAME_KEY = "nameKey";
    private static final String IMAGE_KEY = "imageKey";
    private static final String STATUS_KEY = "statusKey";
    private static final String SPECIES_KEY = "speciesKey";
    private static final String GENDER_KEY = "genderKey";
    private static final String ORIGIN_KEY = "originKey";

    private String name;
    private String image;
    private String status;
    private String species;
    private String gender;
    private String origin;


    private ImageView characterImage;
    private TextView characterName;
    private TextView characterStatus;
    private TextView characterSpecies;
    private TextView characterGender;
    private TextView characterOrigin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            status = getArguments().getString(STATUS_KEY);
            species = getArguments().getString(SPECIES_KEY);
            gender = getArguments().getString(GENDER_KEY);
            origin = getArguments().getString(ORIGIN_KEY);
            image = getArguments().getString(IMAGE_KEY);
        }
    }

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
    }


    public void initViews(View view) {

        characterImage = view.findViewById(R.id.image_character);
        characterName = view.findViewById(R.id.name);
        characterStatus = view.findViewById(R.id.status);
        characterSpecies = view.findViewById(R.id.species);
        characterGender = view.findViewById(R.id.gender);
        characterOrigin = view.findViewById(R.id.origin);

        characterName.setText(name);
        characterStatus.setText(status);
        characterSpecies.setText(species);
        characterGender.setText(gender);
        characterOrigin.setText(origin);
        Glide.with(requireContext()).load(image).centerCrop().into(characterImage);


    }

    public static CharacterDetailFragment newInstance(String name, String status, String species, String gender, String origin, String image) {
        CharacterDetailFragment detailsFragment = new CharacterDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, name);
        bundle.putString(STATUS_KEY, status);
        bundle.putString(SPECIES_KEY, species);
        bundle.putString(GENDER_KEY, gender);
        bundle.putString(ORIGIN_KEY, origin);
        bundle.putString(IMAGE_KEY, image);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

}
