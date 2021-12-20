package com.epam.kostrov_homeworks

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.epam.kostrov_homeworks.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        supportActionBar?.hide()

        var appToolbarSubtitle = binding.topAppBar
        val babyBoomerFragment = BBFragment()
        val xFragment = XFragment()
        val yFragment = YFragment()
        val zFragment = ZFragment()
        loadFragment(babyBoomerFragment)
        appToolbarSubtitle.subtitle = "Baby Boomer"

        supportFragmentManager.addOnBackStackChangedListener {
            binding.tvCounterClick.text = supportFragmentManager.backStackEntryCount.toString()
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BBFragment -> {
                    loadFragment(babyBoomerFragment)
                    appToolbarSubtitle.subtitle = "Baby Boomer"
                    supportFragmentManager.restoreBackStack("babyBoomer")
                    supportFragmentManager.saveBackStack("generationX")
                    supportFragmentManager.saveBackStack("generationY")
                    supportFragmentManager.saveBackStack("generationZ")
//                    supportFragmentManager.addOnBackStackChangedListener {
//                        binding.bottomNavigationView.getOrCreateBadge(R.id.BBFragment).apply {
//                            number = count
//                            isVisible = true
//                            badgeGravity = BadgeDrawable.TOP_START
//                            backgroundColor = Color.RED
//                        }
//                    }
                }
                R.id.XFragment -> {
                    loadFragment(xFragment)
                    appToolbarSubtitle.subtitle = "Generation X"
                    supportFragmentManager.saveBackStack("babyBoomer")
                    supportFragmentManager.restoreBackStack("generationX")
                    supportFragmentManager.saveBackStack("generationY")
                    supportFragmentManager.saveBackStack("generationZ")


                }
                R.id.YFragment -> {
                    loadFragment(yFragment)
                    appToolbarSubtitle.subtitle = "Generation Y"
                    supportFragmentManager.saveBackStack("babyBoomer")
                    supportFragmentManager.saveBackStack("generationX")
                    supportFragmentManager.restoreBackStack("generationY")
                    supportFragmentManager.saveBackStack("generationZ")

                }
                R.id.ZFragment -> {
                    loadFragment(zFragment)
                    appToolbarSubtitle.subtitle = "Generation Z"
                    supportFragmentManager.saveBackStack("babyBoomer")
                    supportFragmentManager.saveBackStack("generationX")
                    supportFragmentManager.saveBackStack("generationY")
                    supportFragmentManager.restoreBackStack("generationZ")

                }
            }
            true
        }

        binding.bottomNavigationView.getOrCreateBadge(R.id.BBFragment).apply {
            isVisible = true
            badgeGravity = BadgeDrawable.TOP_START
            backgroundColor = Color.GREEN
        }

            binding.bottomNavigationView.getOrCreateBadge(R.id.XFragment).apply {
                number = supportFragmentManager.backStackEntryCount
                isVisible = true
                badgeGravity = BadgeDrawable.TOP_START
                backgroundColor = Color.BLUE
            }

            binding.bottomNavigationView.getOrCreateBadge(R.id.YFragment).apply {
                number = supportFragmentManager.backStackEntryCount
                isVisible = true
                badgeGravity = BadgeDrawable.TOP_END
                backgroundColor = Color.RED
            }


            binding.bottomNavigationView.getOrCreateBadge(R.id.ZFragment).apply {
                number = supportFragmentManager.backStackEntryCount
                isVisible = true
                badgeGravity = BadgeDrawable.TOP_END
                backgroundColor = Color.YELLOW
            }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .commit()

    }

//    override fun onBackPressed() {
//        val transaction=supportFragmentManager.beginTransaction()
//        val currentFragment=supportFragmentManager.findFragmentById(R.id.fragment_container)
//            if (currentFragment != null) {
//                transaction.remove(currentFragment)
//                transaction.commit()
//            }
//        else{
//            super.onBackPressed()
//        }
//    }


}