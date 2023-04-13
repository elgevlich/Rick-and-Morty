package com.example.rickandmorty.presentation.fragments.characters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.data.model.Character;
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding;

import java.util.List;

public class CharacterDetailsFragment extends Fragment {


	private FragmentCharacterDetailsBinding binding;
//	private CharacterViewModel viewModel;
//
//
//	@Override
//	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//		binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);
//		View view = binding.getRoot();
//		return view;
//	}
//
//
//	@Override
//	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
//
////		viewModel.getDataCharacter().observe();
//
//		Character character = (Character) getIntent().getSerializableExtra(EXTRA_CHARACTER);
//
//
//		viewModel.getDataCharacter().observe(getViewLifecycleOwnerLiveData()   {
//			Glide.with(this)
//					.load(binding.imageCharacter)
//					.into(binding.imageCharacter);
//
//		});
//
//
//
//		Glide.with(this)
//				.load(binding.imageCharacter)
//				.into(binding.imageCharacter);
//		textViewTitle.setText(character.getName());
//		textViewYear.setText(String.valueOf(character.getYear()));
//		textViewDescription.setText(movie.getDescription());
//
//		}
//		TextView name = binding.name;
//		TextView species = binding.species;
//		TextView status = binding.status;
//		TextView gender = binding.gender;
//		ImageView image = binding.imageCharacter;
//
//


}
