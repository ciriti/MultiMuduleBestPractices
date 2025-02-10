package com.example.posttemplate.posts.data.service

import com.example.posttemplate.posts.domain.extensions.toDomain
import com.example.posttemplate.posts.domain.model.Post
import com.example.posttemplate.posts.domain.repository.PostsRepository
import com.example.posttemplate.posts.domain.service.PostsService


internal class PostsServiceImpl(
    private val postRepository: PostsRepository
) : PostsService {

    override suspend fun getPosts(): Result<List<Post>> =
        runCatching {
            val postsDto = postRepository.getPosts().getOrElse { throw it }
            postsDto.map { postDto ->
                postDto.toDomain(postDto.userId)
            }
        }

    override suspend fun getPostById(id: Int): Result<Post> =
        runCatching {
            val postDto = postRepository.getPostById(id).getOrElse { throw it }
            postDto.toDomain(postDto.userId)
        }
}
