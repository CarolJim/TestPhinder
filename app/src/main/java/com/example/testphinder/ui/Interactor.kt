package com.example.testphinder.ui

import com.example.testphinder.POJO.DataModel
import com.example.testphinder.conection.ApiClient
import retrofit2.Callback


class Interactor : Contracts.interactor {
    //variable
    private  var presenter : Contracts.Presenter


    constructor(presenter: Contracts.Presenter){
        this.presenter = presenter
    }
    override fun getDataInitial() {
        val call: retrofit2.Call<DataModel> = ApiClient.getClient.getload()
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: retrofit2.Call<DataModel>?, response: retrofit2.Response<DataModel>?) {
                response.toString()
                //listaSupers.addAll(response!!.body()!!.albumid)
                presenter.showData()
            }
            override fun onFailure(call: retrofit2.Call<DataModel>?, t: Throwable?) {
                t.toString()
            }
        })
    }

}