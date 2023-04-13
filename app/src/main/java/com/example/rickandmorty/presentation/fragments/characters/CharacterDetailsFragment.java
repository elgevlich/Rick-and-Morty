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
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding;

public class CharacterDetailsFragment extends Fragment {


	private FragmentCharacterDetailsBinding binding;
	private CharacterViewModel viewModel;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);
		View view = binding.getRoot();
		return view;
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);

//		viewModel.getDataCharacter().observe();


		};
		TextView name = binding.name;
		TextView species = binding.species;
		TextView status = binding.status;
		TextView gender = binding.gender;
		ImageView image = binding.imageCharacter;






}
