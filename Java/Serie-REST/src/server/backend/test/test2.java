package server.backend.test;

        /*
         *  Author: Raffaele Mignone
         *  Mat: 863/747
         *  Date: 13/12/17
         */

import server.backend.Registry;

import java.io.IOException;
import java.util.Arrays;

public class test2 {
    public static void main(String[] args){
        Registry rg = new Registry();
        try {
            rg.load("save");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("list: " + Arrays.toString(rg.getKeys()));
    }
}
