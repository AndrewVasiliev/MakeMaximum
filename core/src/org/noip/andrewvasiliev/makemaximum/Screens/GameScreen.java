package org.noip.andrewvasiliev.makemaximum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;
import org.noip.andrewvasiliev.makemaximum.Utils.Constants;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoardActor;
import org.noip.andrewvasiliev.makemaximum.objects.HudActor;

/**
 * Created by root on 05.11.15.
 */
public class GameScreen implements Screen {
    final MakeMaximum game;
    private Stage stage;

    public GameBoardActor gameboard;
    public HudActor hud;


    public GameScreen (final MakeMaximum gam, final Stage stg) {
        game = gam;
        stage = stg;

        gameboard = new GameBoardActor(9);
        hud = new HudActor();

        for (int i=0; i<2; i++){
            hud.setPlayerName(i, gameboard.gb.getPlayerName(i));
            hud.setPlayerScore(i, gameboard.gb.getPlayerScore(i));
            hud.setPlayerTablePos(i, gameboard.getPlayerTableXCoord(i) /*+ gameboard.getPlayerTableWidth() / 2*/, game.HEIGHT);
            hud.setPlayerTableWidth(i, gameboard.getPlayerTableWidth());
        }

        stage.addActor(gameboard);
        stage.addActor(hud);

        Gdx.input.setInputProcessor(gameboard);

        //stage.addActor(game.background);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }




}
