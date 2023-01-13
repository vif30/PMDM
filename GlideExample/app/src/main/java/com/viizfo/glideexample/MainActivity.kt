package com.viizfo.glideexample

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.squareup.picasso.Picasso
import com.viizfo.glideexample.databinding.ActivityMainBinding
import java.security.AccessController.getContext
import kotlin.random.Random

const val ERROR_IMAGE = "https://bigseoagency.com/wp-content/uploads/2018/03/error-404-foxplay.png"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //private lateinit var glideDrawable : GlideDrawableImageViewTarget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.btnChangeImage.setOnClickListener {
            changeImage ()
        }
    }


    private fun changeImage() {
        val url = "https://picsum.photos/200?rand=" + Random.nextInt()
        Picasso.get().load(url).into (binding.ivImage)
        //With this work around we can show a gif before loading
        /*Glide.with(this).load(url)
            .thumbnail(Glide.with(this).load(R.raw.loading))
            .fitCenter()
            .dontAnimate()
            .into(binding.ivImage);*/

        /*Glide.with(this)
            .load(url) //Image that we want to show
            .placeholder(R.raw.loading) //Image that will be displayed while loading the image to be displayed
            .error(ERROR_IMAGE) //Image that we will show if something goes wrong. it is typically use a drawable image stored
            .into(binding.ivImage) //ImageView that will contain the image*/
    }
}
