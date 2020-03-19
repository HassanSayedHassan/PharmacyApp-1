package com.yarenyarsilikal.pharmacy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.yarenyarsilikal.pharmacy.ui.news.NewsFragment
import com.yarenyarsilikal.pharmacy.ui.pharmacies.PharmaciesFragment
import com.yarenyarsilikal.pharmacy.ui.profile.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setUpViewPager()



        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer(true)
        } else {
            super.onBackPressed()
        }
    }

    private fun setUpViewPager() {
        val pageList = ArrayList<Fragment>()
        pageList.add(NewsFragment())
        pageList.add(PharmaciesFragment())
        pageList.add(ProfileFragment())


        val pagerAdapter = ViewPagerAdapter(pageList, this)
        viewPager.adapter = pagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        title = resources.getString(R.string.title_news)
                        bottomNavView.menu.findItem(R.id.navigation_news).isChecked = true
                    }
                    1 -> {
                        title = resources.getString(R.string.title_pharmacies)
                        bottomNavView.menu.findItem(R.id.navigation_pharmacies).isChecked = true
                    }
                    2 -> {
                        title = resources.getString(R.string.title_profile)
                        bottomNavView.menu.findItem(R.id.navigation_profile).isChecked = true
                    }
                }
            }
        })

        bottomNavView.setOnNavigationItemSelectedListener {
            chooseMenuItem(
                it.itemId,
                bottomNavView
            )
        }

        navView.setNavigationItemSelectedListener { chooseMenuItem(it.itemId, navView) }


    }

    private fun chooseMenuItem(itemId: Int, view: View): Boolean {
        when (itemId) {
            R.id.navigation_news -> {
                title = resources.getString(R.string.title_news)
                viewPager.currentItem = 0
                closeDrawer(view is NavigationView)
                return true
            }

            R.id.navigation_pharmacies -> {
                title = resources.getString(R.string.title_pharmacies)
                viewPager.currentItem = 1
                closeDrawer(view is NavigationView)
                return true
            }

            R.id.navigation_profile -> {
                title = resources.getString(R.string.title_profile)
                viewPager.currentItem = 2
                closeDrawer(view is NavigationView)
                return true
            }

        }
        return false
    }

    private fun initView() {
        navView = findViewById(R.id.nav_view)
        bottomNavView = findViewById(R.id.bottomNavView)
        viewPager = findViewById(R.id.viewPager)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun closeDrawer(boolean: Boolean) {
        if (boolean)
            drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.itemId
        return if (id == R.id.logout) {
            // TODO: cikis yap
            true
        } else super.onOptionsItemSelected(item)
    }


}
