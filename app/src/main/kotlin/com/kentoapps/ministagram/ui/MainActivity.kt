package com.kentoapps.ministagram.ui

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.ui.post.PostFragmentArgs
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = nav_host_fragment as NavHostFragment? ?: return

        val navController = host.navController
        NavigationUI.setupWithNavController(bottom_nav_view, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("======= onOptions Activity")
        return when (item.itemId) {
            R.id.gallery -> {
                startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).run { setType("image/*") }, REQUEST_CODE)
                super.onOptionsItemSelected(item)
            }
            else -> NavigationUI.onNavDestinationSelected(item,
                    Navigation.findNavController(this, R.id.nav_host_fragment))
                    || super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageUri = data?.data?.toString()
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && imageUri != null) {
            val bundle = PostFragmentArgs.Builder(imageUri).build().toBundle()
            Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.postFragment, bundle)
        }
    }

    companion object {
        private const val REQUEST_CODE = 101
    }
}
