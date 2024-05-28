package com.example.pooldiary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pooldiary.adapters.ChemistryAdapter
import com.example.pooldiary.database.view.ChemistryViewModel
import com.example.pooldiary.databinding.FragmentChemistryBinding
import com.example.pooldiary.models.Chemistry
import com.example.pooldiary.models.User
import com.example.pooldiary.models.submodels.ChemistryPriceList
import com.google.gson.Gson
import java.time.LocalDateTime

class ChemistryFragment : Fragment() {
    private var _binding:FragmentChemistryBinding? = null
    private val binding get() = _binding!!
    private lateinit var chemistryViewModel: ChemistryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChemistryBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.rvChemistry.layoutManager = GridLayoutManager(view.context,2)


        val chemistryRv = binding.rvChemistry
        val chemistryRvAdapter =  ChemistryAdapter(emptyList()){
//            val bundle = Bundle()
//            bundle.putString("user", Gson().toJson(it))
//
//            findNavController().navigate(R.id.action_clientsFragment_to_aboutUserFragment, bundle)
        }

        chemistryViewModel = ViewModelProvider(this).get(ChemistryViewModel::class.java)
        chemistryViewModel.getAllChemistry.observe(viewLifecycleOwner, Observer { chem ->
            chemistryRvAdapter.setData(chem)
        })
        chemistryRv.adapter = chemistryRvAdapter

        val aboba = ChemistryPriceList(listOf(1,5,25), listOf(157.0,600.0,1570.0),"kg" )

        val gson = Gson()
        val jsonString = gson.toJson(aboba)


        binding.btnAddChemistry.setOnClickListener {
            chemistryViewModel.addChemistry(Chemistry(0,"Chlore Aquadoctor","aboba", "","",jsonString,"",
                LocalDateTime.now(),""))
        }


        binding.btnGetRequest.setOnClickListener {

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}