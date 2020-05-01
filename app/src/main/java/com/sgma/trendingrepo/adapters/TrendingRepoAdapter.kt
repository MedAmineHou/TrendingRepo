package com.sgma.trendingrepo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sgma.trendingrepo.R
import com.sgma.trendingrepo.api.models.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*

class TrendingRepoAdapter(private var items: ArrayList<Item>,private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TrendingRepoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        (holder as TrendingRepoViewHolder).mRepoName.text = item.full_name
        holder.mRepoDesc.text = item.description
        Picasso.get().load(item.owner.avatar_url).into(holder.mOwnerAvatar)
        holder.mOwnerName.text = item.owner.login
        holder.mStarsNumber.text = item.stargazers_count.toString()


    }
}

class TrendingRepoViewHolder(view: View): RecyclerView.ViewHolder(view){
    val mRepoName:AppCompatTextView = view.repoName
    val mRepoDesc:AppCompatTextView = view.repoDesc
    val mOwnerAvatar:ImageView = view.ownerAvatar
    val mOwnerName:AppCompatTextView = view.ownerName
    val mStarsNumber:AppCompatTextView = view.starsNumber

}