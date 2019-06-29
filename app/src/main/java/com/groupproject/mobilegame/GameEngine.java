package com.groupproject.mobilegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    BackgroundImage backgroundImage;
    Copter copter;
    static int gameState;
    ArrayList<Tube> tubes;
    Random random;
    int score; // Stores the score
    int scoringTube; // Keeps track of scoring tube
    Paint scorePaint;
    int level = 1;
    boolean tubeVelocityFlag = false;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        copter = new Copter();

        gameState = 0;
        tubes = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < AppConstants.numberOfTubes; i++) {
            int tubeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenTubes;
            // Get topTubeOffsetY
            int topTubeOffsetY = AppConstants.minTubeOffsetY +
                    random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
            // Now create Tube objects
            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }
        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    public void updateAndDrawTubes(Canvas canvas) {
        if (gameState == 1) {
            if (level == 2 && tubeVelocityFlag == false) {
                AppConstants.tubeVelocity = 15;
                tubeVelocityFlag = true;
            } else if (level == 3 && tubeVelocityFlag == true) {
                AppConstants.tubeVelocity = 18;
                tubeVelocityFlag = false;
            }
            if ((tubes.get(scoringTube).getTubeX() < copter.getX() + AppConstants.getBitmapBank().getCopterWidth())
                    && (tubes.get(scoringTube).getTopTubeOffsetY() > copter.getY()
                    || tubes.get(scoringTube).getBottomTubeY() < (copter.getY() +
                    AppConstants.getBitmapBank().getCopterHeight()))) {
                // Go to GameOver screen
                gameState = 2;
                //Log.d("Game", "Over");
                AppConstants.getSoundBank().playHit();
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else if (tubes.get(scoringTube).getTubeX() < copter.getX() - AppConstants.getBitmapBank().getTubeWidth()) {
                score++;
                scoringTube++;
                if (scoringTube > AppConstants.numberOfTubes - 1) {
                    scoringTube = 0;
                }
                AppConstants.getSoundBank().playPoint();
            }
            for (int i = 0; i < AppConstants.numberOfTubes; i++) {
                if (tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()) {
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() +
                            AppConstants.numberOfTubes * AppConstants.distanceBetweenTubes);
                    int topTubeOffsetY = AppConstants.minTubeOffsetY +
                            random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                    tubes.get(i).setTubeColor();
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
                if (tubes.get(i).getTubeColor() == 0) {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                } else {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                }
            }
            canvas.drawText("Pt: " + score, 0, 110, scorePaint);
        }
    }

    public void updateAndDrawBackgroundImage(Canvas canvas) {
        if (score > 3 && score < 7) {
            level = 2;
        } else if (score >= 7) {
            level = 3;
        }
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        if (level == 1) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() +
                        AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
            }
        } else if (level == 2) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground2(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackground2Width() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground2(), backgroundImage.getX() +
                        AppConstants.getBitmapBank().getBackground2Width(), backgroundImage.getY(), null);
            }
        } else if (level == 3) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground3(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackground3Width() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground3(), backgroundImage.getX() +
                        AppConstants.getBitmapBank().getBackground3Width(), backgroundImage.getY(), null);
            }
        }
    }

    public void updateAndDrawCopter(Canvas canvas) {
        if (gameState == 1) {
            if (copter.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getCopterHeight()) || copter.getVelocity() < 0) {
                copter.setVelocity(copter.getVelocity() + AppConstants.gravity);
                copter.setY(copter.getY() + copter.getVelocity());
            }
        }
        int currentFrame = copter.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getCopter(currentFrame), copter.getX(), copter.getY(), null);
        currentFrame++;

        if (currentFrame > copter.maxFrame) {
            currentFrame = 0;
        }
        copter.setCurrentFrame(currentFrame);
    }
}


