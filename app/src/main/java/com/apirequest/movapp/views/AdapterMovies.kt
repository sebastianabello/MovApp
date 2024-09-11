package com.apirequest.movapp.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.apirequest.movapp.R
import com.apirequest.movapp.core.Constans
import com.apirequest.movapp.models.MovieModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AdapterMovies(
    val context: Context,
    var moviesList: List<MovieModel>
): RecyclerView.Adapter<AdapterMovies.ViewHolder>()
{
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val cvMovie = itemView.findViewById(R.id.cvMovie) as CardView
        val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
        val tvInfo = itemView.findViewById(R.id.tvInfo) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rvmovie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide.with(context)
            .load("${Constans.BASE_URL_IMAGE}${movie.poster}")
            .apply(RequestOptions().override(Constans.IMAGE_WIDTH, Constans.IMAGE_HEIGHT))
            .into(holder.ivImage)

        holder.tvInfo.text = HtmlCompat.fromHtml(
            "<b>Title: </b>" + movie.title  +
                    "<br><b>Popularity: </b>" + movie.popularity +
                    "<br><b>Rating:</b>" + movie.rating,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        holder.cvMovie.setOnClickListener{
            showOverview(movie.title, movie.overview)
        }
    }

    private fun showOverview(title: String, overview: String){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
        dialog.setMessage(overview)
        dialog.show()
    }

}