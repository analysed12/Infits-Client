package com.example.infits;


import java.io.Serializable;

public class Dietician_review implements Serializable {

    private String reviewer_name;
    private String reviewer_img;
    private String reviwer_review;
    private String reviewer_rating;

    public Dietician_review(String reviewer_name, String reviewer_img, String reviwer_review, String reviewer_rating) {
        this.reviewer_name = reviewer_name;
        this.reviewer_img = reviewer_img;
        this.reviwer_review = reviwer_review;
        this.reviewer_rating = reviewer_rating;
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }

    public String getReviewer_img() {
        return reviewer_img;
    }

    public void setReviewer_img(String reviewer_img) {
        this.reviewer_img = reviewer_img;
    }

    public String getReviwer_review() {
        return reviwer_review;
    }

    public void setReviwer_review(String reviwer_review) {
        this.reviwer_review = reviwer_review;
    }

    public String getReviewer_rating() {
        return reviewer_rating;
    }

    public void setReviewer_rating(String reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }
}
