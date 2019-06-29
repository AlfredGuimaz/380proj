package com.groupproject.mobilegame;
public class Copter {

    private int copterX, copterY, currentFrame, velocity;
    public static int maxFrame;

    public Copter(){
        copterX = AppConstants.SCREEN_WIDTH/2 - AppConstants.getBitmapBank().getCopterWidth()/2;
        copterY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitmapBank().getCopterHeight()/2;
        currentFrame = 0;
        maxFrame = 3;
        velocity = 0;
    }

    // Getter method for velocity
    public int getVelocity(){
        return velocity;
    }

    // Setter method for velocity
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    // Getter method for current frame
    public int getCurrentFrame(){
        return currentFrame;
    }

    // Setter method for current frame

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }


    public int getX(){
        return copterX;
    }


    public int getY(){
        return copterY;
    }

    // Setter method for setting the X-coordinate
    public void setX(int copterX){
        this.copterX = copterX;
    }

    // Setter method for setting the Y-coordinate
    public void setY(int copterY){
        this.copterY = copterY;
    }
}
