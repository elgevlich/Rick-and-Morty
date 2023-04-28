package com.example.rickandmorty.presentation.fragments.locations.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.api.CharacterApi;
import com.example.rickandmorty.data.api.LocationApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.location.Location;
import com.example.rickandmorty.domain.model.location.LocationResponse;
import com.example.rickandmorty.domain.model.character.Character;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class LocationDetailViewModel extends ViewModel {

	public MutableLiveData<Location> selectedItemLocation = new MutableLiveData<>();
	public MutableLiveData<List<Character>> responseCharacters = new MutableLiveData<>();
	public MutableLiveData<String> locationName = new MutableLiveData<>();

	public List<String> listOfCharacters = new ArrayList<>();
	public String charactersIds;

	private final CharacterApi characterApi = RetrofitInstance.INSTANCE.getCharacterApi();
	private final LocationApi locationApi = RetrofitInstance.INSTANCE.getLocationApi();


	CompositeDisposable compositeDisposable = new CompositeDisposable();

	public void onClickItemLocation(Location location) {
		selectedItemLocation.setValue(location);
		setListOfCharacters(location);
	}

	public void setListOfCharacters(Location location) {
		listOfCharacters
			.addAll(location
				.getResidents());
	}

	public void setLocationName(String name) {
		locationName.setValue(name);
		fetchLocationData();
	}

	public void setResponseCharacter(List<Character> post) {
		responseCharacters.setValue(post);
	}

	public void setResponseLocation(LocationResponse post) {
		selectedItemLocation.setValue(post.getResults().get(0));
		setListOfCharacters(post.getResults().get(0));
	}

	public MutableLiveData<Location> getSelectedItemLocation() {
		return selectedItemLocation;
	}

	public void clearListOfCharacters() {
		listOfCharacters.clear();
	}

	void fetchData() {
		compositeDisposable.add(characterApi.getListOfCharactersForDetails(charactersIds)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::setResponseCharacter, throwable -> {
			}));
	}

	void fetchLocationData() {
		compositeDisposable.add(locationApi.getLocation(locationName.getValue())
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::setResponseLocation, throwable -> {
			}));
	}


	public void getCharacters() {
		String str1;
		String result = "";
		if (!listOfCharacters.isEmpty()) {
			for (String character : listOfCharacters) {
				str1 = character.substring(42);
				result = result + str1 + ",";
			}
		}
		charactersIds = result;
	}

}