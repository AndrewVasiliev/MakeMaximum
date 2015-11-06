package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.noip.andrewvasiliev.makemaximum.Utils.Constants;

import java.util.Random;

/**
 * Created by root on 05.11.15.
 */
public class BackgroundStar extends Actor {
    private TextureRegion starSprite;
    private int layer;
    private int maxX;
    private float dx;


    public BackgroundStar (TextureRegion sp, int lr, int maxX) {
        this.maxX = maxX;
        dx = 0;
        layer = lr;
        starSprite = sp;
        setBounds(getX(), getY(), starSprite.getRegionWidth(), starSprite.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(starSprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act (float delta){
        //dx = getX();
        //dx= getY();
        dx = delta * layer *10;
        moveBy(dx, 0);
        if (getX() > maxX) {
            setX(getX() - maxX);
        }

    }

}
