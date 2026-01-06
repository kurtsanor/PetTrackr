package com.example.tracker.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tracker.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        val navBar = findViewById< BottomNavigationView>(R.id.bottomNavigationView)

        openFragment(HomeFragment())

        navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.nav_pets -> {
                    openFragment(PetsFragment())
                    true
                }
                R.id.nav_account -> {
                    openFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }
    }

        private fun openFragment (fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView2, fragment).commit()
    }
}