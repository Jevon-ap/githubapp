package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.api.RetrfitClient
import com.example.myapplication.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val userAdapter: UserAdapter by lazy {
        UserAdapter(userList)
    }
    private var userList = ArrayList<User>()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_user)

        // Create an instance of the UserAdapter and set it as the adapter for the recyclerView
        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        showUser()
    }

    private fun showUser() {
        // Initialize the recyclerView property
        recyclerView = findViewById(R.id.rv_user)

        // Create an instance of the UserAdapter and set it as the adapter for the recyclerView
        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        RetrfitClient.instance.getUser().enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        userList.addAll(users)
                        userAdapter.notifyDataSetChanged()
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("API", "Error: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.e("API", "Error: ${t.message}")

            }

        })
    }
}