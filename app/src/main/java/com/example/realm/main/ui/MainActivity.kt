package com.example.realm.main.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.realm.R
import com.example.realm.SplashActivity
import com.example.realm.databinding.ActivityMainBinding
import com.example.realm.main.ui.viewmodel.MainHomeViewModel
import com.example.realm.main.ui.viewmodel.MainHomeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainHomeViewModel
    companion object{
        fun launch(appCompatActivity: AppCompatActivity) {
            val intent = Intent(appCompatActivity, MainActivity::class.java)
            appCompatActivity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this, MainHomeViewModelFactory()).get(MainHomeViewModel::class.java)

        val countries = mutableListOf("", "México", "EUA", "Canada")

        mBinding.country.adapter = getSpinnerAdapter(countries)


        mBinding.registerButton.setOnClickListener {
            if (validateFields()) {
                mViewModel.saveUser(
                    mBinding.name.text.toString(),
                    mBinding.lastName1.text.toString(),
                    mBinding.lastName2.text.toString(),
                    mBinding.country.selectedItem.toString(),
                    (mBinding.datePicker1.dayOfMonth.toString() + "/" + mBinding.datePicker1.month.toString() + "/" + mBinding.datePicker1.year.toString())
                )
                CatalogActivity.launch(this)
                finish()
            } else {
                Toast.makeText(this, "Información incompleta", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validateFields(): Boolean{

        if (mBinding.name.text.isNullOrBlank() || mBinding.lastName2.text.isNullOrBlank()
            || mBinding.lastName1.text.isNullOrBlank() || mBinding.country.selectedItem.toString().isEmpty()) {

            if (mBinding.name.text.isNullOrBlank())
                mBinding.name.error = getString(R.string.required_field)
            if (mBinding.lastName1.text.isNullOrBlank())
                mBinding.lastName1.error = getString(R.string.required_field)
            if (mBinding.lastName2.text.isNullOrBlank())
                mBinding.lastName2.error = getString(R.string.required_field)
            if (mBinding.country.selectedItem.toString().isEmpty())
                (mBinding.country.getChildAt(0) as TextView).error = ""
            return false
        }
        return true
    }

    private fun getSpinnerAdapter(items: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(this, R.layout.simple_spinner_item_montserrat, items).also { adapter ->
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_montserrat)
        }
    }
}

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //////////////////////agregar configuracion inicial
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        val persona = PersonaDAO()
        PersonaDAO.clear()
        llenarDatos()
    }

    private fun llenarDatos() {
        val modeloP: MutableList<ModeloPersona> = ArrayList()
        modeloP.add(ModeloPersona("raul", 0, true))
        modeloP.add(ModeloPersona("luis", 0, true))
        modeloP.add(ModeloPersona("luis2", 0, true))
        modeloP.add(ModeloPersona("luis3", 0, true))
        modeloP.add(ModeloPersona("luis4", 0, true))
        modeloP.add(ModeloPersona("luis5", 0, true))
        modeloP.add(ModeloPersona("luis6", 0, true))
        modeloP.add(ModeloPersona("luis7", 0, true))
        modeloP.add(ModeloPersona("luis8", 0, true))
        modeloP.add(ModeloPersona("luis9", 0, true))
        modeloP.add(ModeloPersona("luis10", 0, true))
        modeloP.add(ModeloPersona("luis11", 0, true))
        modeloP.add(ModeloPersona("luis12", 0, true))
        modeloP.add(ModeloPersona("luis13", 0, true))
        PersonaDAO.addAll(modeloP)
        consultarDatos()
    }

    private fun consultarDatos() {
        val nuevoModeloLista = PersonaDAO.getSelected()
        for (auxModelo in nuevoModeloLista) {
            Log.i("R E A L M ", auxModelo.nombre)
        }
    }
}

 */