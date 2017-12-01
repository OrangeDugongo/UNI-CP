/*
 *      Copyright (c) 2017 Raffaele Mignone
 *
 *      This file is part of  Score30
 *
 *      Score30 is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 2 of the License, or
 *      (at your option) any later version.
 *
 *      Score30 is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with Score30.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.unisannio.cp.orange.score30;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/11/17
 *
 */


import java.io.Serializable;

public class Serie implements Serializable{
    public Serie(String nome, float score) {
        this.nome = nome;
        this.score = score;
        this.network = findNetwork();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    private String findNetwork(){
        switch(nome){
            case "Big Mouth":
            case "BoJack Horseman":
            case "Stranger Things":
            case "Narcos":
                return "Netflix";
            case "Breaking Bad":
            case "Halt and Catch Fire":
                return "AMC";
            case "Game of Thrones":
            case "Silicon Valley":
            case "Westworld":
                return "HBO";
            case "Grey's Anatomy":
            case "New Girl":
                return "FOX";
            case "Rick and Morty":
                return "Adult Swim";
            case "Mr. Robot":
                return "USA";
            default:
                return "Network Unknown";
        }
    }

    private String nome;
    private String network;
    private float score;
}
