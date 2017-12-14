package server.backend.wrapper;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import commons.IllegalSerieException;
import commons.Serie;
import server.backend.Registry;

import java.io.File;
import java.io.IOException;

public class RegistryAPI {

    public static synchronized RegistryAPI instnce(){
        if(instance==null)
            instance = new RegistryAPI();
        return instance;
    }

    private RegistryAPI(){
        rg = new Registry();
    }

    public synchronized Serie get(String name) throws IllegalSerieException {
        return rg.get(name);
    }

    public synchronized void put(Serie s) throws IllegalSerieException {
        rg.put(s);
        commit();
    }

    public synchronized void update(Serie s){
        rg.update(s);
        commit();
    }

    public synchronized void remove(String name) throws IllegalSerieException {
        rg.remove(name);
        commit();
    }

    public synchronized String[] getKeys(){
        return rg.getKeys();
    }

    public synchronized int size(){
        return rg.size();
    }

    public void setStorageFiles(String rootDir, String baseFile){
        this.rootDir = rootDir;
        this.baseFile = baseFile;
    }

    protected int buildStorageFileExtension(){
        final File folder = new File(rootDir);
        int c;
        int max=-1;

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().substring(0, baseFile.length()).equalsIgnoreCase(baseFile)){
                try {
                    c = Integer.parseInt(fileEntry.getName().substring(baseFile.length()+1));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e){
                    c=-1;
                }
                if (c>max)
                    max=c;
            }
        }
        return max;
    }

    public void commit(){
        int extension=buildStorageFileExtension();
        String fileName = rootDir+"/"+baseFile+"."+(extension+1);
        System.err.println("Commit storage to: "+fileName);
        try {
            rg.save(fileName);
        } catch (IOException e) {
            System.err.println("Commit filed");
        }
    }

    public void restore(){
        int extension=buildStorageFileExtension();
        if (extension==-1){
            System.err.println("No data to load - starting a new registry");
        } else {
            String fileName = rootDir+"/"+baseFile+"."+extension;
            System.err.println("Restore storage from: " + fileName);
            try {
                rg.load(fileName);
            } catch (ClassNotFoundException | IOException e) {
                System.err.println("Restore filed - starting a new registry");
                rg=new Registry();
            }
        }
    }


    private static RegistryAPI instance;
    private Registry rg;
    private String rootDir;
    private String baseFile;
}
