package com.example.pooldiary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pooldiary.databinding.FragmentChemistryBinding

class ChemistryFragment : Fragment() {
    private var _binding:FragmentChemistryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChemistryBinding.inflate(inflater,container,false)
        val view = binding.root

        val lbl = binding.boba

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}