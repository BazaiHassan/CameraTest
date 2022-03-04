package com.masterdev.cameratest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var btnCam: Button
    private lateinit var imgCam: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

                if (isGranted) {
                    Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, 1001)

                } else {
                    Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()

                }

            }

        btnCam = findViewById(R.id.btn_cam)
        imgCam = findViewById(R.id.imageView)
        btnCam.setOnClickListener {
            request.launch(android.Manifest.permission.CAMERA)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK) {
            if (requestCode == 1001) {
                val bitmapImage = data?.extras?.get("data") as Bitmap
                imgCam.setImageBitmap(bitmapImage)
            }
        }
    }
}