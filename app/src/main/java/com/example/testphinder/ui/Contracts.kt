package com.example.testphinder.ui

class Contracts {

    interface Presenter{
        fun initViews()
        fun showData(message: String)

    }

    interface interactor{
        fun getDataInitial()
    }

    interface Router {
        fun showNext()

    }


}