package com.example.userapplication.callback

import com.example.userapplication.requests.response.Image

interface RecyclerViewCallback {

    fun onRecycleViewItemClick(image: Image, position: Int)
}