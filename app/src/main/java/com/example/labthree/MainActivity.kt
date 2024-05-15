package com.example.labthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CustomAdapter.myClickListener {
    lateinit var recycleOne: RecyclerView
    lateinit var recycleTwo: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var progressBar2: ProgressBar
    lateinit var imgView1: ImageView
    lateinit var imgView2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleOne = findViewById(R.id.rv1)
        recycleTwo = findViewById(R.id.rv2)
        progressBar = findViewById(R.id.progressBar)
        progressBar2 = findViewById(R.id.progressBar2)
        imgView1 = findViewById(R.id.imgView1)
        imgView2 = findViewById(R.id.imgView2)
        recycleOne.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycleTwo.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val allFunctions = RetroClient().getRetrofitClients()
        allFunctions.getPosts().enqueue(
            object : Callback<PostResponse> {
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.size == 0) {
                            zeroState()
                            return
                        }
                        showState()
                        recycleOne.adapter = CustomAdapter(response.body(), this@MainActivity)

                    } else {
                        zeroState()
                        Toast.makeText(
                            this@MainActivity,
                            "Something Wrong Happen!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    zeroState()
                    Toast.makeText(this@MainActivity, "Failed to Load", Toast.LENGTH_SHORT).show()
                }

            }
        )
        allFunctions.getComments().enqueue(
            object : Callback<CommentResponse> {
                override fun onResponse(
                    call: Call<CommentResponse>,
                    response: Response<CommentResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.size == 0) {
                            zeroStateTwo()
                            return
                        }
                        showStateTwo()
                        recycleTwo.adapter = CustomAdapter2(response.body())
                    } else {
                        zeroStateTwo()
                        Toast.makeText(
                            this@MainActivity,
                            "Something Wrong Happen!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                    zeroStateTwo()
                    Toast.makeText(this@MainActivity, "Failed to Load", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    override fun onClick(position: Int) {
        progressBar2.isVisible = true
        recycleTwo.isVisible = false
        val allFunctions = RetroClient().getRetrofitClients()
        allFunctions.getCommentById("${position + 1}").enqueue(
            object : Callback<CommentResponse> {
                override fun onResponse(
                    call: Call<CommentResponse>,
                    response: Response<CommentResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.size==0){
                            zeroStateTwo()
                            return
                        }
                        showStateTwo()
                        recycleTwo.adapter = CustomAdapter2(response.body())
                    } else {
                        zeroStateTwo()
                        Toast.makeText(
                            this@MainActivity,
                            "Something Wrong Happen!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                    zeroStateTwo()
                    Toast.makeText(this@MainActivity, "Failed to Load", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    fun zeroState() {
        progressBar.isVisible = false
        recycleOne.isVisible = false
        imgView1.isVisible = true
    }

    fun zeroStateTwo() {
        progressBar2.isVisible = false
        recycleTwo.isVisible = false
        imgView2.isVisible = true
    }

    fun showState() {
        progressBar.isVisible = false
        imgView1.isVisible = false
        recycleOne.isVisible = true
    }

    fun showStateTwo() {
        progressBar2.isVisible = false
        imgView2.isVisible = false
        recycleTwo.isVisible = true
    }

}