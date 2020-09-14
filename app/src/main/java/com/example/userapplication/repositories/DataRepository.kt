package com.example.userapplication.repositories

import com.example.userapplication.requests.ImageListApi
import com.example.userapplication.requests.NetworkErrorState
import com.example.userapplication.requests.response.Image
import org.koin.standalone.KoinComponent
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


/***
 *  Repository is mediators between different data sources,
 *  such as persistent models, web services, and caches
 */
class DataRepository(val imageListApi: ImageListApi) : KoinComponent {


    /**
     * This function is used to get the data from cloud.
     *
     * @param onImageList interface to send data back to view modeL
     *
     */

    fun getImagesFromCloud(onImageList: OnImageList) {

        imageListApi.getImages(2, 20).enqueue(object : retrofit2.Callback<MutableList<Image>?> {

            override fun onFailure(call: Call<MutableList<Image>?>, t: Throwable)  {

                when(t) {
                    is IOException -> onImageList.onFailure(NetworkErrorState.NoConnection)
                    else -> {
                        onImageList.onFailure(NetworkErrorState.UnknownError)
                    }
                }
            }

            override fun onResponse(call: Call<MutableList<Image>?>, response: Response<MutableList<Image>?>) {

                if (response.body() is MutableList<Image>?) {
                    onImageList.onSuccess(response.body() as MutableList<Image>)
                }
            }
        })
    }


    /**
     * Interface for returning back results or failure info
     *
     */
    interface OnImageList {
        fun onSuccess(data: MutableList<Image>)
        fun onFailure(error: NetworkErrorState)
    }
}

