package com.example.gabri.tallerinterneti024114;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabri.tallerinterneti024114.Models.Person;
import com.example.gabri.tallerinterneti024114.Parser.JsonPerson;
import com.example.gabri.tallerinterneti024114.URL.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_data);
        textView = (TextView) findViewById(R.id.id_tv_data);

        if (isOnLine()){
            // Hacer llamado a la tarea
            // MyTask task = new MyTask();

            Taskperson taskperson = new Taskperson();

            taskperson.execute("http://pastoral.iucesmag.edu.co/practica/listar.php");
        }else {
            Toast.makeText(this, "no hay conexion", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo para validar la conexion a internet
    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }


    public void processData(){
        //textView.setText("Numero: "+s);
        //textView.setTextSize(Integer.parseInt(s));

        //textView.append(s + "\n");

        Toast.makeText(this, String.valueOf(personList.size()), Toast.LENGTH_SHORT).show();

           for (Person str : personList){

            textView.append("Codigo: " + str.getCodigo() +", Nombre: "+ str.getNombre() + ", Edad: "+ str.getEdad() +", Correo: "+ str.getCorreo() +", Pass: "+ str.getPass() + "\n");

        }
    }



    public class Taskperson extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings [0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                personList = JsonPerson.getDataJson(s);
            }catch (JSONException e){
                e.printStackTrace();


            }

            processData();

            progressBar.setVisibility(View.GONE);
        }
    }
}
