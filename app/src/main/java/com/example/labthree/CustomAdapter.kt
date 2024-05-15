package com.example.labthree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var list: PostResponse?,val listener:myClickListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtView:TextView=itemview.findViewById(R.id.txtView)
        init {
            itemview.setOnClickListener{
                val position=adapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtView.text="Title: "+ list!![position].title+"\n"+"Body: "+ list!![position].body+"\n"+"ID: "+ list!![position].id+"\n"+"UserID: "+ list!![position].userId
    }
    interface myClickListener{
        fun onClick(position: Int)
    }

}