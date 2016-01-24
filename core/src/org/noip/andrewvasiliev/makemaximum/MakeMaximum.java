package org.noip.andrewvasiliev.makemaximum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.jrenner.smartfont.SmartFontGenerator;
import org.noip.andrewvasiliev.makemaximum.Screens.GameScreen;
import org.noip.andrewvasiliev.makemaximum.objects.BackgroundActor;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoard;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoardActor;
import org.noip.andrewvasiliev.makemaximum.objects.HudActor;
import org.noip.andrewvasiliev.makemaximum.objects.TileActor;
import org.noip.andrewvasiliev.makemaximum.objects.myStage;


public class MakeMaximum extends Game implements InputProcessor {
	public BackgroundActor background;
	public GameScreen locGameScreen;
	/*public GameBoardActor gameboard;
	public HudActor hud;*/
	public Stage stage;
	public Skin uiSkin;
	public static int HEIGHT;
	public static int WIDTH;
	public static BitmapFont tileFont;
	public static BitmapFont fontSmall;
	public static BitmapFont fontMedium;
	public static BitmapFont fontLarge;
//public static InputMultiplexer multiplexer;


	@Override
	public void create () {
		//makemaximum
		SmartFontGenerator fontGen = new SmartFontGenerator();
		FileHandle exoFile = Gdx.files.internal("liberation.ttf");

		tileFont = fontGen.createFont(exoFile, "exo-tile", /*(int)locGameScreen.gameboard.getWidth()*/ 48);
		fontSmall = fontGen.createFont(exoFile, "exo-small", 24);
		fontMedium = fontGen.createFont(exoFile, "exo-medium", 48);
		fontLarge = fontGen.createFont(exoFile, "exo-large", 64);

		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		uiSkin.add("tileFont", tileFont);


		// multiplexer = new InputMultiplexer();

		stage = new myStage(new ScreenViewport());
		//stage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT));
		HEIGHT = stage.getViewport().getScreenHeight();
		WIDTH = stage.getViewport().getScreenWidth();

		background = new BackgroundActor();
		background.setPosition(0, 0);
		stage.addActor(background);

/*		gameboard = new GameBoardActor(9);
		hud = new HudActor();

		stage.addActor(gameboard);
		stage.addActor(hud);

		Gdx.input.setInputProcessor(gameboard);
*/

		Gdx.input.setInputProcessor(this);

		locGameScreen = new GameScreen(this, stage);



		this.setScreen(locGameScreen);



		//font test
/*		SmartFontGenerator fontGen = new SmartFontGenerator();
		FileHandle exoFile = Gdx.files.internal("liberation.ttf");

		BitmapFont fontSmall = fontGen.createFont(exoFile, "exo-small", 24);
		BitmapFont fontMedium = fontGen.createFont(exoFile, "exo-medium", 48);
		BitmapFont fontLarge = fontGen.createFont(exoFile, "exo-large", 64);


		stage = new Stage();

		Label.LabelStyle smallStyle = new Label.LabelStyle();
		smallStyle.font = fontSmall;
		Label.LabelStyle mediumStyle = new Label.LabelStyle();
		mediumStyle.font = fontMedium;
		Label.LabelStyle largeStyle = new Label.LabelStyle();
		largeStyle.font = fontLarge;

		Label small = new Label("Small Font", smallStyle);
		Label medium = new Label("Medium Font", mediumStyle);
		Label large = new Label("Large Font", largeStyle);
		Label small2 = new Label("Маленький шрифт", smallStyle);
		Label medium2 = new Label("Средний шрифт", mediumStyle);
		Label large2 = new Label("Большой шрифт", largeStyle);
		Label numbers = new Label("1234567890-=+{}", largeStyle);


		Table table = new Table();
		table.setFillParent(true);
		table.align(Align.left);
		table.setDebug(true);
		//table.setPosition(200,200);
		stage.addActor(table);

		//table.defaults().size(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 12);

		table.add(small).row();
		table.add(medium).row();
		table.add(large).row();
		table.add(small2).row();
		table.add(medium2).row();
		table.add(large2).row();
		table.add(numbers).row();

		this.setScreen(new GameScreen(this, stage));*/
	}

	@Override
	public void render () {
		//makemaximum

		super.render();


		//test font
/*		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		*/
	}
	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 coord = this.locGameScreen.gameboard.screenToLocalCoordinates(new Vector2((float) screenX, (float) screenY));
		TileActor temp = (TileActor) locGameScreen.gameboard.locGroup.hit(coord.x, coord.y, true);
		if (temp != null) {
			//temp.touch = true;
			locGameScreen.TilePressed(temp);


/*			MoveToAction moveAction = new MoveToAction();

			moveAction.setPosition(plr_coor[gb.current_player], 0f);
			moveAction.setDuration(0.3f);

			RemoveActorAction removeActor = new RemoveActorAction();

			SequenceAction mySequence = new SequenceAction(moveAction, removeActor);

			temp.addAction(mySequence);
*/
			//temp.addAction(removeActor);

			//locGroup.removeActor(temp);
			//MakeMaximum.multiplexer.removeProcessor(temp);


			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

/*	public String getPlayerName (int i) {
		return gameboard.gb.getPlayerName(i);
	}*/
}
