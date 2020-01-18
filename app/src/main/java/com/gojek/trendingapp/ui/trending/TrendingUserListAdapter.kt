package com.datanapps.myexercise.views.motivation.text

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gojek.trendingapp.R
import com.gojek.trendingapp.databinding.LayoutTrendingUserBinding
import com.gojek.trendingapp.models.TrendingUser


class TrendingUserListAdapter: RecyclerView.Adapter<TrendingUserListAdapter.ViewHolder>() {
    private lateinit var trendingUserList:List<TrendingUser>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutTrendingUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_trending_user, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trendingUserList[position])
    }

    override fun getItemCount(): Int {
        return if(::trendingUserList.isInitialized) trendingUserList.size else 0
    }

    fun updatePostList(postList:List<TrendingUser>){
        this.trendingUserList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutTrendingUserBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = TrendingUserViewModel()

        fun bind(trendingUserList:TrendingUser){
            viewModel.bind(trendingUserList)
            binding.trendingUser = viewModel
        }
    }
}