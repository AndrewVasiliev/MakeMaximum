package org.noip.andrewvasiliev.makemaximum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.jrenner.smartfont.SmartFontGenerator;
import org.noip.andrewvasiliev.makemaximum.Screens.GameScreen;
import org.noip.andrewvasiliev.makemaximum.objects.BackgroundActor;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoard;
import org.noip.andrewvasiliev.makemaximum.objects.GameBoardActor;
import org.noip.andrewvasiliev.makemaximum.objects.HudActor;
import org.noip.andrewvasiliev.makemaximum.objects.myStage;


public class MakeMaximum extends Game {
	public BackgroundActor background;
	/*public GameBoardActor gameboard;
	public HudActor hud;*/
	public Stage stage;
	public static int HEIGHT;
	public static int WIDTH;
	public static BitmapFont fontSmall;
	public static BitmapFont fontMedium;
	public static BitmapFont fontLarge;
public static InputMultiplexer multiplexer;

	@Override
	public void create () {
		//makemaximum
		SmartFontGenerator fontGen = new SmartFontGenerator();
		FileHandle exoFile = Gdx.files.internal("liberation.ttf");

		fontSmall = fontGen.createFont(exoFile, "exo-small", 24);
		fontMedium = fontGen.createFont(exoFile, "exo-medium", 48);
		fontLarge = fontGen.createFont(exoFile, "exo-large", 64);


		 multiplexer = new InputMultiplexer();

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
		this.setScreen(new GameScreen(this, stage));



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

/*	public String getPlayerName (int i) {
		return gameboard.gb.getPlayerName(i);
	}*/
}
