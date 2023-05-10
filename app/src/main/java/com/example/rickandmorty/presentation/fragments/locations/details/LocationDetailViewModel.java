package com.example.rickandmorty.presentation.fragments.locations.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.api.CharacterApi;
import com.example.rickandmorty.data.api.LocationApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.location.LocationResult;
import com.example.rickandmorty.domain.model.location.LocationResponse;
import com.example.rickandmorty.domain.model.character.Character;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class LocationDetailViewModel extends ViewModel {

	public MutableLiveData<String> locationName = new MutableLiveData<>();
	public MutableLiveData<LocationResult> selectedItemLocation = new MutableLiveData<>();
	public MutableLiveData<List<Character>> responseCharacters = new MutableLiveData<>();

	public List<String> listsOfCharacters = new ArrayList<>();

	public String characterId;
	CompositeDisposable compositeDisposable = new CompositeDisposable();
	public CharacterApi apiService = RetrofitInstance.INSTANCE.getCharacterApi();
	public LocationApi locationApiService = RetrofitInstance.INSTANCE.getLocationApi();

	public void onClickItemLocation(LocationResult location) {
		selectedItemLocation.setValue(location);
		listsOfCharacters.addAll(location.getResidents());
		getCharacters();
		fetchData();
	}

	public void setLocationName(String name) {
		locationName.setValue(name);
		fetchDataLocation();
	}

	public void setResponse(List<Character> character) {
		responseCharacters.setValue(character);
	}

	public MutableLiveData<LocationResult> getSelectedItemCharacter() {
		return selectedItemLocation;
	}

	public void setResponseLocation(LocationResponse location) {
		selectedItemLocation.setValue(location.getResults().get(0));
		onClickItemLocation(location.getResults().get(0));
	}

	public void clearListOfCharacters() {
		listsOfCharacters.clear();
	}

	public void fetchData() {
		compositeDisposable.add(apiService.getListOfCharactersForDetails(characterId)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::setResponse, throwable -> {}
			));
	}

	void fetchDataLocation() {
		compositeDisposable.add(locationApiService.getLocation(locationName.getValue())
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::setResponseLocation, throwable -> {
			}));
	}


	public void getCharacters() {
		String str1;
		String result = "";
		if (!listsOfCharacters.isEmpty()) {
			for (String character : listsOfCharacters) {
				str1 = character.substring(42);
				result = result + str1 + ",";
			}
		}
		characterId = result;
	}

}