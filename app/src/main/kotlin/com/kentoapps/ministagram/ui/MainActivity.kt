package com.kentoapps.ministagram.ui

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.ui.post.PostFragmentArgs
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.*
import javax.inject.Inject

@RuntimePermissions
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var imageUri: Uri? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = navHostFragment as NavHostFragment? ?: return

        val navController = host.navController
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.gallery -> startActivityForResult(createIntentForGallery(), REQUEST_GALLERY)
            R.id.camera_activity -> showCameraWithPermissionCheck()
            else -> return NavigationUI.onNavDestinationSelected(item,
                    Navigation.findNavController(this, R.id.navHostFragment))
                    || super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createIntentForGallery() = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return

        if (requestCode == REQUEST_GALLERY) imageUri = data?.data

        if (imageUri == null) return
        val bundle = PostFragmentArgs.Builder(imageUri.toString()).build().toBundle()
        Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.postFragment, bundle)
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showCamera() {
        val filename = System.currentTimeMillis().toString() + ".jpg"

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val intent =  Intent().apply {
            action = MediaStore.ACTION_IMAGE_CAPTURE
            putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }

        startActivityForResult(intent, REQUEST_CAMERA)
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showRationaleForWriteExternalStorage(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onWriteExternalStorageDenied() {
        Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onWriteExternalStorageNeverAskAgain() {
        Toast.makeText(this, R.string.permission_never_ask, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    companion object {
        private const val REQUEST_GALLERY = 101
        private const val REQUEST_CAMERA = 102
    }
}
