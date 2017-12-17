package it.unisannio.cp.orange.serie_rest.util.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.restlet.resource.ClientResource;

import java.io.IOException;

import it.unisannio.cp.orange.serie_rest.commons.Serie;
import it.unisannio.cp.orange.serie_rest.util.List;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/12/17
 *  Last Modified: $file.lastModified
 */


public class GetSerie extends AsyncTask<String, Void, Serie> {

    @Override
    protected Serie doInBackground(String... strings) {
        Gson gson = new Gson();
        ClientResource cr = new ClientResource(strings[0]);
        try {
            String json = cr.get().getText();
            if(cr.getStatus().getCode()==200)
                return gson.fromJson(json, Serie.class);
        } catch (IOException e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(Serie serie) {
        Log.d("SERIE", serie.getNetwork());
        List.instance().add(serie);
    }
}
