package com.groupproject.mobilegame;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background, background2, background3;
    Bitmap[] copter;
    Bitmap tubeTop, tubeBottom;
    Bitmap redTubeTop, redTubeBottom;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background2 = BitmapFactory.decodeResource(res, R.drawable.background2);
        background3 = BitmapFactory.decodeResource(res, R.drawable.background3);
        background = scaleImage(background);
        background2 = scaleImage(background2);
        background3 = scaleImage(background3);
        copter = new Bitmap[4];
        copter[0] = BitmapFactory.decodeResource(res, R.drawable.frame_1);
        copter[1] = BitmapFactory.decodeResource(res, R.drawable.frame_2);
        copter[2] = BitmapFactory.decodeResource(res, R.drawable.frame_3);
        copter[3] = BitmapFactory.decodeResource(res, R.drawable.frame_4);
        tubeTop = BitmapFactory.decodeResource(res, R.drawable.tube_top);
        tubeBottom = BitmapFactory.decodeResource(res, R.drawable.tube_bottom);
        redTubeTop = BitmapFactory.decodeResource(res, R.drawable.red_tube_top);
        redTubeBottom = BitmapFactory.decodeResource(res, R.drawable.red_tube_bottom);
    }

    // Return Red Tube-Top Bitmap
    public Bitmap getRedTubeTop(){
        return redTubeTop;
    }

    // Return Red Tube-Bottom Bitmap
    public Bitmap getRedTubeBottom(){
        return redTubeBottom;
    }

    // Return Tube-Top Bitmap
    public Bitmap getTubeTop(){
        return tubeTop;
    }

    // Return Tube-Bottom Bitmap
    public Bitmap getTubeBottom(){
        return tubeBottom;
    }

    //Return Tube-width
    public int getTubeWidth(){
        return tubeTop.getWidth();
    }

    //Return Tube-height
    public int getTubeHeight(){
        return tubeTop.getHeight();
    }


    public Bitmap getCopter(int frame){
        return copter[frame];
    }

    public int getCopterWidth(){
        return copter[0].getWidth();
    }

    public int getCopterHeight(){
        return copter[0].getHeight();
    }

    //Return background bitmap
    public Bitmap getBackground(){
        return background;
    }

    //Return background width
    public int getBackgroundWidth(){
        return background.getWidth();
    }

    //Return background height
    public int getBackgroundHeight(){
        return background.getHeight();
    }

    //Return background2 bitmap
    public Bitmap getBackground2(){
        return background2;
    }

    //Return background2 width
    public int getBackground2Width(){
        return background2.getWidth();
    }

    //Return background2 height
    public int getBackground2Height(){
        return background2.getHeight();
    }

    //Return background3 bitmap
    public Bitmap getBackground3(){
        return background3;
    }

    //Return background3 width
    public int getBackground3Width(){
        return background3.getWidth();
    }

    //Return background3 height
    public int getBackground3Height(){
        return background3.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        /*
        We'll multiply widthHeightRatio with screenHeight to get scaled width of the bitmap.
        Then call createScaledBitmap() to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
