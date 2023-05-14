package com.example.rickandmorty.presentation.fragments.characters.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.api.EpisodeApi;
import com.example.rickandmorty.data.api.RetrofitInstance;
import com.example.rickandmorty.domain.model.character.CharacterResult;
import com.example.rickandmorty.domain.model.episode.EpisodeResult;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailViewModel extends ViewModel {

	public MutableLiveData<CharacterResult> selectedItemCharacter = new MutableLiveData<>();
	public MutableLiveData<List<EpisodeResult>> responseEpisodes = new MutableLiveData<>();
	public List<String> episodesList = new ArrayList<>();
	CompositeDisposable compositeDisposable = new CompositeDisposable();
	private final EpisodeApi episodeApi = RetrofitInstance.INSTANCE.getEpisodeApi();
	public String episodesIds;

	public void clearListOfEpisodes() {
		episodesList.clear();
	}

	public void onClickItemCharacter(CharacterResult character) {
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

	public void setListOfEpisodes(List<EpisodeResult> episode) {
		responseEpisodes.setValue(episode);
	}

	public MutableLiveData<CharacterResult> getSelectedItemCharacter() {
		return selectedItemCharacter;
	}

	public void getEpisodes() {
		String str1;
		StringBuilder result = new StringBuilder();
		if (!episodesList.isEmpty()) {
			for (String episode : episodesList) {
				str1 = episode.substring(40);
				result.append(str1).append(",");
			}
		}
		episodesIds = result.toString();
	}

}
