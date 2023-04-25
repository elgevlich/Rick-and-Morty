package com.example.rickandmorty.presentation.fragments.locations.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.rickandmorty.domain.model.location.Location;
import java.util.ArrayList;
import java.util.List;


public class LocationDetailViewModel extends ViewModel {

	public MutableLiveData<Location> selectedItemLocation = new MutableLiveData<>();
	public List<String> charactersList = new ArrayList<>();
	public String charactersIds;

	public void clearListOfCharacters() {
		charactersList.clear();
	}

	public void onClickItemLocation(Location location) {
		selectedItemLocation.setValue(location);
		charactersList
			.addAll(location
				.getResidents());
	}

	public MutableLiveData<Location> getSelectedItemLocation() {
		return selectedItemLocation;
	}

	public void getCharacters() {
		String str1;
		String result = "";
		if (!charactersList.isEmpty()) {
			for (String character : charactersList) {
				str1 = character.substring(42);
				result = result + str1 + ",";
			}
		}
		charactersIds = result;
	}

}