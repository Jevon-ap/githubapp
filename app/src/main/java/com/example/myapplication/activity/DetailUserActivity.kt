package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.api.RetrfitClient
import com.example.myapplication.model.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME="extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        val username =intent?.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)
        if (username != null) {
            setUserDetail(username.toString())
        }
    }

    private fun setUserDetail(username: String){
        val tv_name :TextView = findViewById(R.id.tv_name)
        val tvUsername :TextView = findViewById(R.id.tv_username)
        val imageView : ImageView = findViewById(R.id.imageView2)
        username?.let {
            RetrfitClient.instance
                .findUserDetailByUsername(it)
                .enqueue(object: Callback<UserDetail>{
                    override fun onResponse(
                        call: Call<UserDetail>,
                        response: Response<UserDetail>
                    ) {
                        if(response.isSuccessful){
                                val data = response.body()
                                if (data != null){
                                    tv_name.text = data.name
                                    tvUsername.text=data.login
                                    Glide.with(this@DetailUserActivity)
                                        .load(data.avatar_url)
                                        .transition(DrawableTransitionOptions.withCrossFade())
                                        .centerCrop()
                                        .into(imageView)
                                }

                        }
                        else {
                            // Handle unsuccessful response
                            Log.e("API", "Error: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                        Log.e("API", "Error: ${t.message}")
                    }

                })
        }
    }
}