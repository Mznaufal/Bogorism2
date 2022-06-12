package com.akhmad.bogorism2.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.ui.User
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_PERSON = "extra_person"
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail User"

        var imgAvatar : ImageView = findViewById(R.id.iv_imagePlace)
        var tvPlaceName: TextView = findViewById(R.id.tv_nama)
        var tvDeskripsi: TextView = findViewById(R.id.tv_description)
        var tvRating : TextView = findViewById(R.id.tv_rating)





        val data = intent.getParcelableExtra<User>("DATA")
        tvPlaceName.text = data?.placename.toString()
        tvDeskripsi.text = data?.description.toString()
        tvRating.text = data?.rating.toString()



        Glide.with(this)
            .load(data!!.gambar)
            .circleCrop()
            .into(imgAvatar)



    }
}