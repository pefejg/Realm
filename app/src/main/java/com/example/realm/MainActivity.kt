package com.example.realm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.realm.DAO.ModeloPersona
import com.example.realm.DAO.PersonaDAO
import io.realm.Realm
import io.realm.RealmConfiguration

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