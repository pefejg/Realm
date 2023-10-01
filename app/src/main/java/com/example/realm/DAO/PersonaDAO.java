package com.example.realm.DAO;

import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class PersonaDAO {

    private static final String FIELD_NAME_SELECTED = "seleccionado";

    public static List<ModeloPersona> addAll(List<ModeloPersona> modeloPersona) {
        Realm realm;
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        List<ModeloPersona> models;
        models = realm.copyToRealm(modeloPersona);

        realm.commitTransaction();
        realm.close();

        return models;
    }

    public static List<ModeloPersona> getSelected() {
        Realm realm;
        realm = Realm.getDefaultInstance();
        //realm.beginTransaction();

        List<ModeloPersona> result;
        result = realm.where(ModeloPersona.class).equalTo(FIELD_NAME_SELECTED, true).findAll();
        // result = (List<ModeloPersona>) realm.where(ModeloPersona.class).equalTo(FIELD_NAME_SELECTED, true).findFirst();

        List<ModeloPersona> modelo;

        if (result != null) {
            modelo = result;
        } else {
            modelo = null;
        }

        return modelo;
    }

    public List<ModeloPersona> getAll() {
        Realm realm;
        realm = Realm.getDefaultInstance();

        RealmResults<ModeloPersona> results;
        results = realm.where(ModeloPersona.class).findAll();

        List<ModeloPersona> entities = new ArrayList<>();


        if (!results.isEmpty()) {
            entities = results;
        }

        realm.close();

        return entities;
    }

    public List<ModeloPersona> update(List<ModeloPersona> clientEntity) {
        Realm realm;
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        List<ModeloPersona> model = realm.copyToRealmOrUpdate(clientEntity);

        realm.commitTransaction();
        realm.close();

        return model;
    }

    public static void clear() {
        Realm realm;
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<ModeloPersona> results;
        results = realm.where(ModeloPersona.class).findAll();

        if (!results.isEmpty()) {
            results.deleteAllFromRealm();
        }

        realm.commitTransaction();
        realm.close();
    }

}