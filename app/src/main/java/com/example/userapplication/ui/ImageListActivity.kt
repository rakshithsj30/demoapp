package com.example.userapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.userapplication.R
import com.example.userapplication.adapter.ImageListAdapter
import com.example.userapplication.callback.RecyclerViewCallback
import com.example.userapplication.requests.response.Image
import com.example.userapplication.viewmodels.ImageListViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import android.widget.Toast
import com.example.userapplication.requests.NetworkErrorState

class ImageListActivity : AppCompatActivity() ,RecyclerViewCallback {


    // Inject ImageListViewModel using Koin DI.

    private val imageListViewModel: ImageListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //This will for default android divider
        recyclerView.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(applicationContext,androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setRefreshing(true)

        imageListViewModel.getImages()

        imageListViewModel.imageList.observe(this, Observer(function = fun(imageList: List<Image>?) {
            imageList?.let {
                swipeRefreshLayout.isRefreshing = false
                var imageListAdapter: ImageListAdapter = ImageListAdapter(imageList)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = imageListAdapter
                imageListAdapter.setOnCallbackListener(this)
                imageListAdapter.notifyDataSetChanged();
            }
        }))


        imageListViewModel.errorState.observe(this, Observer(function = fun(errorState: NetworkErrorState) {
            errorState?.let {

                swipeRefreshLayout.isRefreshing = false

                when (errorState) {
                    NetworkErrorState.NoConnection  -> Toast.makeText(applicationContext,resources.getString(R.string.noConnection), Toast.LENGTH_LONG).show()
                    NetworkErrorState.UnknownError -> Toast.makeText(applicationContext,resources.getString(R.string.unKnownError), Toast.LENGTH_LONG).show()
                }
            }
        }))


        swipeRefreshLayout.setOnRefreshListener{ imageListViewModel.getImages()}

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onRecycleViewItemClick(image: Image, position: Int) {

        val message = "Author Name: ${image.author} \n Image Width: ${image.width} \n Image Height: ${image.height}"
        showAlertDialog(message)
    }


    private fun showAlertDialog(message: String) {

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //performing negative action
        builder.setPositiveButton("Ok"){dialogInterface, which ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
	
	
}
oooo