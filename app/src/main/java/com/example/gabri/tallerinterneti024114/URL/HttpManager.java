package com.example.gabri.tallerinterneti024114.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gabri on 17/04/2018.
 */

public class HttpManager {
    //metodo para las peticiones
    public  static String getData(String url) throws IOException {
        //Clase para manejar archivos
        BufferedReader bufferedReader;

        //Clase para manejar las urls de internet
        URL urlData = new URL(url);

        // Clase para abrir la conexion a internet
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlData.openConnection();

        // Clase para manejar los tipos de archivos
        StringBuilder stringBuilder = new StringBuilder();

        // Leer datos de internet
        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            stringBuilder.append(line + "\n");
            // pasa los datos a cadena
        }
        return stringBuilder.toString();
    }

}
