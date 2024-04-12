package com.example.pooldiary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pooldiary.databinding.ActivityMainBinding
import com.example.pooldiary.fragments.ChemistryFragment
import com.example.pooldiary.fragments.ClientsFragment
import com.example.pooldiary.fragments.ServicesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ServicesFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.chemistry_item -> replaceFragment(ChemistryFragment())
                R.id.services_item -> replaceFragment(ServicesFragment())
                R.id.clients_item -> replaceFragment(ClientsFragment())
                else ->{

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}