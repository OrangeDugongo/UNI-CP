package it.unisannio.cp.orange.score20;


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
                return "HBO";
            case "Grey's Anatomy":
            case "New Girl":
                return "FOX";
            case "Rick and Morty":
                return "Adult Swim";
            default:
                return "Network Unknown";
        }
    }

    private String nome;
    private String network;
    private float score;
}
