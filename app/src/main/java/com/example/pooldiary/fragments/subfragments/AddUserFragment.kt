package com.example.pooldiary.fragments.subfragments;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentAddUserBinding
import com.example.pooldiary.models.User

class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val btnAddUser = binding.btnAddUser
        val name = binding.edtName
        val phoneNumber = binding.edtPhone
        val streetName = binding.edtStreet
        val note = binding.edtNote

        val edits = listOf(name,phoneNumber,streetName,note)

        btnAddUser.setOnClickListener {
            if (checkEdits(edits)) {
                userViewModel.addUser(User(0,name.text.toString(), phoneNumber.text.toString(),streetName.text.toString(),note.text.toString(), "1234"))
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    private fun checkEdits(edits: List<EditText>): Boolean {
        for (edit in edits) {
            if (edit.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Fill all fields!",Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}