package com.example.labthree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter2(var list: CommentResponse?, val listener: CustomAdapter.myClickListener) :
    RecyclerView.Adapter<CustomAdapter2.ViewHolder>() {
    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtView: TextView =itemview.findViewById(R.id.txtView2)
        init {
            itemview.setOnClickListener{
                val position=adapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycle2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtView.text="Name: "+ list!![position].name+"\n"+"Email: "+ list!![position].email+"\n"+"Body: "+ list!![position].body+"\n"+"PostID: "+ list!![position].postId+"\n"+"ID: "+ list!![position].id
    }
    interface myClickListener{
        fun onClick(position: Int)
    }

}