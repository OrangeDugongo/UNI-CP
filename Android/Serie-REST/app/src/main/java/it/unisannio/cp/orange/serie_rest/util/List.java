package it.unisannio.cp.orange.serie_rest.util;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/12/17
 *  Last Modified: $file.lastModified
 */


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import it.unisannio.cp.orange.serie_rest.commons.Serie;

public class List {

    private List() {
        list = new ArrayList<>();
    }

    public static List instance(){
        if(instance == null)
            instance = new List();
        return instance;
    }

    public ArrayList<Serie> getList(){
        return list;
    }

    public void add(Serie s){
        list.add(s);
        SharedPreferences.Editor editor = spList.edit();
        editor.putString(s.getName(), gson.toJson(s, Serie.class));
        editor.apply();
        sort();
    }

    public void remove(String name){
       int pos = pos(name);
       if(pos>=0){
           list.remove(pos);
           adapter.notifyDataSetChanged();
           SharedPreferences.Editor editor = spList.edit();
           editor.remove(name);
           editor.apply();
       }
    }

    public void update(Serie s){
        int pos = pos(s.getName());
        if (pos>=0){
            list.remove(pos);
            list.add(s);
            SharedPreferences.Editor editor = spList.edit();
            editor.putString(s.getName(), gson.toJson(s, Serie.class));
            editor.apply();
            sort();
        }
    }

    public void toZero(String name){
        int pos = pos(name);
        if(pos>=0){
            Serie s = list.get(pos);
            s.setScore(0);
            list.remove(pos);
            list.add(s);
            SharedPreferences.Editor editor = spList.edit();
            editor.putString(s.getName(), gson.toJson(s, Serie.class));
            editor.apply();
            sort();
        }
    }

    public void init(Context context){
        adapter = new SerieAdapter(list, context);
        spList = context.getSharedPreferences("list", Context.MODE_PRIVATE);
    }

    public void load(){
        for (String s: spList.getAll().keySet()){
            list.add(gson.fromJson(spList.getString(s, "null"), Serie.class));
        }
        sort();
    }

    public SerieAdapter getAdapter(){
        return adapter;
    }

    private void sort(){
        Collections.sort(list);
        adapter.notifyDataSetChanged();
    }

    private int pos(String name){
        for (int i=0;i < list.size(); i++)
            if (list.get(i).getName().equals(name))
                return i;
        return -1;
    }

    private ArrayList<Serie> list;
    private SerieAdapter adapter;
    private SharedPreferences spList;
    private Gson gson = new Gson();
    private static List instance = null;
}
