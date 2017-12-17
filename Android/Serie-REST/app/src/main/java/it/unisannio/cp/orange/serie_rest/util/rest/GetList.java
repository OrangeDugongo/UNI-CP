package it.unisannio.cp.orange.serie_rest.util.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.Arrays;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/12/17
 *  Last Modified: $file.lastModified
 */


public class GetList extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... strings) {
        Gson gson = new Gson();
        ClientResource cr = new ClientResource(strings[0]);
        try {
            String json = cr.get().getText();
            if(cr.getStatus().getCode()==200)
                return gson.fromJson(json, String[].class);
        } catch (IOException e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        Log.d("REST", Arrays.toString(strings));
        for (String s: strings)
            new GetSerie().execute("http://192.168.1.241:8182/series/"+s);
    }
}
