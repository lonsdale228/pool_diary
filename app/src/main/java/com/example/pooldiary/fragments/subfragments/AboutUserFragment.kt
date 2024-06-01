package com.example.pooldiary.fragments.subfragments;

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentAboutUserBinding
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
        val user = Gson().fromJson(arguments?.getString("user"), User::class.java)

        binding.button.setOnClickListener{
            val viberPackageName = "com.viber.voip"
            val phone = user.phone_number
            try {
                activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("viber://add?number=$phone")))
            } catch (ex: ActivityNotFoundException) {
                try {
                    activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$viberPackageName")))
                } catch (ex: ActivityNotFoundException) {
                    activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$viberPackageName")))
                }
            }
        }

        binding.userName.text = user.name
        binding.userName.isSelected = true
        binding.userPhoneNumber.text = user.phone_number
        binding.streetName.text = user.address
        binding.userServicesRecyclerView.layoutManager = LinearLayoutManager(view.context)

        val userServicesRv = binding.userServicesRecyclerView

        val servicesRvAdapter =  ServicesAdapter(emptyList()){
//            Toast.makeText(view.context, it.id.toString(), Toast.LENGTH_SHORT).show()

            val bundle = Bundle()
            bundle.putString("service", Gson().toJson(it))
            bundle.putString("datetime", Gson().toJson(it.datetime.toString()))
            findNavController().navigate(R.id.action_aboutUserFragment_to_aboutServiceFragment, bundle)
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        serviceViewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)
        serviceViewModel.getUserServices(user.id).observe(viewLifecycleOwner, Observer { service ->
            servicesRvAdapter.setData(service)
        })

        userServicesRv.adapter = servicesRvAdapter


        binding.btnDeleteUser.setOnClickListener {
            userViewModel.deleteUser(user)
            findNavController().popBackStack()
        }

        binding.btnAddService.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("user", Gson().toJson(user))

            findNavController().navigate(R.id.action_aboutUserFragment_to_addServiceFragment, bundle)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}