package com.example.nasaassignment.ui
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.nasaassignment.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        setupBottomNavMenu(navController)

    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> {

                showPopUp()

                return true
            }
           // todo call api with menu item text as camera name item.tooltipText

        }
        return true
    }

    fun showPopUp() {

        val selectedItem = bottomNav.selectedItemId
        val menuItem = bottomNav.menu.findItem(selectedItem)
        Log.d("selectedItem", "setupBottomNavMenu: $menuItem")

        val menuItemView = findViewById<View>(R.id.filter)
        val popupMenu = PopupMenu(this, menuItemView)

        when (selectedItem) {
            R.id.curiosityFragment -> {

                popupMenu.inflate(R.menu.filter_curiosity)
            }
            R.id.opportunityFragment -> {

                popupMenu.inflate(R.menu.filter_opportunity)
            }
            R.id.spiritFragment -> {

                popupMenu.inflate(R.menu.filter_spirit)
            }
        }
        popupMenu.show()

    }

}