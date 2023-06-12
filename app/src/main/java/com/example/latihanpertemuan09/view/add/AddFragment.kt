package com.example.latihanpertemuan09.view.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.latihanpertemuan09.databinding.FragmentAddBinding
import com.example.latihanpertemuan09.viewmodel.ViewModelMahasiswa

class AddFragment : Fragment() {
    lateinit var binding : FragmentAddBinding
    lateinit var viewModel : ViewModelMahasiswa
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ViewModelMahasiswa::class.java)

        binding.btnTambah.setOnClickListener{
            val nim = binding.inputNim.text.toString()
            val nama = binding.inputNama.text.toString()
            val telepon = binding.inputTelepon.text.toString()
            viewModel.insertMahasiswa().observe(viewLifecycleOwner){
                if (it != null){
                    findNavController().navigateUp()
                    Toast.makeText(context,"Data berhasil ditambahkan",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Data gagal ditambahkan",Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.insertDataMahasiswa(nim,nama,telepon)
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}