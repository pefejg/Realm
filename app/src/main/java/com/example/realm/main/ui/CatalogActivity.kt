package com.example.realm.main.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.realm.R
import com.example.realm.databinding.ActivityCatalogBinding
import com.example.realm.main.background.response.Result
import com.example.realm.main.ui.adapter.PlanetsAdapter
import com.example.realm.main.ui.viewmodel.DetailActivity
import com.example.realm.main.ui.viewmodel.MainHomeViewModel
import com.example.realm.main.ui.viewmodel.MainHomeViewModelFactory

class CatalogActivity: AppCompatActivity(), PlanetsAdapter.Callback {

    private lateinit var mBinding: ActivityCatalogBinding
    private lateinit var mViewModel: MainHomeViewModel
    private lateinit var mPlanetsAdapter: PlanetsAdapter

    companion object {

        fun launch (appCompatActivity: AppCompatActivity){
            val intent = Intent(appCompatActivity, CatalogActivity::class.java)
            appCompatActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_catalog)
        mViewModel = ViewModelProvider(this, MainHomeViewModelFactory()).get(MainHomeViewModel::class.java)

        mViewModel.getPlanets()
        mPlanetsAdapter = PlanetsAdapter(this, this)

        mViewModel.mPlanets.observe(this){
            var planetList=  it.results!!
            mPlanetsAdapter.updateList(planetList)
        }

        mViewModel.mErrorResponse.observe(this){
            Toast.makeText(this, "Error al consultar planeta", Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemSelected(planets: Result, position: Int) {
        DetailActivity.launch(this, (position+1).toString())
        finish()
    }

}