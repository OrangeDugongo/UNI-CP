package server.backend.wrapper.test;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import commons.IllegalSerieException;
import server.backend.wrapper.RegistryAPI;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args){
        RegistryAPI rgAPI = RegistryAPI.instnce();
        rgAPI.setStorageFiles("storage", "save");
        rgAPI.restore();

        System.out.println("size: " + rgAPI.size());
        System.out.println("list: " + Arrays.toString(rgAPI.getKeys()));

        try {
            System.out.println("remove Narcos");
            rgAPI.remove("Narcos");
        } catch (IllegalSerieException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("list: " + Arrays.toString(rgAPI.getKeys()));
    }
}
