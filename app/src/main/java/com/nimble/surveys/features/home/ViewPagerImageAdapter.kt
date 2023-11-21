package com.nimble.surveys.features.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nimble.surveys.R
import com.nimble.surveys.databinding.ViewImageHomeBinding
import com.nimble.surveys.domain.model.SurveysItems


class ViewPagerImageAdapter(private val surveySelectionListener:SelectSurvey) : RecyclerView.Adapter<ImageViewHolder>() {

    interface SelectSurvey{
        fun surveySelected(pos:Int)

    }

    private var items = mutableListOf<SurveysItems>()
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        context = parent.context

        val mView: ViewImageHomeBinding = ViewImageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(mView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.mView.btnSurveyDetail.setOnClickListener {
            surveySelectionListener.surveySelected(position)
        }
        holder.bind(context, items[position])
    }

    fun update(data: List<SurveysItems>) {
        this.items.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

}

class ImageViewHolder(itemView: ViewImageHomeBinding) : RecyclerView.ViewHolder(itemView.root) {
    var mView: ViewImageHomeBinding = itemView

    fun bind(context: Context, items: SurveysItems) {
        with(mView) {
            Glide.with(context).load(items.coverImageUrl).apply(
                RequestOptions()
                    .placeholder(R.drawable.splash_background)
                    .error(R.drawable.splash_background)
            ).into(bgImg)
            txtTitle.text=items.title
            txtDescription.text=items.description
        }
    }
}
