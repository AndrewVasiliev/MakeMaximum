package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;
import org.noip.andrewvasiliev.makemaximum.Screens.GameScreen;
import org.noip.andrewvasiliev.makemaximum.Utils.Constants;

import java.util.Random;

/**
 * Created by Andrew on 16.11.2015.
 */
public class HudActor extends Actor {
    private Group locGroup;
    private Label[] playerName, playerScore;
    private String[] sName;
    private Table[] playerTable;
    private int curr_player;
    Label.LabelStyle playerNameStyle, playerNameStyleActive;

    private Label nextMoveMessage;
    private TextField nextMoveMessage2;
    private String nextMoveString;
    private Table nextMoveTable;
    private GameScreen gscr;

    public HudActor (final GameScreen gscr_temp) {
        gscr = gscr_temp;

        curr_player = 0;

        locGroup = new Group();

        playerName = new Label[2];
        playerScore = new Label[2];
        playerTable = new Table[2];
        sName = new String[2];

        playerNameStyle = new Label.LabelStyle();
        playerNameStyle.font = MakeMaximum.fontSmall;

        playerNameStyleActive = new Label.LabelStyle();
        playerNameStyleActive.font = MakeMaximum.fontSmall;
        playerNameStyleActive.fontColor = Color.CYAN;


        Label.LabelStyle playerScoreStyle = new Label.LabelStyle();
        playerScoreStyle.font = MakeMaximum.fontMedium;

        for (int i=0; i<2; i++){
            playerName[i] = new Label("", playerNameStyle);
            playerScore[i] = new Label("", playerScoreStyle);
            playerTable[i] = new Table(gscr.game.uiSkin);
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

        nextMoveString = "Следующий ход у %s";

        nextMoveMessage = new Label("xxxxxxxxxxx", playerNameStyle);

        nextMoveMessage2 = new TextField("1234567890", gscr.game.uiSkin);
        nextMoveMessage2.setWidth(400);
        nextMoveTable = new Table();
        nextMoveTable.add(nextMoveMessage2).row();
        nextMoveTable.setPosition(Constants.WIDTH/2, Constants.HEIGHT/2);
        locGroup.addActor(nextMoveTable /*nextMoveMessage2*/);
        //nextMoveTable.center();
        //nextMoveTable.setVisible(false);

    }


    @Override
    public void draw(Batch batch, float alpha) {
        locGroup.act(Gdx.graphics.getDeltaTime());
        locGroup.draw(batch, alpha);
    }

    public void setPlayerName (int playerNum, String playerName){
        this.playerName[playerNum].setText(playerName);
        sName[playerNum] = playerName;
    }

    public void setPlayerScore (int playerNum, int playerScore){
        this.playerScore[playerNum].setText(Integer.toString(playerScore));
    }

    public void setPlayerTablePos (int playerNum, float x, float y){
        playerTable[playerNum].setPosition(x, y);
    }

    public void setPlayerTableWidth (int playerNum, float wid) {
        playerTable[playerNum].setWidth(wid);
    }

    public float getPlayerTableWidth (int playerNum) {
        return playerTable[playerNum].getPrefWidth();
    }

    public Vector2 getPlayerTabelCoord (int pltNum) {
        Vector2 coord = new Vector2();
        coord.x = playerTable[pltNum].getX() + playerTable[pltNum].getWidth()/2;
        coord.y = playerTable[pltNum].getY()/* - playerTable[pltNum].getMaxHeight()*/;



        return coord;
    }

    private void changeNextMessageString (int plr) {
        String tempStr;
        tempStr = String.format(nextMoveString, sName[plr]);
        nextMoveMessage.setText(tempStr);
    }

    public void setCurrentPlayer (int plr) {
        curr_player = plr;
        playerName[curr_player].setStyle(playerNameStyleActive);
        playerName[curr_player^1].setStyle(playerNameStyle);
        changeNextMessageString(plr);
    }

}
