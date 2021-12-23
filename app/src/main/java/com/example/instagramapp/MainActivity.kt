package com.example.instagramapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.instagramapp.Fragments.HomeFragment
import com.example.instagramapp.Fragments.NotificationFragment
import com.example.instagramapp.Fragments.ProfileFragment
import com.example.instagramapp.Fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.nav_home ->
            {
                routeToFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_search ->
            {
                routeToFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_add_post ->
            {

                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_notifications ->
            {
                routeToFragment(NotificationFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_profile ->
            {
                routeToFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    private fun routeToFragment(fragment: Fragment)
    {
        val routeFrag = supportFragmentManager.beginTransaction()
        routeFrag.replace(R.id.fragment_container, fragment)
        routeFrag.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        routeToFragment(HomeFragment())

    }

}