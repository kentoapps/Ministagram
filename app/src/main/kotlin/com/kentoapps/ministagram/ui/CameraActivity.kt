package com.kentoapps.ministagram.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kentoapps.ministagram.R

class CameraActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).run { setType("image/*") }, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE -> {
                val resultUri = data?.data
                println("======= Result uri: $resultUri")
            }
        }
    }
}
