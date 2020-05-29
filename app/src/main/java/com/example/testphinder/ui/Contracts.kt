package com.example.testphinder.ui

class Contracts {

    interface Presenter{
        fun initViews()
        fun showData()

    }

    interface interactor{
        fun getDataInitial()
    }

    interface Router {

    }


}