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
import android.widget.Toast
import com.example.nasaassignment.R
import com.example.nasaassignment.ui.rovers.curiosity.CuriosityFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    private var listener: IMenuOnClick? = null

    private lateinit var popupMenu: PopupMenu

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

        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Log.d("TAG", "onOptionsItemSelected: "+item.itemId)
        when (item.itemId) {
            R.id.filter -> {

                showPopUp()

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    fun showPopUp() {

        val selectedItem = bottomNav.selectedItemId
        val menuItem = bottomNav.menu.findItem(selectedItem)
        Log.d("selectedItem", "setupBottomNavMenu: $menuItem")

        val menuItemView = findViewById<View>(R.id.filter)
        popupMenu = PopupMenu(this, menuItemView)

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
        popupMenu.setOnMenuItemClickListener {
            listener?.onMenuClick(it.title.toString())
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()

    }

    fun addListener(listener: IMenuOnClick){
        this.listener = listener
    }
}