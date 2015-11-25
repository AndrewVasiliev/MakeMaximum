package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;

import java.util.Random;

/**
 * Created by Andrew on 16.11.2015.
 */
public class HudActor extends Actor {
    private Group locGroup;
    private Label[] playerName, playerScore;
    private Table[] playerTable;

    public HudActor () {
        locGroup = new Group();

        playerName = new Label[2];
        playerScore = new Label[2];
        playerTable = new Table[2];

        Label.LabelStyle playerNameStyle = new Label.LabelStyle();
        playerNameStyle.font = MakeMaximum.fontSmall;

        Label.LabelStyle playerScoreStyle = new Label.LabelStyle();
        playerScoreStyle.font = MakeMaximum.fontMedium;

        for (int i=0; i<2; i++){
            playerName[i] = new Label("", playerNameStyle);
            playerScore[i] = new Label("", playerScoreStyle);
            playerTable[i] = new Table();
        }

        for (int i=0; i<2; i++) {
            //playerTable[i].align(Align.center | Align.top);
            //playerTable[i].center();
            playerTable[i].top();

            //playerTable[i].setDebug(true);
            //playerTable[i].setFillParent(true);
            //playerTable[i].debugAll();
            //playerTable[i].setPosition(0, 100);
            locGroup.addActor(playerTable[i]);

            playerTable[i].add(playerName[i]).row();
            playerTable[i].add(playerScore[i]).row();
        }
    }


    @Override
    public void draw(Batch batch, float alpha) {
        locGroup.act(Gdx.graphics.getDeltaTime());
        locGroup.draw(batch, alpha);
    }

    public void setPlayerName (int playerNum, String playerName){
        this.playerName[playerNum].setText(playerName);
    }

    public void setPlayerScore (int playerNum, int playerScore){
        this.playerScore[playerNum].setText(Integer.toString(playerScore));
    }

    public void setPlayerTablePos (int playerNum, float x, float y){
        playerTable[playerNum].setPosition(x,y);
    }

    public void setPlayerTableWidth (int playerNum, float wid) {
        playerTable[playerNum].setWidth(wid);
    }

    public float getPlayerTableWidth (int playerNum) {
        return playerTable[playerNum].getPrefWidth();
    }


}
