package com.example.rickandmorty.presentation.fragments.characters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.rickandmorty.databinding.FragmentCharacterFilterBinding
import com.example.rickandmorty.presentation.Navigator


class CharacterFilterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterFilterBinding
    var name = ""
    var status = ""
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("name") ?: ""
        status = arguments?.getString("status") ?: ""
        gender = arguments?.getString("gender") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigator = requireActivity() as Navigator

        binding.backButton.setOnClickListener {
            navigator.popUpToBackStack("Characters")
        }

        if (name.isNotEmpty()) binding.search.setText(name)
        when (status) {
            "Alive" -> binding.chipAlive.isChecked = true
            "Dead" -> binding.chipDead.isChecked = true
            "unknown" -> binding.chipUnknown.isChecked = true
        }

        when (gender) {
            "Male" -> binding.chipMale.isChecked = true
            "Female" -> binding.chipFemale.isChecked = true
            "Genderless" -> binding.chipGenderless.isChecked = true
            "unknown" -> binding.chipUnknownGender.isChecked = true
        }

        binding.btnMakeFilter.setOnClickListener {
            name = ""
            status = ""
            gender = ""
            name = binding.search.text.toString()
            if (binding.chipAlive.isChecked) status = "Alive"
            if (binding.chipDead.isChecked) status = "Dead"
            if (binding.chipUnknown.isChecked) status = "unknown"
            if (binding.chipMale.isChecked) gender = "Male"
            if (binding.chipFemale.isChecked) gender = "Female"
            if (binding.chipUnknownGender.isChecked) gender = "unknown"
            if (binding.chipGenderless.isChecked) gender = "Genderless"

            setFragmentResult(
                "requestKey", Bundle().apply {
                    putString("name", name)
                    putString("gender", gender)
                    putString("status", status)
                }
            )
            navigator.popUpToBackStack("Characters")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, status: String, gender: String): CharacterFilterFragment {
            return CharacterFilterFragment().apply {
                arguments = Bundle().apply {
                    putString("name", name)
                    putString("status", status)
                    putString("gender", gender)
                }
            }
        }
    }
}
