package com.example.userapplication.requests.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/***
 * A koltin data class used in  deserialize image list data in api response
 */

data class Image (

        @SerializedName("id") val id : Int,
        @SerializedName("author") val author : String,
        @SerializedName("width") val width : Int,
        @SerializedName("height") val height : Int,
        @SerializedName("url") val url : String,
        @SerializedName("download_url") val download_url : String
)