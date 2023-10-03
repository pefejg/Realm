package com.example.realm.main.ui.viewmodel

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.realm.R
import com.example.realm.base.Keys.Keys
import com.example.realm.databinding.ActivityDetailBinding
import com.example.realm.main.ui.CatalogActivity

class DetailActivity: AppCompatActivity() {

   private lateinit var mBinding: ActivityDetailBinding
   private lateinit var mViewModel: MainHomeViewModel
   private lateinit var mID: String

    companion object {
        fun launch(appCompatActivity: AppCompatActivity, id: String){
            val intent = Intent(appCompatActivity, DetailActivity::class.java)
            intent.putExtra(Keys.EXTRA_ID, id)
            appCompatActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        mViewModel = ViewModelProvider(this, MainHomeViewModelFactory()).get(MainHomeViewModel::class.java)

        mID = intent.getStringExtra(Keys.EXTRA_ID).toString()

        mViewModel.getPlanetsById(mID)

        mViewModel.mPlanet.observe(this){
            mBinding.planetName.text = it.name
            mBinding.created.text = it.created
            mBinding.climate.text = it.climate
            mBinding.diameter.text = it.diameter
            mBinding.edited.text = it.edited
            mBinding.gravity.text = it.gravity
            mBinding.population.text = it.population
            mBinding.orbital.text = it.orbitalPeriod
            mBinding.rotation.text = it.rotationPeriod
            mBinding.surface.text = it.surfaceWater
            mBinding.terrain.text = it.terrain
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        CatalogActivity.launch(this)
        finish()
    }
}