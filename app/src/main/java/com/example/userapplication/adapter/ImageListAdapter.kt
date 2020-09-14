package com.example.userapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.userapplication.R
import com.example.userapplication.callback.RecyclerViewCallback
import com.example.userapplication.requests.response.Image
import de.hdodenhof.circleimageview.CircleImageView

/*RecyclerView Adapter  for showing items*/

class ImageListAdapter(private val imageList: List<Image>) :
        RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    private var recyclerViewCallback: RecyclerViewCallback? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_images, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val authorName = imageList[position].author?:""
        viewHolder.tv_name?.text = viewHolder.itemView.context.getString(R.string.authorLabel)+authorName

        val width = imageList[position].width
        viewHolder.tv_width?.text = viewHolder.itemView.context.getString(R.string.widthLabel)+width.toString()

        val height = imageList[position].height
        viewHolder.tv_height?.text = viewHolder.itemView.context.getString(R.string.heightLabel)+height.toString()

        val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)


        Glide.with(viewHolder.itemView.context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageList[position].download_url?:"")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(400,400)
                .into(viewHolder.iv_avatar)

        viewHolder.itemView.setOnClickListener {
            this@ImageListAdapter.recyclerViewCallback?.onRecycleViewItemClick(imageList[position], position)

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
        val tv_width = itemView.findViewById<TextView>(R.id.tv_width)
        val tv_height = itemView.findViewById<TextView>(R.id.tv_height)
        val iv_avatar = itemView.findViewById<CircleImageView>(R.id.iv_avatar)
    }




    fun setOnCallbackListener(recyclerViewCallback: RecyclerViewCallback) {
        this.recyclerViewCallback = recyclerViewCallback
    }

}