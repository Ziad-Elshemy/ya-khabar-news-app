package com.example.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.CategoriesFragment
import com.example.newsapp.ui.Category
import com.example.newsapp.ui.NewsFragment
import com.example.newsapp.ui.SettingsFragment
import com.google.android.material.appbar.AppBarLayout

class HomeActivity : AppCompatActivity() {


    val categoriesFragment = CategoriesFragment()

    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var drawerIcon: ImageView

    lateinit var categories: View
    lateinit var settings: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerIcon = findViewById(R.id.drawer_icon)
        categories = findViewById(R.id.categories)
        settings = findViewById(R.id.settings)
        pushFragment(categoriesFragment)
        categoriesFragment.onCategoryClickListener = object :CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick(category: Category) {
                pushFragment(NewsFragment.getInstance(category),true)
            }
        }

        drawerIcon.setOnClickListener{
            drawerLayout.open()
        }

        categories.setOnClickListener {
            pushFragment(categoriesFragment)
        }

        settings.setOnClickListener {
            pushFragment(SettingsFragment())
        }

    }

    fun pushFragment(fragment: Fragment,addToBackStack:Boolean=false){
        val fragment_transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
        if (addToBackStack)
            fragment_transaction.addToBackStack("").commit()
        else
            fragment_transaction.commit()
        drawerLayout.close()
    }

}