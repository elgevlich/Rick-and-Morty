package com.example.rickandmorty.presentation.fragments.characters.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.data.network.CharacterRepository
import com.example.rickandmorty.databinding.FragmentCharacterFilterBinding
import com.example.rickandmorty.extensions.*
import com.example.rickandmorty.presentation.fragments.characters.CharacterViewModelFactory
import com.example.rickandmorty.presentation.fragments.characters.CharacterViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CharacterFilterFragment : BottomSheetDialogFragment() {

//	private lateinit var binding: FragmentCharacterFilterBinding
//	private val viewModel: CharacterViewModel by activityViewModels {
//		CharacterViewModelFactory(
//			CharacterRepository()
//		)
//	}
//
//	override fun onCreateView(
//		inflater: LayoutInflater, container: ViewGroup?,
//		savedInstanceState: Bundle?
//	): View {
//		binding = FragmentCharacterFilterBinding.inflate(inflater)
//		return binding.root
//	}
//
//
//	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//		super.onViewCreated(view, savedInstanceState)
//		hideBottomNav()
//		binding.apply {
//			viewModel.filterValue.observe(viewLifecycleOwner) {
//				chipgroupStatus.setChipChecked(it[0])
//				radiogroupGender.setButtonChecked(it[1])
//			}
//		}
//
//
//		binding.btnMakeFilter.setOnClickListener {
//			if (binding.chipgroupStatus.getTextChipChecked().isNotEmpty() && binding.radiogroupGender.getTextButtonChecked()
//					.isNotEmpty()
//			) {
//				viewModel.getByStatusAndGender(
//					binding.chipgroupStatus.getTextChipChecked(),
//					binding.radiogroupGender.getTextButtonChecked(),
//					1
//				)
//			} else {
//				if (binding.chipgroupStatus.getTextChipChecked().isNotEmpty()) {
//					viewModel.getByStatus(binding.chipgroupStatus.getTextChipChecked(), 1)
//				} else {
//					viewModel.getByGender(binding.radiogroupGender.getTextButtonChecked(), 1)
//				}
//			}
//
//			viewModel.filterValue.value =
//				arrayOf(binding.chipgroupStatus.checkedChipId, binding.radiogroupGender.checkedRadioButtonId)
//			parentFragmentManager.popBackStack()
//		}
//	}

	private fun hideBottomNav() {
		val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomNavigationView.visibility = View.GONE
	}
}
