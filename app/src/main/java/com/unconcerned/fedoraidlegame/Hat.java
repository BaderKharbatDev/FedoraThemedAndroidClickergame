package com.unconcerned.fedoraidlegame;

public class Hat {
    private String Text1;
    private String Text2;
    private int power;
    private int imageLocation;
    private long cost;
    private boolean ownedBool;
    private boolean equiped;

    public Hat(String text1, String text2, int power, long cost, int imageLocation, boolean bool, boolean equiped) {
        Text1 = text1;
        Text2 = text2;
        this.power = power;
        this.imageLocation = imageLocation;
        this.cost = cost;
        this.ownedBool = bool;
        this.equiped = equiped;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
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

    public int changeGetImage() {
        if(isOwnedBool() == true){
            return R.drawable.ownedsymbol2;
        } else{
            return R.drawable.ownedsymbol;
        }
    }

    public int changeEquipImage(){
        if(isOwnedBool() == true && isEquiped() == true){
            return R.drawable.equipedfinal;
        } else {
            return R.drawable.unequipedfinal;
        }
    }

    public boolean isEquiped() {
        return equiped;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }
}
