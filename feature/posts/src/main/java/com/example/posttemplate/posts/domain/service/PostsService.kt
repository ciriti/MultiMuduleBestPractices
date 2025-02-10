package com.example.posttemplate.posts.domain.service

import com.example.posttemplate.posts.data.service.PostsServiceImpl
import com.example.posttemplate.posts.domain.model.Post
import com.example.posttemplate.posts.domain.repository.PostsRepository

interface PostsService {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getPostById(id: Int): Result<Post>

    companion object {
        fun create(postRepository: PostsRepository): PostsService =
            PostsServiceImpl(postRepository)
    }
}
