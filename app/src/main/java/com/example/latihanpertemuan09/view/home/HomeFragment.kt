package com.example.latihanpertemuan09.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanpertemuan09.R
import com.example.latihanpertemuan09.adapter.HomeAdapter
import com.example.latihanpertemuan09.databinding.FragmentHomeBinding
import com.example.latihanpertemuan09.viewmodel.ViewModelMahasiswa

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(ViewModelMahasiswa::class.java)
        viewModel.getDataMahasiswa().observe(viewLifecycleOwner){
            if(it!=null){
                binding.rvUser.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                val adapter = HomeAdapter(it)
                binding.rvUser.adapter = adapter
            }
            else{
                binding.rvUser.visibility = View.GONE
            }
        }
        viewModel.showDataMahasiswa()

        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

}