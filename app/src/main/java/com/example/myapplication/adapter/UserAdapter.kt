package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.activity.DetailUserActivity
import com.example.myapplication.model.User

class UserAdapter(private val list :ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        fun bind(user: User){
            with(itemView){
                val name = user.login
                tvName.text = name
                Glide.with(context)
                    .load(user.avatar_url)
                    .into(imageView)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intentDetail.putExtra("EXTRA_USERNAME", list[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }


    }

    override fun getItemCount(): Int = list.size


}