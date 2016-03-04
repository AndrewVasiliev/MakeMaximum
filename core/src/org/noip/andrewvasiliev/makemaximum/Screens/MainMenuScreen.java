package org.noip.andrewvasiliev.makemaximum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;
import org.noip.andrewvasiliev.makemaximum.objects.BackgroundActor;
import org.noip.andrewvasiliev.makemaximum.objects.myStage;

/**
 * Created by root on 02.02.16.
 */
public class MainMenuScreen  implements Screen {
    public final MakeMaximum game;
    private Stage stage;
    private BackgroundActor background;

    public int HEIGHT;
    public int WIDTH;

    public MainMenuScreen (final MakeMaximum gam/*, final Stage stg*/) {
        game = gam;
        //stage = stg;
        stage = new myStage(new ScreenViewport());

        //stage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT));
//        HEIGHT = stage.getViewport().getScreenHeight();
//        WIDTH = stage.getViewport().getScreenWidth();
        HEIGHT = (int)stage.getHeight();
        WIDTH = (int)stage.getWidth();

        background = new BackgroundActor();
        background.setPosition(0, 0);

        Table rootTable = new Table();
        //rootTable.debugAll();
        rootTable.setFillParent(true);
        //rootTable.top();


        stage.addActor(background);
        stage.addActor(rootTable);


        rootTable.add(new Label("Make Maximum", game.uiSkin, "title")).colspan(3);
        rootTable.row();
        rootTable.row();
        rootTable.row();
        rootTable.row();

        Table menuTable = new Table();
        //menuTable.setFillParent(true);
        //menuTable.debug();
        menuTable.defaults().pad(2.0f);

        TextButton locPvP = new TextButton(/*"Player vs Player"*/ "Игрок против Игрока", game.uiSkin, "round");
        menuTable.add(locPvP).row();
        TextButton locPvAI = new TextButton("Player vs AI", game.uiSkin, "round");
        menuTable.add(locPvAI).row();
        TextButton locNetwork = new TextButton("Network game", game.uiSkin, "round");
        menuTable.add(locNetwork).row();
        TextButton locOptions = new TextButton("Options", game.uiSkin, "round");
        menuTable.add(locOptions).row();

        rootTable.add(menuTable);

        locNetwork.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                new Dialog("Warning", game.uiSkin, "dialog") {
                    protected void result(Object object) {
                        System.out.println("Chosen: " + object);
                    }
                }.text("This feature is not yet implemented.\n Sorry. :)").button("Ok", true)
                        .key(Input.Keys.ENTER, true).show(stage).getTitleLabel().setAlignment(Align.center);
            }
        });

        locPvP.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.locGameScreen);
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
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
        stage.dispose();
    }
}
