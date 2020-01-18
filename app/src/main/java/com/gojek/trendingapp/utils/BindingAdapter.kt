package com.gojek.trendingapp.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gojek.trendingapp.R


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        // add divider
        view.addItemDecoration(
            DividerItemDecoration(
                parentActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        view.adapter = adapter
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}



@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}



@BindingAdapter("app:srcRoundedImage")
fun setRoundedImageUrl(imageView: ImageView, url: String?) {
    val parentActivity: AppCompatActivity? = imageView.getParentActivity()
    Log.d("asd", "------ :: "+url)
    if(parentActivity != null && !url.isNullOrBlank()) {
        Glide.with(parentActivity)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)


    }

}