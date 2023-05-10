package com.example.rickandmorty.presentation.fragments.episodes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.api.RetrofitInstance.characterApi
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.episode.Episode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodeDetailViewModel : ViewModel() {


    private val listOfCharacters = mutableListOf<List<String>>()
    val responseCharacters = MutableLiveData<List<Character?>?>()
    private val compositeDisposable = CompositeDisposable()
    val selectedItemEpisode = MutableLiveData<Episode>()
    private var charactersIds: String? = null
    private lateinit var api: CharacterApi

    fun onClickItemEpisode(episode: Episode) {
        selectedItemEpisode.value = episode
        listOfCharacters.add(episode.characters)
    }

    fun fetchData() {
        api = characterApi
        compositeDisposable.add(api.getListOfCharactersForDetails(charactersIds!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { post: List<Character?>? ->
                    this.responseCharacters.value = post
                }
            ) { throwable: Throwable? -> })
    }

    fun getCharacters() {
        var str1: String
        var result = ""
        if (listOfCharacters.isNotEmpty()) {
            for (episode in listOfCharacters[0]) {
                str1 = episode.substring(42)
                result = "$result+$str1,"
            }
        }
        charactersIds = result
    }

    fun clearListOfCharacters() {
        listOfCharacters.clear()
    }
}