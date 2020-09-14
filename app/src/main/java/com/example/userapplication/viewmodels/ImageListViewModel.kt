package com.example.userapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userapplication.repositories.DataRepository
import com.example.userapplication.requests.NetworkErrorState
import com.example.userapplication.requests.response.Image
import org.koin.standalone.KoinComponent

class ImageListViewModel (val dataRepository: DataRepository):ViewModel(),KoinComponent {


    // Live data
    var imageList = MutableLiveData<MutableList<Image>>()
    var errorState = MutableLiveData<NetworkErrorState>()

    init {
        imageList.value = mutableListOf()
    }

    /**
     * This function is used to get images  from cloud
     */

    fun getImages() {
            dataRepository.getImagesFromCloud(object : DataRepository.OnImageList {

                override fun onSuccess(data: MutableList<Image>) {

                    imageList.value =data
                }

                override fun onFailure(error: NetworkErrorState) {
                    //REQUEST FAILED
                    errorState.value = error
                }
            });
    }
}