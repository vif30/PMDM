package com.viizfo.masterdetailexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import com.viizfo.masterdetailexample.model.SuperHero

class MainActivity : AppCompatActivity(),OnItemClick {
    private val layoutList: FrameLayout by lazy { findViewById(R.id.containerList) }
    private val layoutDetail:FrameLayout? by lazy { findViewById(R.id.containerDetail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Load ListFragment on its container. (layoutList)
        loadRecyclerView()

        //In the case we were in landscape we load also the DetailFragment in its container. (layoutDetail)
        if (isLandScape()){
            //We load first SuperHero
            loadDetailFragment(true, SuperHero.getFirstID())
        }
    }
    private fun loadRecyclerView() {
        supportFragmentManager.beginTransaction()
            .replace(layoutList.id, ListFragment.newInstance(1))
            .addToBackStack(null)
            .commit()
    }
    /*Work around that let  us to know if we're un landscape mode*/
    private fun isLandScape():Boolean {
        return layoutDetail != null
    }
    private fun loadDetailFragment(twoPane:Boolean, heroID:Int){
        //If layoutDetail is null (we're in portrait mode) then we load DetailFragment in layoutList
        val id = layoutDetail?.id?:layoutList.id

        supportFragmentManager.beginTransaction()
            .replace(id, DetailFragment.newInstance(heroID))
            .addToBackStack(null)
            .commit()
    }
    //Callback implementation
    override fun onItemClick(hero: SuperHero) {
        //We don't nee to use snackbar, which is such as an enhanced Toast where you can implement actions.
        //But I wanted to introduce it to you
        Snackbar.make(layoutList, "You have clicked on ${hero.SuperHero}", Snackbar.LENGTH_LONG)
            .setAction("Open"){

                loadDetailFragment(isLandScape(), hero.id)

            }
            .show()
    }
}