package com.kentoapps.ministagram.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
