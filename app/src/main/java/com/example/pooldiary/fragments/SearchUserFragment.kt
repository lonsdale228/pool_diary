package com.example.pooldiary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pooldiary.R
import com.example.pooldiary.adapters.ClientsAdapter
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentSearchUserBinding
import com.example.pooldiary.models.User
import com.google.gson.Gson
import java.util.Locale

class SearchUserFragment : Fragment() {
    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var clientsRvAdapter: ClientsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        val view = binding.root



        clientsRvAdapter = ClientsAdapter(emptyList()) {
            val bundle = Bundle()
            bundle.putString("user", Gson().toJson(it))

            findNavController().navigate(R.id.action_searchUserFragment_to_addServiceFragment, bundle)
        }

        binding.rvSearchUser.layoutManager = LinearLayoutManager(view.context)
        binding.rvSearchUser.adapter = clientsRvAdapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Observing changes in the user list from the ViewModel
        userViewModel.getAllUsers.observe(viewLifecycleOwner, Observer { users ->
            users?.let {
                updateSearchResults("", it, clientsRvAdapter)  // Initial list setup
            }
        })

        setupSearchView()

        return view
    }
    private fun setupSearchView() {
        val searchView = binding.searchUser
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { updateSearchResults(it, userViewModel.getAllUsers.value ?: emptyList(), clientsRvAdapter) }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { updateSearchResults(it, userViewModel.getAllUsers.value ?: emptyList(), clientsRvAdapter) }
                return true
            }
        })
    }

    private fun updateSearchResults(query: String, userList: List<User>, adapter: ClientsAdapter) {
        val filteredList = if (query.isEmpty()) {
            userList
        } else {
            val lowerCaseQuery = query.lowercase(Locale.getDefault())
            userList.filter { it.name.lowercase(Locale.getDefault()).contains(lowerCaseQuery) }
        }
        adapter.setData(filteredList)
    }
}