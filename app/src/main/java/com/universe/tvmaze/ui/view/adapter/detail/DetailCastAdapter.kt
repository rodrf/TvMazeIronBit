package com.universe.tvmaze.ui.view.adapter.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.universe.tvmaze.R
import com.universe.tvmaze.domain.model.DetailCastItemDomain

class DetailCastAdapter(
    private var cast: MutableList<DetailCastItemDomain>
) : RecyclerView.Adapter<DetailCastAdapter.ViewHolderCast>(){

    private lateinit var context: Context

    class ViewHolderCast(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivPerson: ImageView = itemView.findViewById(R.id.ivPerson)
        val tvPerson : TextView = itemView.findViewById(R.id.tvPersonName)
        val progressCast : ProgressBar = itemView.findViewById(R.id.progressCast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCast {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actors, parent,false)
        context = parent.context
        return ViewHolderCast(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCast, position: Int) {
        Picasso.with(context).load(cast[position].person?.image?.medium)
            .placeholder(R.drawable.ic_error_image)
            .into(holder.ivPerson, object :Callback{
            override fun onSuccess() {
                holder.progressCast.visibility = View.GONE
            }

            override fun onError() {
                holder.progressCast.visibility = View.GONE
            }
        })
        holder.tvPerson.text = cast[position].person?.name
    }

    override fun getItemCount(): Int = cast.size
}