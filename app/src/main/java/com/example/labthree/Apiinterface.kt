package com.example.labthree

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apiinterface {
        @GET("posts") // Replace "endpoint" with your actual endpoint
        fun getPosts():Call<PostResponse>
        @GET("comments") // Replace "endpoint" with your actual endpoint
        fun getComments():Call<CommentResponse>
        @GET("comments")
        fun getCommentById(@Query("postId") postId: String):Call<CommentResponse>
}