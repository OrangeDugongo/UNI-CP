package server.web.resource.series;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 14/12/17
         */

import com.google.gson.Gson;
import commons.IllegalSerieException;
import commons.Serie;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import server.backend.wrapper.RegistryAPI;

public class NameJSON extends ServerResource{

    @Get
    public String getName(){
        Gson gson = new Gson();
        RegistryAPI rg = RegistryAPI.instnce();

        try{
            Serie s = rg.get(getAttribute("serie").replaceAll("%20", " "));
            return gson.toJson(s.getName(), String.class);
        } catch (IllegalSerieException e) {
            Status s = new Status(404);
            setStatus(s);
            return getAttribute("serie") + " not exist";
        }
    }
}
