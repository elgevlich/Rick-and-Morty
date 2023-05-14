package com.example.rickandmorty.presentation


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.rickandmorty.databinding.SplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

	private lateinit var binding: SplashScreenBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = SplashScreenBinding.inflate(layoutInflater)
		setContentView(binding.root)

		CoroutineScope(Dispatchers.Main).launch {
			delay(3000L)
			startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
			finish()
		}
	}
}