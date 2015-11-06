package org.noip.andrewvasiliev.makemaximum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;
import org.noip.andrewvasiliev.makemaximum.Utils.Constants;

/**
 * Created by root on 05.11.15.
 */
public class GameScreen implements Screen {
    final MakeMaximum game;
    private Stage stage;

    public GameScreen (final MakeMaximum gam, final Stage stg) {
        game = gam;

        stage = stg;
        //stage = new Stage(new ScreenViewport());
        //stage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT));
        //Constants.HEIGHT = stage.getViewport().getScreenHeight();
        //Constants.WIDTH = stage.getViewport().getScreenWidth();

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
