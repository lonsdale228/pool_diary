package com.example.pooldiary.fragments.subfragments;

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
import com.example.pooldiary.adapters.ServicesAdapter
import com.example.pooldiary.database.view.ServiceViewModel
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentAboutUserBinding
import com.example.pooldiary.models.Service
import com.example.pooldiary.models.User
import com.google.gson.Gson

class AboutUserFragment : Fragment() {

    private var _binding: FragmentAboutUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var serviceViewModel: ServiceViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUserBinding.inflate(inflater, container, false)

        val view = binding.root
        val btnDeleteUser = binding.btnDeleteUser
        val user = Gson().fromJson(arguments?.getString("user"), User::class.java)

        val tv = binding.aboba
        tv.text = user.name

        binding.userServicesRecyclerView.layoutManager = LinearLayoutManager(view.context)

        val userServicesRv = binding.userServicesRecyclerView

        val servicesRvAdapter =  ServicesAdapter(emptyList()){
            Toast.makeText(view.context, it.id.toString(), Toast.LENGTH_SHORT).show()
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        serviceViewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)


//        serviceViewModel.addService(Service(0,user.id, "Aboba", "Norm","Yes", "No", "today"))

        serviceViewModel.getUserServices(user.id).observe(viewLifecycleOwner, Observer { service ->
            servicesRvAdapter.setData(service)
        })

        userServicesRv.adapter = servicesRvAdapter


        btnDeleteUser.setOnClickListener{
            userViewModel.deleteUser(user)
            findNavController().popBackStack()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}