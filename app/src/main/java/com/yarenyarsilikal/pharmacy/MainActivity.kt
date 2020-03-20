package com.yarenyarsilikal.pharmacy

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import com.yarenyarsilikal.pharmacy.network.model.CityList
import com.yarenyarsilikal.pharmacy.network.model.User
import com.yarenyarsilikal.pharmacy.ui.news.NewsFragment
import com.yarenyarsilikal.pharmacy.ui.pharmacies.PharmaciesFragment
import com.yarenyarsilikal.pharmacy.ui.profile.ProfileFragment
import com.yarenyarsilikal.pharmacy.util.PrefUtil
import com.yarenyarsilikal.pharmacy.util.extStartActivity
import com.yarenyarsilikal.pharmacy.util.extToastMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream


class MainActivity : AppCompatActivity(), View.OnClickListener,
    ProfileFragment.OnFragmentInteractionListener {
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    private lateinit var imageViewPicture: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewEmail: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Hawk.init(this).build()

        initView()
        setUpViewPager()
        saveCityList(loadJson(this))

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
                        bottomNavView.menu.findItem(R.id.navigation_news).isChecked = true
                    }
                    1 -> {
                        bottomNavView.menu.findItem(R.id.navigation_pharmacies).isChecked = true
                    }
                    2 -> {
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
                viewPager.currentItem = 0
                closeDrawer(view is NavigationView)
                return true
            }

            R.id.navigation_pharmacies -> {
                viewPager.currentItem = 1
                closeDrawer(view is NavigationView)
                return true
            }

            R.id.navigation_profile -> {
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
        setSupportActionBar(toolbar)
        imageButtonLogOut.setOnClickListener(this)
        imageButtonFacebook.setOnClickListener(this)
        imageButtonInstagram.setOnClickListener(this)
        imageButtonTwitter.setOnClickListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        imageViewPicture = navView.getHeaderView(0).findViewById(R.id.imageViewPicture)
        textViewName = navView.getHeaderView(0).findViewById(R.id.textViewName)
        textViewEmail = navView.getHeaderView(0).findViewById(R.id.textViewEmail)
        setNavHeaderData(PrefUtil.getUser())
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
        val id: Int = item.itemId
        return if (id == R.id.logout) {
            logOut()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun saveCityList(loadJson: String?) {
        val city = Gson().fromJson(loadJson, CityList::class.java)
        val hashMap = hashMapOf<String, List<String>>()
        for (c in city.cities) {
            hashMap[c.il] = c.ilceleri
        }
        PrefUtil.setCityList(hashMap)
    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            input = context.assets.open("city.json")

            val size = input.available()

            val buffer = ByteArray(size)

            input.read(buffer)

            jsonString = String(buffer)
            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            input?.close()
        }

        return null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            imageButtonLogOut.id -> logOut()
            imageButtonFacebook.id -> this.extToastMessage("Facebook Adresimiz : ...........")
            imageButtonInstagram.id -> this.extToastMessage("Instagram Adresimiz : ...........")
            imageButtonTwitter.id -> this.extToastMessage("Twitter Adresimiz : ...........")
        }
    }

    private fun logOut() {
        PrefUtil.setFirstTime(true)
        this@MainActivity extStartActivity LoginActivity::class.java
        finish()
    }

    override fun onFragmentInteraction(msg: String) {
        this.extToastMessage(msg)
        viewPager.currentItem = 0
        setNavHeaderData(PrefUtil.getUser())
        // TODO: Profil değişikliklerini nav headerda güncelle
    }

    private fun setNavHeaderData(user: User) {

        //imageViewPicture
        textViewName.text = "${user.name} ${user.surname}"
        textViewEmail.text = user.email
    }


}
