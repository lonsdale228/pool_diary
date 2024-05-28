package com.example.pooldiary.fragments.subfragments;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pooldiary.R
import com.example.pooldiary.database.view.UserViewModel
import com.example.pooldiary.databinding.FragmentAddUserBinding
import com.example.pooldiary.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
        val name = binding.textInputName
        val phoneNumber = binding.textInputPhone
        val streetName = binding.textInputStreet
        val note = binding.textInputNote



        val edits = listOf(name,phoneNumber,streetName)

        for (edit in edits){
            val textInputLayout = edit.parent.parent as TextInputLayout
            edit.addTextChangedListener {
                textInputLayout.helperText = ""
            }
        }

        btnAddUser.setOnClickListener {
            if (checkEdits(edits)) {
                userViewModel.addUser(User(0,name.text.toString(), streetName.text.toString(), "+380"+phoneNumber.text.toString(),note.text.toString(), "1234", 650))
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    private fun checkEdits(edits: List<TextInputEditText>): Boolean {
        val shake = AnimationUtils.loadAnimation(view?.context, R.anim.shake)
        var isOk = true
        for (edit in edits) {
            if (edit.text.toString().trim().isEmpty()) {
                val textInputLayout = edit.parent.parent as TextInputLayout
                textInputLayout.helperText = "* Required"
                textInputLayout.animation = shake
                isOk = false
            }
        }
        return isOk
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}