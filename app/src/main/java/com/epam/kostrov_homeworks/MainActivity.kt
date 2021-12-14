package com.epam.kostrov_homeworks

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.epam.counter.GPSMonitor
import com.epam.counter.NetworkMonitor
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.vmadalin.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_COUNTER = "KEY_COUNTER"
        private const val PACKAGE = "package"
    }

    private lateinit var binding: ActivityMainBinding
    private var counter = 100_000
    private val counterMax = 100_000
    private var counterTopStart = 0
    private var counterTopEnd = 0
    private var counterBottomStart = 0
    private var counterBottomEnd = 0

    private val networkMonitor = NetworkMonitor(this)
    private val snackbarGPS by lazy { Snackbar.make(binding.root, "haven't GPS",
        Snackbar.LENGTH_INDEFINITE) }
    private val snackbarInternet by lazy { Snackbar.make(binding.root, "haven't internet",
        Snackbar.LENGTH_INDEFINITE) }
    private val requestLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(), ::onPermissionResult
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setVisibility()



        networkMonitor.networkListener = { isEnabled ->
            runOnUiThread {
                when (isEnabled) {
                    true -> {
                        binding.buttonInternet?.isEnabled = true
                        snackbarInternet.dismiss()
                    }
                    false -> {
                        binding.buttonInternet?.isEnabled = false
                        snackbarInternet.show()

                    }
                }
            }
        }

        val startText =
            counter.toString() + "/" + counterMax.toString() + " " + getString(R.string.mAh)

        binding.textviewFirst.text = startText

        binding.root.setOnClickListener {
            counter--
            "$counter/$counterMax ${getString(R.string.mAh)}".also {
                binding.textviewFirst.text = it
            }
        }

        binding.buttonFirst.setOnClickListener {
            if (counter < counterMax)
                counter++
            "$counter/$counterMax ${getString(R.string.mAh)}".also {
                binding.textviewFirst.text = it
            }
        }

        binding.tvTopEnd.setOnClickListener {
            counterTopEnd++
            binding.tvTopEnd.text = counterTopEnd.toString()
        }
        binding.tvTopStart.setOnClickListener {
            counterTopStart++
            binding.tvTopStart.text = counterTopStart.toString()
        }
        binding.tvBottomEnd.setOnClickListener {
            counterBottomEnd++
            binding.tvBottomEnd.text = counterBottomEnd.toString()
        }
        binding.tvBottomStart.setOnClickListener {
            counterBottomStart++
            binding.tvBottomStart.text = counterBottomStart.toString()
        }

        binding.buttonInternet?.setOnClickListener {
            counter -= 1000
            "$counter/$counterMax ${getString(R.string.mAh)}".also {
                binding.textviewFirst.text = it
            }
        }

        binding.buttonPermissionGps?.setOnClickListener {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        binding.buttonGps?.setOnClickListener {
            val gps = GPSMonitor(this)
            gps.getCurrentLocation()
            counter -= 10000
            "$counter/$counterMax ${getString(R.string.mAh)}".also {
                binding.textviewFirst.text = it
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt(KEY_COUNTER)
        val newText =
            counter.toString() + "/" + counterMax.toString() + " " + getString(R.string.mAh)
        binding.textviewFirst.text = newText
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

    private fun hasPermission() =
        EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)

    private fun setVisibility() {
        binding.buttonGps?.isVisible = hasPermission()
        binding.buttonPermissionGps?.isVisible = !hasPermission()
    }

    private fun onPermissionResult(granted: Boolean) {
        if (granted) {
            onPermissionGranted()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                openSettings()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openSettings() {
        val settingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts(PACKAGE, packageName, null)
        )
        if (packageManager.resolveActivity(
                settingsIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) != null
        ) {
            AlertDialog.Builder(this)
                .setMessage("Permission denied, open settings?")
                .setPositiveButton("Open") { _, _ -> startActivity(settingsIntent) }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }

    private fun onPermissionGranted() {
        setVisibility()
    }
}
