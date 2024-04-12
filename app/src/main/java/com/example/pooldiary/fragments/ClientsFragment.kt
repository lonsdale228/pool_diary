package com.example.pooldiary.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pooldiary.AboutUserActivity
import com.example.pooldiary.AddUserActivity
import com.example.pooldiary.adapters.ClientsAdapter
import com.example.pooldiary.databinding.FragmentClientsBinding
import com.example.pooldiary.models.User
import com.google.gson.Gson

class ClientsFragment : Fragment() {
    private var _binding: FragmentClientsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientsBinding.inflate(inflater,container,false)
        val view = binding.root

        val floatingBtn = binding.floatingActionButton

        floatingBtn.setOnClickListener {
            val intent = Intent(view.context, AddUserActivity::class.java)
//            intent.putExtra("user", it as Parcelable)
            startActivity(intent)
        }

        binding.clientsRecyclerView.layoutManager = LinearLayoutManager(view.context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clientsRv = binding.clientsRecyclerView

        val items = listOf<User>(
            User(0,"test_name0", "centralna", "+38099999999", "тест записка","tut i tam"),
            User(1,"test_name1", "centralna", "+38099999999", "тест записка","tut i tam"),
            User(2,"test_name2", "centralna", "+38099999999", "тест записка","tut i tam"),
            User(3,"test_name3", "centralna", "+38099999999", "тест записка","tut i tam"),
        )

        val clientsRvAdapter =  ClientsAdapter(items){
            Toast.makeText(view.context, it.id.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(view.context, AboutUserActivity::class.java)
            intent.putExtra("user", Gson().toJson(it))
            startActivity(intent)
        }

        clientsRv.adapter = clientsRvAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}