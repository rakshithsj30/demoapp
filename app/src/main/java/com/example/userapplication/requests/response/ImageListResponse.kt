package com.example.userapplication.requests.response

import java.io.Serializable

/***
 * Data class used in  deserialize  api response.
 */
data class ImageListResponse (var items:MutableList<Image>):Serializable {



}