package server.backend;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import commons.IllegalSerieException;
import commons.Serie;

import java.io.*;
import java.util.TreeMap;

public class Registry {

    public Registry() {
        dict = new TreeMap<>();
    }

    public Serie get(String name) throws IllegalSerieException {
        if(dict.containsKey(name)) return dict.get(name);
        throw new IllegalSerieException(name + " not exists");
    }

    public void put(Serie s) throws IllegalSerieException {
        if(!dict.containsKey(s.getName()))
            dict.put(s.getName(), s);
        else
            throw new IllegalSerieException(s.getName() + " already exists");
    }

    public void remove(String name) throws IllegalSerieException {
        if(dict.containsKey(name))
            dict.remove(name);
        else
            throw new IllegalSerieException(name + " not exists");
    }

    public void update(Serie s){
        dict.put(s.getName(), s);
    }

    public int size(){
        return dict.size();
    }

    public String[] getKeys(){
        return dict.keySet().toArray(new String[dict.size()]);
    }

    public void save(String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(dict);
        objOut.close();
        out.close();
    }

    public void load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(in);
        dict = (TreeMap<String, Serie>) objIn.readObject();
        objIn.close();
        in.close();
    }

    private TreeMap<String, Serie> dict;
}
