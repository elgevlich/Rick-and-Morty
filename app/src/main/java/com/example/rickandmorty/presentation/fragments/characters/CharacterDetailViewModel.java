package com.example.rickandmorty.presentation.fragments.characters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.rickandmorty.domain.model.character.Character;
import java.util.ArrayList;
import java.util.List;

public class CharacterDetailViewModel extends ViewModel {

	public MutableLiveData<Character> selectedItemCharacter = new MutableLiveData<>();
	public List<String> episodesList = new ArrayList<>();
	public String episodesIds;

	public void clearListOfEpisodes(){
		episodesList.clear();
	}

	public void onClickItemCharacter(Character character) {
		selectedItemCharacter.setValue(character);
		episodesList
			.addAll(character
				.getEpisode());

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
