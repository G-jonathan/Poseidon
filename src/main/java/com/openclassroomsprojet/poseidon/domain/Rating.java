package com.openclassroomsprojet.poseidon.domain;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "moodys_rating")
    private String moodysRating;
    @Column(name = "sand_prating")
    private String sandPrating;
    @Column(name = "fitch_rating")
    private String fitchRating;
    @Column(name = "order_number")
    private int orderNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPrating() {
        return sandPrating;
    }

    public void setSandPrating(String sandPrating) {
        this.sandPrating = sandPrating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}