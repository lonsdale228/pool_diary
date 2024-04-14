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
import com.example.pooldiary.adapters.ClientsAdapter
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentClientsBinding
import com.google.gson.Gson

class ClientsFragment : Fragment() {
    private var _binding: FragmentClientsBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientsBinding.inflate(inflater,container,false)
        val view = binding.root

        val floatingBtn = binding.floatingActionButton

        floatingBtn.setOnClickListener {
            findNavController().navigate(R.id.addUserFragment)
        }

        binding.clientsRecyclerView.layoutManager = LinearLayoutManager(view.context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clientsRv = binding.clientsRecyclerView
        val clientsRvAdapter =  ClientsAdapter(emptyList()){
            val bundle = Bundle()
            bundle.putString("user", Gson().toJson(it))

            findNavController().navigate(R.id.aboutUserFragment, bundle)
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUsers.observe(viewLifecycleOwner, Observer { user ->
            clientsRvAdapter.setData(user)
        })
        clientsRv.adapter = clientsRvAdapter

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}