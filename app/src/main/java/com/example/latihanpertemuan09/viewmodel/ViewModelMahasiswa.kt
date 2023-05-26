package com.example.latihanpertemuan09.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanpertemuan09.model.request.DataMahasiswa
import com.example.latihanpertemuan09.model.response.ResponseDataMahasiswa
import com.example.latihanpertemuan09.model.response.ResponseDetailMahasiswa
import com.example.latihanpertemuan09.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMahasiswa : ViewModel(){
    private val getDataMahasiswa = MutableLiveData<List<DataMahasiswa>?>()
    private val detailMahasiswa = MutableLiveData<ResponseDetailMahasiswa?>()

    fun getDataMahasiswa(): MutableLiveData<List<DataMahasiswa>?>{
        return getDataMahasiswa
    }
    fun getDetailMahasiswa(): MutableLiveData<ResponseDetailMahasiswa?> {
        return detailMahasiswa
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
}