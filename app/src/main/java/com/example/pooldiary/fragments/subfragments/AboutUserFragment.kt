package com.example.pooldiary.fragments.subfragments;

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pooldiary.R
import com.example.pooldiary.databinding.FragmentAboutUserBinding
import com.example.pooldiary.models.User
import com.google.gson.Gson

class AboutUserFragment : Fragment() {

    private var _binding: FragmentAboutUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUserBinding.inflate(inflater, container, false)
        val view = binding.root


        val user = Gson().fromJson(arguments?.getString("user"), User::class.java)

        val tv = binding.aboba
        tv.text = user.name


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}