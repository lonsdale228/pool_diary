package com.example.pooldiary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pooldiary.R
import com.example.pooldiary.adapters.ServicesAdapter
import com.example.pooldiary.database.view.ServiceViewModel
import com.example.pooldiary.databinding.FragmentServicesBinding
import com.example.pooldiary.models.Service
import com.google.gson.Gson

class ServicesFragment : Fragment() {
    private lateinit var serviceViewModel: ServiceViewModel
    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.servicesRecycleView.layoutManager = LinearLayoutManager(view.context)

        val servicesRv = binding.servicesRecycleView
        val servicesRvAdapter =  ServicesAdapter(emptyList()){
            val bundle = Bundle()
            bundle.putString("service", Gson().toJson(it))

            findNavController().navigate(R.id.aboutServiceFragment, bundle)
        }

        serviceViewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)
        serviceViewModel.getAllServices.observe(viewLifecycleOwner, Observer { service ->
            servicesRvAdapter.setData(service)
        })
        servicesRv.adapter = servicesRvAdapter


        val btnAddService = binding.btnAddService

        btnAddService.setOnClickListener {
            serviceViewModel.addService(Service(0,5, "Aboba", "Norm","Yes", "No", "today"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}