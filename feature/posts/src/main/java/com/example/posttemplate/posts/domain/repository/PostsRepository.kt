package com.example.posttemplate.posts.domain.repository

import com.example.posttemplate.data.models.PostDto

interface PostsRepository {
    suspend fun getPosts(): Result<List<PostDto>>
    suspend fun getPostById(id: Int): Result<PostDto>

    companion object
}
