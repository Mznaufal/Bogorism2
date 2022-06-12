package com.akhmad.bogorism2.ui.main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.akhmad.bogorism2.ImageData
import com.akhmad.bogorism2.ImageSliderAdapter
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.databinding.ActivityMainBinding
import com.akhmad.bogorism2.ui.*
import com.akhmad.bogorism2.ui.factory.UserViewModelFactory
import com.akhmad.bogorism2.ui.login.LoginActivity
import com.akhmad.bogorism2.ui.map.MapsActivity
import com.akhmad.bogorism2.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            ImageData(
                "https://storage.googleapis.com/bogorism/imagesPlaces/23-A.jpg"
            )
        )
        list.add(
            ImageData(
                "https://storage.googleapis.com/bogorism/imagesPlaces/55-A.jpg"
            )
        )
        list.add(
            ImageData(
                "https://storage.googleapis.com/bogorism/imagesPlaces/24-A.jpg"
            )
        )
        list.add(
            ImageData(
                "https://storage.googleapis.com/bogorism/imagesPlaces/35-A.jpg"
            )
        )
        list.add(
            ImageData(
                "https://storage.googleapis.com/bogorism/imagesPlaces/18-A.jpg"
            )
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        binding.catNatural.setOnClickListener{
            val intent = Intent(this, ListNatureActivity::class.java)
            startActivity(intent)
        }

        binding.catFood.setOnClickListener{
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivity(intent)
        }

        binding.catReligious.setOnClickListener{
            val intent = Intent(this,ListReligiousActivity::class.java)
            startActivity(intent)
        }

        binding.catPark.setOnClickListener{
            val intent = Intent(this,ListParkActivity::class.java)
            startActivity(intent)
        }

        binding.catAnimals.setOnClickListener {
            val intent = Intent(this,ListAnimalActivity::class.java)
            startActivity(intent)
        }

        setupViewModel()
    }
    private fun selectedDot(position: Int) {
        for(i in 0  until list.size){
            if(i==position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.green_up))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.purple_500))
        }
    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(this))
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            }else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotIndicator.addView(dots[i])
        }
    }

    private fun setupViewModel() {
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(
            this,
            factory
        )[MainViewModel::class.java]
        mainViewModel.isLogin().observe(this){
            if (!it){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                val intent = Intent(this, SearchActivity::class.java)
//                intent.putExtra(MapsActivity.EXTRA_TOKEN, token)
                startActivity(intent)
                true
            }
            R.id.menu_map -> {
                val intent = Intent(this, MapsActivity::class.java)
//                intent.putExtra(MapsActivity.EXTRA_TOKEN, token)
                startActivity(intent)
                true
            }
            R.id.menu_logout -> {
                mainViewModel.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            else -> false
        }
    }
}