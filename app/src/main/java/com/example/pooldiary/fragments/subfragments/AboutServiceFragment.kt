package com.example.pooldiary.fragments.subfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pooldiary.database.view.ServiceViewModel
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentAboutServiceBinding
import com.example.pooldiary.models.Service
import com.google.gson.Gson

class AboutServiceFragment : Fragment() {

    private lateinit var serviceViewModel: ServiceViewModel
    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentAboutServiceBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutServiceBinding.inflate(inflater, container, false)


//        val user = userViewModel.getUserById(service.user_id)



        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv_client_name = binding.clientName
        val tv_street_name = binding.clientStreet
        val tv_date = binding.serviceDate
        val tv_time = binding.serviceTime

        val service = Gson().fromJson(arguments?.getString("service"), Service::class.java)



        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        serviceViewModel = ViewModelProvider(this)[ServiceViewModel::class.java]

        userViewModel.getUserById(service.user_id).observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                tv_client_name.text = user.name
                tv_street_name.text = user.address
            } else {
                Toast.makeText(context, "User not found!", Toast.LENGTH_LONG).show()
            }
        })

        binding.btnDeleteService.setOnClickListener {
            serviceViewModel.deleteService(service)
            findNavController().popBackStack()
        }

        binding.phSeekBar.apply {
            setLabelFormatter { value ->
                val intValue = value.toFloat()  // Convert the floating point value to an integer
                (intValue / 100).toString()  // Divide the value by 10 and convert it to String
            }
        }

        tv_date.text = service.datetime
        tv_time.text = service.datetime
    }
}