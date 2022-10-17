package com.universe.tvmaze.ui.view.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.universe.tvmaze.R
import com.universe.tvmaze.domain.model.ScheduleItemDomain


class TvMazeAdapter(
    private var movies: MutableList<ScheduleItemDomain>,
    private val listener: ITvMazeOnClick,
    private var isFromDate: Boolean
): RecyclerView.Adapter<TvMazeAdapter.ViewHolderTvMaze>() {
    lateinit var context: Context

    class ViewHolderTvMaze(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvShowName : TextView = itemView.findViewById(R.id.tvTitle)
        val tvSubtitle : TextView = itemView.findViewById(R.id.tvSubtitle)
        val tvRating : TextView = itemView.findViewById(R.id.tvRating)
        val tvSchedule : TextView = itemView.findViewById(R.id.tvSchedule)
        val ivMovie : ImageView = itemView.findViewById(R.id.ivMovie)
        val linearStar: LinearLayout = itemView.findViewById(R.id.linearStar)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTvMaze {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent,false)
        context = parent.context
        return ViewHolderTvMaze(view)
    }

    override fun onBindViewHolder(holder: ViewHolderTvMaze, position: Int) {
        holder.tvShowName.text = movies[position].show?.name.toString()
        if(movies[position].show?.network?.name.toString()=="null"){
            holder.tvSubtitle.visibility = View.GONE
        }else{
            holder.tvSubtitle.visibility = View.VISIBLE
            holder.tvSubtitle.text = movies[position].show?.network?.name.toString()
        }

        if(isFromDate){
            holder.tvSchedule.text = movies[position].airdate + " | "+movies[position].airtime
            holder.linearStar.visibility = View.GONE
        }else{
            holder.tvSchedule.text = movies[position].show?.schedule?.time + " | " +movies[position].show?.schedule?.days
            holder.linearStar.visibility = View.VISIBLE
            if(movies[position].show?.rating?.average.toString() =="null"){
                holder.tvRating.text = "Not available"
            }else{
                holder.tvRating.text = movies[position].show?.rating?.average.toString()
            }

        }
        Picasso.with(context).load(movies[position].show?.image?.medium).into(holder.ivMovie)

        holder.itemView.setOnClickListener {
            listener.onClickMovie(movies[position].show?.id.toString())
        }

    }

    override fun getItemCount(): Int = movies.size
}