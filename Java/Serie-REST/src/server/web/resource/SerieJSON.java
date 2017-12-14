package server.web.resource;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import com.google.gson.Gson;
import commons.IllegalSerieException;
import commons.Serie;
import org.restlet.data.Status;
import org.restlet.resource.*;
import server.backend.wrapper.RegistryAPI;

public class SerieJSON extends ServerResource{

    @Get
    public String getSerie(){
        Gson gson = new Gson();
        RegistryAPI rg = RegistryAPI.instnce();

        try{
            Serie s = rg.get(getAttribute("serie").replaceAll("%20", " "));
            return gson.toJson(s, Serie.class);
        } catch (IllegalSerieException e) {
            Status s = new Status(404);
            setStatus(s);
            return null;
        }
    }

    @Delete
    public String remove(){
        Gson gson = new Gson();
        RegistryAPI rg = RegistryAPI.instnce();

        try{
            rg.remove(getAttribute("serie").replaceAll("%20", " "));
            return getAttribute("serie").replaceAll("%20", " ")+" deleted";
        } catch (IllegalSerieException e) {
            Status s = new Status(404);
            setStatus(s);
            return null;
        }
    }

    @Post
    public String update(String payload){
        Gson gson = new Gson();
        RegistryAPI rg = RegistryAPI.instnce();
        Serie s = gson.fromJson(payload, Serie.class);
        rg.update(s);
        return s.getName()+" updated";
    }

    @Put
    public String add(String payload){
        Gson gson = new Gson();
        RegistryAPI rg = RegistryAPI.instnce();
        try {
            Serie s = gson.fromJson(payload, Serie.class);
            rg.put(s);
            return s.getName()+" added";
        } catch (IllegalSerieException e) {
            Status s = new Status(400);
            setStatus(s);
            return null;
        }
    }



}
