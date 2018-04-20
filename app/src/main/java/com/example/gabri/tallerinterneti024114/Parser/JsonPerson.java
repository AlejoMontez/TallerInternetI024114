package com.example.gabri.tallerinterneti024114.Parser;

import com.example.gabri.tallerinterneti024114.Models.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 17/04/2018.
 */

public class JsonPerson {

    public static List<Person> getDataJson (String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);

        List<Person> personList = new ArrayList<>();

        //recorrer item
        for (int i = 0; i < jsonArray.length();i++){

            //ingresar a cada item, ponerme en la posicion del item
            JSONObject item = jsonArray.getJSONObject(i);

            Person person = new Person();

            person.setCodigo(item.getString("codigo"));
            person.setNombre(item.getString("nombre"));
            person.setEdad(item.getString("edad"));
            person.setCorreo(item.getString("correo"));
            person.setPass(item.getString("pass"));



            personList.add(person);
        }
        return personList;
    }
}
