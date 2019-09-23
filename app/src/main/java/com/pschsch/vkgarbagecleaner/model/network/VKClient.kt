package com.pschsch.vkgarbagecleaner.model.network

import com.pschsch.vkgarbagecleaner.model.DeleteResponse
import com.pschsch.vkgarbagecleaner.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

interface VKClient {
    @GET("method/video.get")
    @Throws(Exception::class)
    suspend fun getVideo(
        @Query("owner_id") ownerId: Int,
        @Query("access_token") accessToken: String,
        @Query("v") vkApiVersion: Double
    ): VideoResponse

    @GET("method/video.delete")
    @Throws(Exception::class)
    suspend fun deleteVideo(
        @Query("video_id") videoId : Int,
        @Query("owner_id") ownerId : Int,
        @Query("target_id") userId : Int,
        @Query("access_token") accessToken: String,
        @Query("v") vkApiVersion: Double
    ) : DeleteResponse

}