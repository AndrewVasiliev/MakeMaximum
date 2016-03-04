package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;




/**
 * Created by root on 06.11.15.
 */
public class TileActor extends Actor {
    private Array<TextureRegion> starSprite;
    private int value;
    public int column, row;
    private Label.LabelStyle valueLabelStyle;
    private Label valueLabel;

    public boolean touch = false;



    public TileActor (Array<TextureRegion> sp, int value, int column, int row) {
        this.value = value;
        this.column = column;
        this.row = row;
        starSprite = sp;

        valueLabelStyle = new Label.LabelStyle();
        valueLabelStyle.font = MakeMaximum.tileFont;
        //valueLabelStyle.font.setColor(new Color( Integer.parseInt("7fff0033", 16)));

        valueLabel = new Label(Integer.toString(this.value), valueLabelStyle);


        //setTouchable(Touchable.enabled);
    }


    @Override
    protected void positionChanged () {
//        valueLabel.setPosition(this.getX() + ((this.getWidth() - valueLabel.getPrefWidth()) / 2),
//                this.getY() + ((this.getHeight() - valueLabel.getPrefHeight()) / 2));


        valueLabel.setPosition( this.getX() + ((this.getScaleX() * this.getWidth() - valueLabel.getScaleX() * valueLabel.getWidth()) / 2),
                                this.getY() + ((this.getScaleY() * this.getHeight() - valueLabel.getScaleY() * valueLabel.getHeight()) / 2) );
    }

    @Override
    protected void sizeChanged () {

        //valueLabel.setScale(this.getWidth() / starSprite.getRegionWidth(), this.getHeight() / starSprite.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if ( (GameBoardActor.locGameLogic.vertical_move && (GameBoardActor.locGameLogic.current_position == column)) ||
                (!GameBoardActor.locGameLogic.vertical_move && (GameBoardActor.locGameLogic.current_position == row)) ) {
            batch.draw(starSprite.get(1), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }

        if (touch){
            batch.draw(starSprite.get(1), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }

        batch.draw(starSprite.get(0), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());

        valueLabel.draw(batch, alpha);
    }

//    @Override
//    public void act (float delta){
//    }


}
