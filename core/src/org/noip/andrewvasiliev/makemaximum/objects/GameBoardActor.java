package org.noip.andrewvasiliev.makemaximum.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import org.noip.andrewvasiliev.makemaximum.MakeMaximum;

import java.util.Random;

/**
 * Created by root on 06.11.15.
 */
public class GameBoardActor extends Actor {
    private GameBoard gb;
    private TileActor[] tiles;
    private Group locGroup;
    private final  TextureRegion tileTexture;
    private int locFieldSize; //размер игрового поля в клетках (поле всегда квадратное)

    private float width;    // width of one tile
    private float height;   // height of one tile
    private int selX;       // X index of selection
    private int selY;       // Y index of selection
    //ava beg
    private int offsetX;    //смещение по Х, для того чтобы поле было посередине
    private int offsetY;    //смещение по Y, для того чтобы поле было посередине
    private int fieldWidth; //ширина игрового поля в пикселях
    private int fieldHeight;//высота игрового поля в пикселях

    private int plr_coor[] = new int [2];   //координаты по Х для отображения имен игроков и заработанных очков
    private int plr_height; //высота текста имен игроков и заработанных очков
    private int plr_width;  //ширина полей для имен и очков

    public GameBoardActor (int FieldSize) {
        locFieldSize = FieldSize;

        locGroup = new Group();

        tileTexture = new TextureRegion(new Texture("tile.png"));
        gb = new GameBoard();
        gb.GameBoard(locFieldSize, null);

        tiles = new TileActor[locFieldSize * locFieldSize];

        CalculateTileParam(MakeMaximum.WIDTH, MakeMaximum.HEIGHT);


        for (int y = 0; y< locFieldSize; y++) {
            for (int x = 0; x < locFieldSize; x++) {
                tiles[y * locFieldSize + x] = new TileActor(tileTexture, gb.getTile(x,y));

                tiles[y * locFieldSize + x].setPosition(offsetX + x * width, offsetY + y * height);

                // Set the name of the Jet to it's index within the loop
                tiles[y * locFieldSize + x].setName("tile" + Integer.toString(x) + Integer.toString(y));

                tiles[y * locFieldSize + x].setSize(width, height);

                // Add them to the stage
                locGroup.addActor(tiles[y * locFieldSize + x]);
            }
        }

    }

    protected void CalculateTileParam(int w, int h) {
        //ava beg
        fieldWidth  = w < h ? w : h;
        fieldHeight = w < h ? w : h;


        offsetX = ((w - fieldWidth) / 2);
        offsetY = ((h - fieldHeight) / 2);

        width = fieldWidth / (float) locFieldSize;
        height = fieldHeight /  (float) locFieldSize;

        //имена игроков и заработанные очки
        plr_height = (int) height /2;
        if (offsetY < plr_height) {
            //альбом
            plr_width = (int)((w - fieldWidth) /2 );
            plr_coor[0] = 0;
            plr_coor[1] = w - plr_width;

        } else {
            //портрет
            plr_width = (int)(w /5 * 2);
            plr_coor[0] = 0;
            plr_coor[1] = w - plr_width;
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        locGroup.act(Gdx.graphics.getDeltaTime());
        locGroup.draw(batch, alpha);
    }
}
