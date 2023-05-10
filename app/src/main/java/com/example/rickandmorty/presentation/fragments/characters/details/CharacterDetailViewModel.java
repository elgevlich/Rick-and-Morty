package com.example.rickandmorty.presentation.fragments.characters.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.api.EpisodeApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.character.Character;
import com.example.rickandmorty.domain.model.episode.Episode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailViewModel extends ViewModel {

	public MutableLiveData<Character> selectedItemCharacter = new MutableLiveData<>();
	public MutableLiveData<List<Episode>> responseEpisodes = new MutableLiveData<>();
	public List<String> episodesList = new ArrayList<>();
	public String episodesIds;
	CompositeDisposable compositeDisposable = new CompositeDisposable();
	private final EpisodeApi episodeApi = RetrofitInstance.INSTANCE.getEpisodeApi();

	public void clearListOfEpisodes() {
		episodesList.clear();
	}

	public void onClickItemCharacter(Character character) {
		selectedItemCharacter.setValue(character);
		episodesList
			.addAll(character
				.getEpisode());
	}

	void fetchData() {
		compositeDisposable.add(episodeApi.getListOfEpisodesForDetails(episodesIds)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(this::setListOfEpisodes, throwable -> {}));
	}

	public void setListOfEpisodes(List<Episode> episode) {
		responseEpisodes.setValue(episode);
	}

	public MutableLiveData<Character> getSelectedItemCharacter() {
		return selectedItemCharacter;
	}

	public void getEpisodes() {
		String str1;
		String result = "";
		if (!episodesList.isEmpty()) {
			for (String episode : episodesList) {
				str1 = episode.substring(40);
				result = result + str1 + ",";
			}
		}
		episodesIds = result;
	}

}
