package com.akhmad.bogorism2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.ui.detail.DetailActivity

class ListFoodActivity : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    private val list = ArrayList<User>()
    private lateinit var listUserAdapter: ListUserAdapter

    private fun showSelectedUser(user: User) {
        Toast.makeText(this, "Kamu memilih " + user.placename, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_food)

        rvUser = findViewById(R.id.rvHeroes)
        rvUser.setHasFixedSize(true)

        list.addAll(listAllUser)
        showRecyclerList()

    }

    private val listAllUser: ArrayList<User>
        get() {
            val username = resources.getStringArray(R.array.placename)
            val rating = resources.getStringArray(R.array.rating)
            val gambar = resources.obtainTypedArray(R.array.gambar)
            val description = resources.getStringArray(R.array.description)


            val listUser = ArrayList<User>()
            for (i in 10..14) {
                val user = User(username[i],rating[i],gambar.getResourceId(i, -1), description[i])
                listUser.add(user)
            }
            return listUser
        }
    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        listUserAdapter = ListUserAdapter(list)

        rvUser.apply {
            layoutManager = LinearLayoutManager (this@ListFoodActivity)
            adapter = listUserAdapter
            setHasFixedSize(true)
        }
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intentToDetail = Intent(this@ListFoodActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })


    }
}