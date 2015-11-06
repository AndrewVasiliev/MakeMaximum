package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by root on 06.11.15.
 */
public class TileActor extends Actor{
    private TextureRegion starSprite;
    private int value;


    public TileActor (TextureRegion sp, int value) {
        this.value = value;
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
    }
}
