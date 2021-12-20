package com.epam.kostrov_homeworks

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentFragment = BB_FRAGMENT
    private val bBFragment = BBFragment()
    private val xFragment = XFragment()
    private val yFragment = YFragment()
    private val zFragment = ZFragment()

    companion object {
        private const val BB_FRAGMENT = "babyBoomer"
        private const val X_FRAGMENT = "generationX"
        private const val Y_FRAGMENT = "generationY"
        private const val Z_FRAGMENT = "generationZ"

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        window.statusBarColor = getColor(R.color.grey)


        if (savedInstanceState == null) {
            loadFragment(bBFragment)
            appToolbarSubtitle("Baby Boomer")
        }
        supportFragmentManager.addOnBackStackChangedListener {
            binding.tvCounterClick.text = supportFragmentManager.backStackEntryCount.toString()
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BBFragment -> {
                    appToolbarSubtitle("Baby Boomer")
                    loadFragment(bBFragment)
                    changeBackStack(currentFragment, BB_FRAGMENT)
                }
                R.id.XFragment -> {
                    appToolbarSubtitle("Generation X")
                    loadFragment(xFragment)
                    changeBackStack(currentFragment, X_FRAGMENT)
                }
                R.id.YFragment -> {
                    appToolbarSubtitle("Generation Y")
                    loadFragment(yFragment)
                    changeBackStack(currentFragment, Y_FRAGMENT)
                }
                R.id.ZFragment -> {
                    appToolbarSubtitle("Generation Z")
                    loadFragment(zFragment)
                    changeBackStack(currentFragment, Z_FRAGMENT)
                }
            }
            true
        }

        supportFragmentManager.addOnBackStackChangedListener {
            binding.bottomNavigationView.getOrCreateBadge(
                when (currentFragment) {
                    BB_FRAGMENT -> R.id.BBFragment
                    X_FRAGMENT -> R.id.XFragment
                    Y_FRAGMENT -> R.id.YFragment
                    Z_FRAGMENT -> R.id.ZFragment
                    else -> R.id.fragment_container
                }
            ).apply {
                number = supportFragmentManager.backStackEntryCount
                isVisible = number > 0
            }
        }

        binding.bottomNavigationView.getOrCreateBadge(R.id.BBFragment).apply {
            isVisible = false
            badgeGravity = BadgeDrawable.TOP_START
            backgroundColor = getColor(R.color.green)
        }

        binding.bottomNavigationView.getOrCreateBadge(R.id.XFragment).apply {
            isVisible = false
            badgeGravity = BadgeDrawable.TOP_START
            backgroundColor = getColor(R.color.blue)
        }

        binding.bottomNavigationView.getOrCreateBadge(R.id.YFragment).apply {
            isVisible = false
            badgeGravity = BadgeDrawable.TOP_END
            backgroundColor = getColor(R.color.red)
        }

        binding.bottomNavigationView.getOrCreateBadge(R.id.ZFragment).apply {
            isVisible = false
            badgeGravity = BadgeDrawable.TOP_END
            backgroundColor = getColor(R.color.yellow)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
        }
    }

    private fun changeBackStack(lastFragment: String, nextFragment: String) {
        supportFragmentManager.restoreBackStack(nextFragment)
        supportFragmentManager.saveBackStack(lastFragment)
        currentFragment = nextFragment
    }

    private fun appToolbarSubtitle(subtitle: String) {
        binding.topAppBar.subtitle = subtitle
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
