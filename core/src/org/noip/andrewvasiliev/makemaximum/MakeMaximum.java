package org.noip.andrewvasiliev.makemaximum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.jrenner.smartfont.SmartFontGenerator;
import org.noip.andrewvasiliev.makemaximum.Screens.GameScreen;
import org.noip.andrewvasiliev.makemaximum.Screens.MainMenuScreen;


public class MakeMaximum extends Game {
	//public BackgroundActor background;
	public GameScreen locGameScreen;
    public MainMenuScreen locMainMenu;
	/*public GameBoardActor gameboard;
	public HudActor hud;*/
	//public Stage stage;
	public Skin uiSkin;
	public static int HEIGHT;
	public static int WIDTH;
	public static BitmapFont tileFont;
	public static BitmapFont fontSmall;
	public static BitmapFont fontMedium;
	public static BitmapFont fontLarge;



	@Override
	public void create () {
		//makemaximum

        HEIGHT = Gdx.graphics.getHeight();
        WIDTH = Gdx.graphics.getWidth();

		SmartFontGenerator fontGen = new SmartFontGenerator();
		FileHandle exoFile = Gdx.files.internal("liberation.ttf");

		tileFont = fontGen.createFont(exoFile, "exo-tile", 48);
		fontSmall = fontGen.createFont(exoFile, "exo-small", 24);
		fontMedium = fontGen.createFont(exoFile, "exo-medium", 48);
		fontLarge = fontGen.createFont(exoFile, "exo-large", 64);

        uiSkin = new Skin();
		uiSkin.add("font-title", fontSmall, BitmapFont.class);
		uiSkin.addRegions(new TextureAtlas("data/uiskin.atlas"));
		uiSkin.load(Gdx.files.internal("data/uiskin.json"));

/*
		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		uiSkin.add("tileFont", tileFont);
*/

/*
        stage = new myStage(new ScreenViewport());
        //stage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT));
        HEIGHT = stage.getViewport().getScreenHeight();
        WIDTH = stage.getViewport().getScreenWidth();
*/
		// multiplexer = new InputMultiplexer();
/*
		background = new BackgroundActor();
		background.setPosition(0, 0);
		//stage.addActor(background);
*/

        locGameScreen = new GameScreen(this/*, stage*/);
        locMainMenu = new MainMenuScreen(this);

		this.setScreen(locMainMenu);

	}

	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose() {

	}

}
