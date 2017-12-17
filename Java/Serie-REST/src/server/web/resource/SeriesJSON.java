package server.web.resource;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 14/12/17
         */

import com.google.gson.Gson;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import server.backend.wrapper.RegistryAPI;

import java.util.Arrays;

public class SeriesJSON extends ServerResource{

    @Get
    public String getKeys(){
        Gson gson = new Gson();
        RegistryAPI rg = RegistryAPI.instnce();
        return gson.toJson(rg.getKeys(), String[].class);
    }
}
