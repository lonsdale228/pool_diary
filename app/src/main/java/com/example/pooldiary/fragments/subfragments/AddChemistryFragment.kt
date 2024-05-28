package com.example.pooldiary.fragments.subfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pooldiary.databinding.FragmentAddChemistryBinding


class AddChemistryFragment : Fragment() {
    private var _binding: FragmentAddChemistryBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddChemistryBinding.inflate(inflater, container, false)


        return binding.root
    }
}