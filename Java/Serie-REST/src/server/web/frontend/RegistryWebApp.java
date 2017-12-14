package server.web.frontend;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import com.google.gson.Gson;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import server.backend.wrapper.RegistryAPI;
import server.web.resource.SeriesJSON;
import server.web.resource.series.*;
import server.web.resource.SerieJSON;
import server.web.resource.SizeJSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class RegistryWebApp extends Application{

    public static void main(String[] args){
        Gson gson = new Gson();
        Settings settings = null;
        try {
            Scanner sc = new Scanner(new File("settings.json"));
            settings = gson.fromJson(sc.nextLine(), Settings.class);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        RegistryAPI rg = RegistryAPI.instnce();
        rg.setStorageFiles(System.getProperty("user.dir")+"/"+settings.storage_base_dir, settings.storage_base_file);
        rg.restore();

        System.err.println(Arrays.toString(rg.getKeys()));

        try {
            // Create a new Component.
            Component component = new Component();
            // Add a new HTTP server listening on port defined in the settings file.
            component.getServers().add(Protocol.HTTP, settings.port);
            // Add an handler for static files
            component.getClients().add(Protocol.FILE);

            // Attach the CarRegistryWebApplication application.
            component.getDefaultHost().attach(new RegistryWebApp());

            // Start the component.
            component.start();
        } catch (Exception e) {	  // Something is wrong.
            e.printStackTrace();
        }
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router();

        router.attach("/series", SeriesJSON.class);
        router.attach("/series/size", SizeJSON.class);
        router.attach("/series/{serie}", SerieJSON.class);
        router.attach("/series/{serie}/season", SeasonJSON.class);
        router.attach("/series/{serie}/status", StatusJSON.class);
        router.attach("/series/{serie}/name", NameJSON.class);
        router.attach("/series/{serie}/network", NetworkJSON.class);
        router.attach("/series/{serie}/score", ScoreJSON.class);

        return router;
    }

    private class Settings {
        public int port;
        public String web_base_dir;
        public String storage_base_dir;
        public String storage_base_file;
    }
}
