package com.example.testphinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testphinder.R

class Service : AppCompatActivity(),Contracts.Presenter {

    private lateinit var interactor: Contracts.interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        interactor = Interactor(this)
        initViews()
    }

    override fun initViews() {
        interactor.getDataInitial()
    }

    override fun showData() {
        TODO("Not yet implemented")
    }
}
