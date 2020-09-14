package com.example.userapplication.requests
import com.example.userapplication.requests.response.Image
import com.example.userapplication.requests.response.ImageListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.HashMap

/**
 * Interface that define the possible http operations
 */
interface ImageListApi{

    @GET("list")
    fun getImages(@Query("page") page: Int, @Query("limit")  limit: Int):Call<MutableList<Image>?>

}


