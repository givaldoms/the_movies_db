package dev.givaldo.feature.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import dev.givaldo.feature.R


class AppActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.baseContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_app)
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.white))

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
