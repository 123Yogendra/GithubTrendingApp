package com.gojek.trendingapp.dagger

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

       /* if (modelClass.isAssignableFrom(MotivationalImagesFragmentViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
            return MotivationalImagesFragmentViewModel(
                db.postDao()
            ) as T
        }*/

               throw IllegalArgumentException("Unknown ViewModel class")

    }
}