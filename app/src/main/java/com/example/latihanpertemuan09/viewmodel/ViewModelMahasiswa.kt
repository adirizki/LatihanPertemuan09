package com.example.latihanpertemuan09.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanpertemuan09.model.request.DataMahasiswa
import com.example.latihanpertemuan09.model.request.Mahasiswa
import com.example.latihanpertemuan09.model.response.ResponseAddMahasiswa
import com.example.latihanpertemuan09.model.response.ResponseDataMahasiswa
import com.example.latihanpertemuan09.model.response.ResponseDetailMahasiswa
import com.example.latihanpertemuan09.model.response.ResponseUpdateMahasiswa
import com.example.latihanpertemuan09.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMahasiswa : ViewModel(){
    private val getDataMahasiswa = MutableLiveData<List<DataMahasiswa>?>()
    private val detailMahasiswa = MutableLiveData<ResponseDetailMahasiswa?>()
    private val insertMahasiswa = MutableLiveData<ResponseAddMahasiswa?>()
    private val updateMahasiswa = MutableLiveData<ResponseUpdateMahasiswa?>()

    fun getDataMahasiswa(): MutableLiveData<List<DataMahasiswa>?>{
        return getDataMahasiswa
    }
    fun getDetailMahasiswa(): MutableLiveData<ResponseDetailMahasiswa?> {
        return detailMahasiswa
    }
    fun insertMahasiswa(): MutableLiveData<ResponseAddMahasiswa?> {
        return insertMahasiswa
    }
    fun updateMahasiswa(): MutableLiveData<ResponseUpdateMahasiswa?> {
        return updateMahasiswa
    }

    fun showDataMahasiswa(){
        ApiClient.instance.getDataMahasiswa().enqueue(object : Callback<ResponseDataMahasiswa> {
            override fun onResponse(
                call: Call<ResponseDataMahasiswa>,
                response: Response<ResponseDataMahasiswa>
            ) {
                if (response.isSuccessful){
                    getDataMahasiswa.postValue(response.body()?.data)
                }else{
                    getDataMahasiswa.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataMahasiswa>, t: Throwable) {
                getDataMahasiswa.postValue(null)
            }

        })
    }
    fun getDetailData(nim: String) {
        ApiClient.instance.getDetailMahasiswa(nim).enqueue(object
            : Callback<ResponseDetailMahasiswa> {
            override fun onResponse(
                call: Call<ResponseDetailMahasiswa>,
                response: Response<ResponseDetailMahasiswa>
            ) {
                if (response.isSuccessful) {
                    detailMahasiswa.postValue(response.body())
                } else {
                    detailMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call: Call<ResponseDetailMahasiswa>, t: Throwable) {
                detailMahasiswa.postValue(null)
            }
        })
    }

    fun insertDataMahasiswa(nim : String, nama :String,telepon : String){
        ApiClient.instance.addDataMahasiswa(Mahasiswa(nim,nama,telepon)).enqueue(object : Callback<ResponseAddMahasiswa>{
            override fun onResponse(
                call: Call<ResponseAddMahasiswa>,
                response: Response<ResponseAddMahasiswa>
            ) {
                if (response.isSuccessful){
                    insertMahasiswa.postValue(response.body())
                }else{
                    insertMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call: Call<ResponseAddMahasiswa>, t: Throwable) {
                insertMahasiswa.postValue(null)
            }
        })
    }
    fun updateDataMahasiswa(nim : String, nama :String,telepon : String){
        ApiClient.instance.updateDataMahasiswa(nim,Mahasiswa(nim,nama,telepon)).enqueue(object : Callback<ResponseUpdateMahasiswa>{
            override fun onResponse(
                call: Call<ResponseUpdateMahasiswa>,
                response: Response<ResponseUpdateMahasiswa>
            ) {
                if (response.isSuccessful){
                    updateMahasiswa.postValue(response.body())
                }else{
                    updateMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call: Call<ResponseUpdateMahasiswa>, t: Throwable) {
                updateMahasiswa.postValue(null)
            }
        })
    }


}