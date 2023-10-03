package com.example.realm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.realm.databinding.ActivitySplashBinding
import com.example.realm.main.ui.CatalogActivity
import com.example.realm.main.ui.MainActivity
import com.example.realm.main.ui.viewmodel.MainHomeViewModel
import com.example.realm.main.ui.viewmodel.MainHomeViewModelFactory
import io.realm.Realm

class SplashActivity: AppCompatActivity() {

    private lateinit var mViewModel: MainHomeViewModel
    private lateinit var mBinding: ActivitySplashBinding
    companion object{
        fun launch(appCompatActivity: AppCompatActivity) {
            val intent = Intent(appCompatActivity, SplashActivity::class.java)
            appCompatActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(applicationContext)
        val realm: Realm = Realm.getDefaultInstance()

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        mViewModel = ViewModelProvider(this, MainHomeViewModelFactory()).get(MainHomeViewModel::class.java)

        val runnable = Runnable { mBinding.welcomeLabel.visibility = View.VISIBLE }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 5000)

        val runnable2 = Runnable {
            if (mViewModel.getNullableUser() == null){
                MainActivity.launch(appCompatActivity = this)
                finish()
            } else {
                CatalogActivity.launch(appCompatActivity = this)
                finish()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed(runnable2, 500)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        val runnable = Runnable {
            if (mViewModel.getNullableUser() == null){
                MainActivity.launch(appCompatActivity = this)
                finish()
            } else {
                CatalogActivity.launch(this)
                finish()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed(runnable, 500)
    }
}