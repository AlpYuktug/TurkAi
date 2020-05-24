package com.alpyuktug.turkai.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpyuktug.turkai.R
import com.alpyuktug.turkai.model.PostList
import com.alpyuktug.turkai.util.downloadFromUrl
import com.alpyuktug.turkai.util.placeholderProgressBar
import kotlinx.android.synthetic.main.item_flow.view.*


class PostListAdapter(val postlist: ArrayList<PostList>):RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    class PostListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_flow,parent,false)
        return PostListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {

        holder.view.textViewEMail.text = postlist[position].PostOwnerEMail
        holder.view.textViewDetail.text = postlist[position].PostDetail
        holder.view.materialRatingBar.rating = postlist[position].PostRate!!

        holder.view.ImageViewProfilePicture.downloadFromUrl(postlist[position].PostOwnerPicture,
            placeholderProgressBar(holder.view.context))

    }

    fun UpdatePostList(newPostListList : List<PostList>)
    {
        postlist.clear()
        postlist.addAll(newPostListList)
        notifyDataSetChanged()
    }
}