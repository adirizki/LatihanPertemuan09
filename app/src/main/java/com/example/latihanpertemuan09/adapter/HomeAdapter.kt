package com.example.latihanpertemuan09.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanpertemuan09.R
import com.example.latihanpertemuan09.databinding.UserListBinding
import com.example.latihanpertemuan09.model.request.DataMahasiswa
import com.example.latihanpertemuan09.model.response.ResponseDeleteMahasiswa
import com.example.latihanpertemuan09.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAdapter(private var dataMahasiswa : List<DataMahasiswa>)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val binding : UserListBinding) :
        RecyclerView.ViewHolder(binding.root){
            var api =  ApiClient.instance
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view =
            UserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return dataMahasiswa.size
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.binding.txtNama.text = dataMahasiswa[position].nama
        holder.binding.txtNim.text = dataMahasiswa[position].nIM
        holder.binding.txtTelepon.text = dataMahasiswa[position].telepon
        holder.binding.cardView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("nim",dataMahasiswa[position].nIM)
            bundle.putString("nama",dataMahasiswa[position].nama)
            bundle.putString("telepon",dataMahasiswa[position].telepon)
            Navigation.findNavController(it).navigate(com.example.latihanpertemuan09.
            R.id.action_homeFragment_to_detailFragment, bundle)
        }
        holder.binding.btnDellete.setOnClickListener{
            holder.api.deleteDataMahasiswa(dataMahasiswa[position].nIM).enqueue(object :
                Callback<ResponseDeleteMahasiswa> {
                override fun onResponse(
                    call: Call<ResponseDeleteMahasiswa>,
                    response: Response<ResponseDeleteMahasiswa>
                ) {
                    if (response.isSuccessful){
                        dataMahasiswa = dataMahasiswa.toMutableList().apply { removeAt(position) }
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context, "Data berhasil dihapus!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponseDeleteMahasiswa>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Data gagal dihapus!", Toast.LENGTH_SHORT).show()
                }

            })
        }
        holder.binding.btnEdit.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("nim",dataMahasiswa[position].nIM)
            bundle.putString("nama",dataMahasiswa[position].nama)
            bundle.putString("telepon",dataMahasiswa[position].telepon)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_updateFragment, bundle)
        }

    }




}