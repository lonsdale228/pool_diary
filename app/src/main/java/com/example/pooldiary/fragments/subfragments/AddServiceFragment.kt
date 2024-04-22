package com.example.pooldiary.fragments.subfragments

import  android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pooldiary.R
import com.example.pooldiary.database.view.ServiceViewModel
import com.example.pooldiary.databinding.FragmentAddServiceBinding
import com.example.pooldiary.models.Service
import com.example.pooldiary.models.User
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddServiceFragment : Fragment() {

    private var _binding: FragmentAddServiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceViewModel: ServiceViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddServiceBinding.inflate(inflater, container, false)

        val user = Gson().fromJson(arguments?.getString("user"), User::class.java)

        if (user!=null){
            binding.tvTest.text = user.name
        }

        binding.phSeekBarServices.apply {
            setLabelFormatter { value ->
                (value / 100).toString()
            }
        }

        binding.testBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addServiceFragment_to_searchUserFragment)
        }

        serviceViewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)
        binding.addServBtn.setOnClickListener {
            val formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm")
            val current = LocalDateTime.now().format(formatter)
            serviceViewModel.addService(Service(0,user.id,binding.textInputNote.text.toString(), binding.poolStatusSeekBarServices.value.toString(),"est", "non", current))

            findNavController().popBackStack()
        }


        return binding.root
    }
}