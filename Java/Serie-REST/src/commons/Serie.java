package commons;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 13/12/17
 */

import java.io.Serializable;

public class Serie implements Serializable{

    public Serie(String name, String network, int season, String status, float score) {
        this.name = name;
        this.network = network;
        this.season = season;
        this.status = status;
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String name;
    private String network;
    private int season;
    private String status;
    private float score;
}