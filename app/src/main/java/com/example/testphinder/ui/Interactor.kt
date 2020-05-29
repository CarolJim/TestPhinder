package com.example.testphinder.ui

import com.example.testphinder.POJO.DataModel
import com.example.testphinder.conection.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private var presenter: Contracts.Presenter) : Contracts.interactor {




    override fun getDataInitial() {
        val call: Call<DataModel> = ApiClient.getClient.getload()
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                response.toString()
               // listaSupers.addAll(response!!.body()!!.albumid)
                presenter.showData()
            }
            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                t.toString()
            }
        })
    }
}