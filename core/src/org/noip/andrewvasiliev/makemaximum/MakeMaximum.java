package org.noip.andrewvasiliev.makemaximum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.noip.andrewvasiliev.makemaximum.Screens.GameScreen;
import org.noip.andrewvasiliev.makemaximum.objects.BackgroundActor;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoard;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoardActor;


public class MakeMaximum extends Game {
	public GameBoardActor gameboard;
	public BackgroundActor background;
	public Stage stage;
	public static int HEIGHT;
	public static int WIDTH;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		//stage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT));
		HEIGHT = stage.getViewport().getScreenHeight();
		WIDTH = stage.getViewport().getScreenWidth();

		background = new BackgroundActor();
		background.setPosition(0, 0);

		gameboard = new GameBoardActor(9);

		stage.addActor(background);
		stage.addActor(gameboard);

		this.setScreen(new GameScreen(this, stage));

	}

	@Override
	public void render () {

		super.render();
	}
	@Override
	public void dispose() {

	}
}
