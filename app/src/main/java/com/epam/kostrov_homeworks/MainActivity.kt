package com.epam.kostrov_homeworks

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentFragment = BB_FRAGMENT_NAME
    private val bBFragment = BBFragment()
    private val xFragment = XFragment()
    private val yFragment = YFragment()
    private val zFragment = ZFragment()

    companion object {
        private const val BB_FRAGMENT_NAME = "babyBoomer"
        private const val X_FRAGMENT_NAME = "generationX"
        private const val Y_FRAGMENT_NAME = "generationY"
        private const val Z_FRAGMENT_NAME = "generationZ"
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
            appToolbarSubtitle(getString(R.string.subtitle_bb))
        }
        supportFragmentManager.addOnBackStackChangedListener {
            binding.tvCounterClick.text = supportFragmentManager.backStackEntryCount.toString()
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BBFragment -> {
                    appToolbarSubtitle(getString(R.string.subtitle_bb))
                    loadFragment(bBFragment)
                    changeBackStack(currentFragment, BB_FRAGMENT_NAME)
                }
                R.id.XFragment -> {
                    appToolbarSubtitle(getString(R.string.subtitle_x))
                    loadFragment(xFragment)
                    changeBackStack(currentFragment, X_FRAGMENT_NAME)
                }
                R.id.YFragment -> {
                    appToolbarSubtitle(getString(R.string.subtitle_y))
                    loadFragment(yFragment)
                    changeBackStack(currentFragment, Y_FRAGMENT_NAME)
                }
                R.id.ZFragment -> {
                    appToolbarSubtitle(getString(R.string.subtitle_z))
                    loadFragment(zFragment)
                    changeBackStack(currentFragment, Z_FRAGMENT_NAME)
                }
            }
            true
        }

        supportFragmentManager.addOnBackStackChangedListener {
            binding.bottomNavigationView.getOrCreateBadge(
                when (currentFragment) {
                    BB_FRAGMENT_NAME -> R.id.BBFragment
                    X_FRAGMENT_NAME -> R.id.XFragment
                    Y_FRAGMENT_NAME -> R.id.YFragment
                    Z_FRAGMENT_NAME -> R.id.ZFragment
                    else -> R.id.fragment_container
                }
            ).apply {
                number = supportFragmentManager.backStackEntryCount
                isVisible = number >= 1
                badgeGravity = when (currentFragment) {
                    BB_FRAGMENT_NAME -> BadgeDrawable.TOP_START
                    X_FRAGMENT_NAME -> BadgeDrawable.TOP_START
                    Y_FRAGMENT_NAME -> BadgeDrawable.TOP_END
                    Z_FRAGMENT_NAME -> BadgeDrawable.TOP_END
                    else -> 0
                }
                backgroundColor = when (currentFragment) {
                    BB_FRAGMENT_NAME -> getColor(R.color.green)
                    X_FRAGMENT_NAME -> getColor(R.color.blue)
                    Y_FRAGMENT_NAME -> getColor(R.color.red)
                    Z_FRAGMENT_NAME -> getColor(R.color.yellow)
                    else -> getColor(R.color.grey)
                }
            }
        }

        binding.fab.setOnClickListener {
            with(supportFragmentManager){
                clearBackStack(BB_FRAGMENT_NAME)
                clearBackStack(X_FRAGMENT_NAME)
                clearBackStack(Y_FRAGMENT_NAME)
                clearBackStack(Z_FRAGMENT_NAME)
                popBackStack(
                    currentFragment,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
            binding.bottomNavigationView.menu.forEach { menu ->
                binding.bottomNavigationView.getOrCreateBadge(menu.itemId)
                    .also { it.isVisible = false }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
        }
    }

    private fun changeBackStack(lastFragment: String, nextFragment: String) {
        supportFragmentManager.saveBackStack(lastFragment)
        supportFragmentManager.restoreBackStack(nextFragment)
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
