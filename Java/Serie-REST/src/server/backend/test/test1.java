package server.backend.test;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import commons.IllegalSerieException;
import commons.Serie;
import server.backend.Registry;

import java.io.IOException;
import java.util.Arrays;

public class test1 {
    public static void main(String[] args){
        Registry rg = new Registry();

        System.out.println("size: " + rg.size());

        try {
            System.out.println("put Narcos");
            rg.put(new Serie("Narcos", "Netflix", 3, "live", 0));
        } catch (IllegalSerieException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("put Mr. Robot");
            rg.put(new Serie("Mr. Robot", "USA", 3, "live", 0));
        } catch (IllegalSerieException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("size: " + rg.size());

        try {
            System.out.println("get Mr. Robot");
            System.out.println(rg.get("Mr. Robot").toString());
        } catch (IllegalSerieException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("size: " + rg.size());

        System.out.println("list: " + Arrays.toString(rg.getKeys()));

        try {
            System.out.println("put Narcos");
            rg.put(new Serie("Narcos", "Netflix", 3, "live", 0));
        } catch (IllegalSerieException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("update Narcos");
        rg.update(new Serie("Narcos", "Netflix", 3, "live", 0));

        try {
            rg.save("save");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("remove Narcos");
            rg.remove("Narcos");
        } catch (IllegalSerieException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("remove Narcos");
            rg.remove("Narcos");
        } catch (IllegalSerieException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("size: " + rg.size());

        System.out.println("list: " + Arrays.toString(rg.getKeys()));
    }
}
