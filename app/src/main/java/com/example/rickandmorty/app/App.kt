package com.example.rickandmorty.app

import android.app.Application
import com.example.rickandmorty.di.DaggerAppComponent


class App : Application() {

	val component by lazy {
		DaggerAppComponent.factory().create(this)
	}

	override fun onCreate() {
		component.inject(this)
		super.onCreate()
	}
}