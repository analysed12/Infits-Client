package com.example.infits;

public class Questions {

    private String cardname, answers;
    private boolean expandable;


    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Questions(String cardname, String answers) {
        this.cardname = cardname;
        this.answers = answers;
        this.expandable = false;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "cardname='" + cardname + '\'' +
                ", answers='" + answers + '\'' +
                '}';
    }


}
