package com.kentoapps.ministagram.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kentoapps.ministagram.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = nav_host_fragment as NavHostFragment? ?: return

        val navController = host.navController
        NavigationUI.setupWithNavController(bottom_nav_view, navController)
    }
}
