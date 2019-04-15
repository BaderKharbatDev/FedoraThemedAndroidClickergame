package com.unconcerned.fedoraidlegame;

class Hand {
    private String Text1;
    private String Text2;
    private double speed;
    private int imageLocation;
    private long cost;
    private boolean ownedBool;

    public Hand(String text1, String text2, double speed, long cost, int imageLocation, boolean bool) {
        Text1 = text1;
        Text2 = text2;
        this.speed = speed;
        this.imageLocation = imageLocation;
        this.cost = cost;
        this.ownedBool = bool;
    }

    public void changeText1(String text){
        Text1 = text;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getText1() {
        return Text1;
    }

    public void setText1(String text1) {
        Text1 = text1;
    }

    public String getText2() {
        return Text2;
    }

    public void setText2(String text2) {
        Text2 = text2;
    }

    public int getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(int imageLocation) {
        this.imageLocation = imageLocation;
    }

    public boolean isOwnedBool() {
        return ownedBool;
    }

    public void setOwnedBool(boolean ownedBool) {
        this.ownedBool = ownedBool;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int changeGetImage() {
        if(isOwnedBool() == true){
            return R.drawable.ownedsymbol2;
        } else{
            return R.drawable.ownedsymbol;
        }
    }
}

