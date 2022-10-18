package com.universe.tvmaze.ui.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.universe.tvmaze.R
import com.universe.tvmaze.databinding.ActivityDetailBinding
import com.universe.tvmaze.ui.view.adapter.detail.DetailCastAdapter
import com.universe.tvmaze.ui.viewmodel.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private var showMore: Boolean= false
    private var urlShow:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        val idMovie = intent.getLongExtra("idMovie",0L)
        binding.rvCast.layoutManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)

        binding.tvShowMore.setOnClickListener(this)
        binding.btnEnlace.setOnClickListener(this)
        detailViewModel.onCreate(idMovie)
        detailViewModel.getCast(idMovie)

        val genders= StringBuilder("")

        detailViewModel.movieCast.observe(this){ cast ->
            if(cast != null){
                binding.rvCast.adapter = DetailCastAdapter(cast)
                binding.rvCast.adapter?.notifyDataSetChanged()
                binding.rvCast.scheduleLayoutAnimation()
            }

        }

        detailViewModel.movieDetail.observe(this) { detail ->
            Picasso.with(this).load(detail?.image?.original)
                .into(binding.ivMovie, object: Callback{
                    override fun onSuccess() {
                        binding.progressImage.visibility = View.GONE
                    }

                    override fun onError() {

                        binding.progressImage.visibility = View.GONE
                    }
                })
            binding.tvTitle.text = detail?.name

            if(detail?.network?.name.toString()=="null"){
                binding.tvNetName.visibility = View.GONE
            }else{
                binding.tvNetName.visibility = View.VISIBLE
                binding.tvNetName.text = detail?.network?.name.toString()
            }

            if(detail?.rating?.average.toString() == "null"){
                binding.tvRating.text = getString(R.string.not_available)
            }else{
                binding.tvRating.visibility = View.VISIBLE
                binding.tvRating.text = detail?.rating?.average.toString()
            }

            urlShow = detail?.officialSite.toString()


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                binding.tvSynopsis.text = Html.fromHtml(detail?.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }else{
                binding.tvSynopsis.text = Html.fromHtml(detail?.summary)
            }
            if(binding.tvSynopsis.lineCount>5){
                binding.tvShowMore.visibility = View.VISIBLE
                showMore = true

            }

            detail?.genres?.forEach {
                genders.append("$it,")
            }
            binding.tvGenders.text = genders.dropLast(1)
            if(detail?.schedule?.time.isNullOrEmpty() && detail?.schedule?.days.isNullOrEmpty()){
                binding.tvSchedule.text = getString(R.string.schedule_not_available)
            }else{
                binding.tvSchedule.text = Html.fromHtml("<b>Schedule: </b> ${detail?.schedule?.time} |  ${detail?.schedule?.days}")
            }

        }


    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.tvShowMore.id ->{
                if(showMore){
                    showMore = false
                    binding.tvSynopsis.maxLines=Integer.MAX_VALUE
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        binding.tvShowMore.text = Html.fromHtml(getString(R.string.show_less), HtmlCompat.FROM_HTML_MODE_LEGACY)
                    }else{
                        binding.tvShowMore.text = Html.fromHtml(getString(R.string.show_less))
                    }

                }else{
                    showMore = true
                    binding.tvSynopsis.maxLines = 5
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        binding.tvShowMore.text = Html.fromHtml(getString(R.string.show_moree), HtmlCompat.FROM_HTML_MODE_LEGACY)
                    }else{
                        binding.tvShowMore.text = Html.fromHtml(getString(R.string.show_moree))
                    }
                }

            }
            binding.btnEnlace.id ->{
                if(!urlShow.isNullOrEmpty()){
                    val intent= Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(urlShow)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, getString(R.string.url_not_exist), Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}