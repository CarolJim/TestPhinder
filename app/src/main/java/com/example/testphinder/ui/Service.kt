package com.example.testphinder.ui

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testphinder.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class Service : AppCompatActivity(),Contracts.Presenter {

    private lateinit var interactor: Contracts.interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnTag = Button(this)
        btnTag.setLayoutParams(
            ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        )
        setContentView(R.layout.activity_service)
        interactor = Interactor(this)
        initViews()
    }

    override fun initViews() {
        interactor.getDataInitial()
    }

    override fun showData(message: String) {
        try {
            val url : URL = URL(message)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestMethod("GET")
            urlConnection.setDoOutput(true)
            urlConnection.connect()
            val SDCardRoot = Environment.getExternalStorageDirectory()
            val file = File(SDCardRoot, "cargaInicial_flat_20200522181609_89602.zip")
            val fileOutput = FileOutputStream(file)
            val inputStream: InputStream = urlConnection.inputStream
            val totalSize = urlConnection.contentLength
            var downloadedSize = 0
            val buffer = ByteArray(1024)
            var bufferLength: Int = inputStream.read(buffer)
            while (bufferLength > 0 ) {
                fileOutput.write(buffer, 0, bufferLength)
                downloadedSize += bufferLength

            }
            //cerramos
            fileOutput.close();

        }catch (t: Throwable) {
            t.message
        } catch (e: IOException) {
            e.printStackTrace();
        }
    }
}
